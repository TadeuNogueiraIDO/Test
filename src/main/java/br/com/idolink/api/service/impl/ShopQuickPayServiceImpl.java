package br.com.idolink.api.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.QuickPayFinishRequest;
import br.com.idolink.api.dto.request.QuickPayOrderRequest;
import br.com.idolink.api.dto.request.ShopQuickPayCheckoutRequest;
import br.com.idolink.api.dto.response.GenericDetailOrderResponse;
import br.com.idolink.api.dto.response.ShopQuickPayCheckoutResponse;
import br.com.idolink.api.dto.response.ShopQuickPayResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ShopQuickPayMapper;
import br.com.idolink.api.mapper.ShopQuickPayProductMapper;
import br.com.idolink.api.model.ClientQuickPay;
import br.com.idolink.api.model.Notification;
import br.com.idolink.api.model.NotificationSetting;
import br.com.idolink.api.model.PaymentType;
import br.com.idolink.api.model.ShopProductVariation;
import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.ShopQuickPayProduct;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.enums.NotificationType;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.model.enums.TypeShipping;
import br.com.idolink.api.repository.NotificationSettingRepository;
import br.com.idolink.api.repository.PaymentTypeRepository;
import br.com.idolink.api.repository.ShopProductVariationRepository;
import br.com.idolink.api.repository.ShopQuickPayRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.service.MessagePropertieService;
import br.com.idolink.api.service.NotificationService;
import br.com.idolink.api.service.ShopQuickPayService;
import br.com.idolink.api.utils.IdoStringUtils;

@Service
public class ShopQuickPayServiceImpl implements ShopQuickPayService {

	@Autowired
	private ShopQuickPayRepository repository;
		
	@Autowired
	private ShopQuickPayMapper mapper;
	
	@Autowired
	private ShopQuickPayProductMapper productMapper;
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private NotificationSettingRepository notificationServiceRepository;
	
	@Autowired
	private ShopProductVariationRepository shopProductVariationRepository;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	@Override
	public List<ShopQuickPayResponse> listByUser(Long userId) {

		Optional<User> user =  userRepository.findById(userId);
		validate(user, "User", "api.error.user.not.found");
		
		List<ShopQuickPay> model = repository.findByUser(user.get());
		return mapper.modelToResponse(model);
	}

	@Override
	public ShopQuickPayResponse findById(Long id) {
		
		Optional<ShopQuickPay> model =  repository.findById(id);
		validate(model, "ShopQuickPay", "api.error.shop.quick.pay.not.found");
		
		return mapper.modelToResponse(model.get());
	}
	
	
	@Override
	@Transactional
	public ShopQuickPayCheckoutResponse createCheckoutShopQuickPay(Long userId, ShopQuickPayCheckoutRequest profileRequest) {
		
		Optional<User> user =  userRepository.findById(userId);
		validate(user, "User", "api.error.user.not.found");
				
		ShopQuickPay modelPay = new ShopQuickPay();
		
		validateProductRequest(profileRequest);
		
		BigDecimal valueNotification = null;
		
		List<ShopQuickPayProduct> products = productMapper.requestToModel(profileRequest.getProducts());
		
		for (ShopQuickPayProduct singleQuickPayProduct : products) {
			
			Optional<ShopProductVariation> optProduct =   shopProductVariationRepository.findById(singleQuickPayProduct.getShopProductVariation().getId());
			
			validate(optProduct,  "ShopProductVariation", "api.error.shop.product.variation.not.found");
			ShopProductVariation product = optProduct.get();
			
			if(product.getHasStockControl()) {
				//atualiza o estoque da loja
				validateProductStock(product, singleQuickPayProduct.getQuantity());
				product.setAmount(product.getAmount() - singleQuickPayProduct.getQuantity());
				shopProductVariationRepository.save(product);
			}
			
			singleQuickPayProduct.setShopProductVariation(product);
			singleQuickPayProduct.setShopQuickPay(modelPay);
			
			valueNotification = product.getPrice();
			createNotification(product.getShopProduct().getShopCategory().getShop().getIdo().getBusiness().getUser(), valueNotification, modelPay.getId());
			
		}
		
		//tipo padrao para dinheiro
		Optional<PaymentType> payment =  paymentTypeRepository.findById(1L);
		modelPay.setPaymentType(payment.get());
		
		modelPay.setProducts(products);
			
		modelPay.setDiscountValue(profileRequest.getDiscountValue());
		modelPay.setAdditionalValue(profileRequest.getAdditionalValue());
		modelPay.setUser(user.get());
		modelPay.setOrderNumber(IdoStringUtils.generateNumberOrder());
		modelPay.setStatusPayment(QuickPayPaymentStatus.WAITINGPAYMENT);
		modelPay.setStatusSeding(QuickPaySedingStatus.PENDING);
		
		modelPay.setTypeShipping(TypeShipping.LOCALPICKUP);
		modelPay.setShippingDescription("");
		
		modelPay = repository.save(modelPay);
		
		ShopQuickPayCheckoutResponse response = ShopQuickPayCheckoutResponse.builder()
				.shopQuickPayId(modelPay.getId())
				.products(productMapper.modelToResponseList(modelPay.getProducts()))
				.discountValue(modelPay.getDiscountValue())
				.additionalValue(modelPay.getAdditionalValue())
				.statusPayment(modelPay.getStatusPayment())
				.statusSeding(modelPay.getStatusSeding())
				.build();
		
		
		
		
		return response;
	}
	
	@Override
	@Transactional
	public GenericDetailOrderResponse findByDetailsShop(Long userId, Long shopQuickPayId) {
		
		String domain = "";
		String nameShop = "";
		Optional<ShopQuickPay> model =  repository.findByIdbyUser(userId,shopQuickPayId);
		validate(model, "ShopQuickPay", "api.error.shop.quick.pay.not.found");
		
		for(ShopQuickPayProduct pr :model.get().getProducts()) {
			nameShop = pr.getShopProductVariation().getShopProduct().getShopCategory().getShop().getName();
			domain = pr.getShopProductVariation().getShopProduct().getShopCategory().getShop().getIdo().getDomain();
		}
		
		Optional<User> user = userRepository.findById(userId);
		validate(user, "User", "api.error.user.not.found");
		ShopQuickPayResponse responseShop = mapper.modelToResponse(model.get());
	
		GenericDetailOrderResponse response = GenericDetailOrderResponse.builder()
				.id(shopQuickPayId)
				.domain(domain)
				.nameShop(nameShop)
				.discountValue(responseShop.getDiscountValue())
				.additionalValue(responseShop.getAdditionalValue())
				.products(responseShop.getProducts())
				.finalizationType(responseShop.getFinalizationType())
				.hasDeliveryAdress(responseShop.getHasDeliveryAdress())
				.orderNumber(responseShop.getOrderNumber())
				.paymentType(responseShop.getPaymentType())
				.clientName(responseShop.getClientName())
				.clientEmail(responseShop.getClientEmail())
				.clientPhone(responseShop.getClientPhone())
				.clientAddress(responseShop.getClientAddress())
				.created(responseShop.getCreatedIn())
				.statusPayment(responseShop.getStatusPayment())
				.statusSeding(responseShop.getStatusSeding())
				.subTotalValue(responseShop.getSubTotalValue())
				.totalValue(responseShop.getTotalValue())
				.typeShipping(responseShop.getTypeShipping())
				.shippingDescription(responseShop.getShippingDescription())
				.build();
		
		
		
		return response;
		
		
		
	}
	@Override
	@Transactional
	public GenericDetailOrderResponse findByDetailsShopByNumberOrder(Long userId, String orderNumber) {
		
		String domain = "";
		String nameShop = "";
		Optional<ShopQuickPay> model =  repository.findByOrderByUser( userId, orderNumber);
		validate(model, "ShopQuickPay", "api.error.shop.quick.pay.not.found");
		
		for(ShopQuickPayProduct pr :model.get().getProducts()) {
			nameShop = pr.getShopProductVariation().getShopProduct().getShopCategory().getShop().getName();
			domain = pr.getShopProductVariation().getShopProduct().getShopCategory().getShop().getIdo().getDomain();
		}
		
		Optional<User> user = userRepository.findById(userId);
		validate(user, "User", "api.error.user.not.found");
		ShopQuickPayResponse responseShop = mapper.modelToResponse(model.get());
	
		GenericDetailOrderResponse response = GenericDetailOrderResponse.builder()
				.id(model.get().getId())
				.domain(domain)
				.nameShop(nameShop)
				.discountValue(responseShop.getDiscountValue())
				.additionalValue(responseShop.getAdditionalValue())
				.products(responseShop.getProducts())
				.finalizationType(responseShop.getFinalizationType())
				.hasDeliveryAdress(responseShop.getHasDeliveryAdress())
				.orderNumber(responseShop.getOrderNumber())
				.paymentType(responseShop.getPaymentType())
				.clientName(responseShop.getClientName())
				.clientEmail(responseShop.getClientEmail())
				.clientPhone(responseShop.getClientPhone())
				.clientAddress(responseShop.getClientAddress())
				.created(responseShop.getCreatedIn())
				.statusPayment(responseShop.getStatusPayment())
				.statusSeding(responseShop.getStatusSeding())
				.subTotalValue(responseShop.getSubTotalValue())
				.totalValue(responseShop.getTotalValue())
				.typeShipping(responseShop.getTypeShipping())
				.shippingDescription(responseShop.getShippingDescription())
				.build();
		
		
		
		return response;
		
		
		
	}
	
	@Override
	@Transactional
	public ShopQuickPayResponse updateOrderShopQuickPay(Long singleQuickPayId, QuickPayOrderRequest request) {
		
		validateDataClient(request);
		
		Optional<ShopQuickPay> modelOpt =  repository.findById(singleQuickPayId);
		validate(modelOpt, "ShopQuickPay", "api.error.shop.quick.pay.not.found");
		
		ShopQuickPay model = modelOpt.get();
		
		model.setFinalizationType(request.getFinalizationType());
		
		ClientQuickPay client = new ClientQuickPay();
		
		if(request.getHasClientData()) {
			client.setClientDocument(request.getClientDocument());
			client.setClientEmail(request.getClientEmail());
			client.setClientName(request.getClientName());
			client.setClientPhone(request.getDialCode()+";"+request.getClientPhone());
		}
		
		if(request.getHasDeliveryAdress()) {
			client.setClientAddress(request.getClientAddress());
			model.setShippingDescription(request.getClientAddress());
		}
		
		model.setDataClient(client);
		model.setHasClientData(request.getHasClientData());
		model.setHasDeliveryAdress(request.getHasDeliveryAdress());
		model.setTypeShipping(request.getTypeShipping());
		model.setShippingDescription(request.getShippingDescription());
		model = repository.save(model);
		return mapper.modelToResponse(model);
		
	}
	
	@Override
	public ShopQuickPayResponse orderPaymentAndSeding(Long singleQuickPayId, QuickPayFinishRequest request) {
		return finishShopQuickPay(singleQuickPayId, request, QuickPayPaymentStatus.PAID, QuickPaySedingStatus.DELIVERED);
	}
	
	@Override
	public ShopQuickPayResponse orderPaymentAndWaitingDelivery(Long singleQuickPayId, QuickPayFinishRequest request) {
		return finishShopQuickPay(singleQuickPayId, request, QuickPayPaymentStatus.PAID, QuickPaySedingStatus.PENDING);
	}
	
	@Transactional
	private ShopQuickPayResponse finishShopQuickPay(Long singleQuickPayId, QuickPayFinishRequest request, QuickPayPaymentStatus statusPayment, QuickPaySedingStatus statusSeding) {
		
		Optional<ShopQuickPay> modelOpt =  repository.findById(singleQuickPayId);
		validate(modelOpt, "ShopQuickPay", "api.error.shop.quick.pay.not.found");
		
		Optional<PaymentType> payment =  paymentTypeRepository.findById(request.getPaymentTypeId());
		validate(modelOpt, "PaymentType", "api.error.payment.type.not.found");
		
		ShopQuickPay model = modelOpt.get();
		model.setStatusPayment(statusPayment);
		model.setStatusSeding(statusSeding);
		model.setAnotherPaymentType(request.getAnotherPaymentType());
		model.setPaymentType(payment.get());
		model = repository.save(model);
		return mapper.modelToResponse(model);
	}
	
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
		
	
	private void validateProductRequest(ShopQuickPayCheckoutRequest request) {

		if(Objects.isNull(request.getProducts()) || request.getProducts().isEmpty()) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "ShopProducts", "api.error.shop.quick.pay.product.not.found");
		}
		
	}
	
	private void validateDataClient(QuickPayOrderRequest request) {
		
		if(request.getHasClientData() && 
				(Objects.isNull(request.getClientDocument()) || 
			Objects.isNull(request.getClientEmail()) ||
					Objects.isNull(request.getClientName()) ||
							Objects.isNull(request.getDialCode()) ||
									Objects.isNull(request.getClientPhone()))) {
			
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Client", "api.error.shop.quick.pay.client.incomplete");
			
		}
		
		if(request.getHasDeliveryAdress() && Objects.isNull(request.getClientAddress())){
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Client", "api.error.shop.quick.pay.adress.not.found");
		}
		
	}
	
	

	private void validateProductStock(ShopProductVariation product, Integer amount) {
		
		Integer rest = (Integer) (product.getAmount() - amount);
		
		String []params = {product.getName()};
		
		if(rest < 0 ) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "ShopProduct", "api.error.shop.quick.pay.product.stock.insufficient", params);
		}
	}
	private void createNotification(User user,BigDecimal price, Long id) {
		
		NotificationSetting settingNotification = notificationServiceRepository.findBySettingUserId(user.getId());

		if(settingNotification.getRequests()) {	
		Notification notification =	Notification.builder()
		.title(messagePropertieService.getMessageAttribute("api.notification.user.title.pedido"))
		.message(messagePropertieService.getMessageAttribute("api.notification.user.request"))
		.icon("8de9e347-0c11-4a54-b1a8-eef9bdd92791")
		.user(user)
		.creationDate(LocalDate.now())
		.value(price)
		.typeNotification(NotificationType.REQUEST).build();
		
		notificationService.create(notification, id.toString());
		}
		
	}
	
	
}
