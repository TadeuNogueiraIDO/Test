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
import br.com.idolink.api.dto.request.SingleQuickPayCheckoutRequest;
import br.com.idolink.api.dto.request.SingleQuickPayProductRequest;
import br.com.idolink.api.dto.response.GenericDetailOrderSingleResponse;
import br.com.idolink.api.dto.response.SingleQuickPayCheckoutResponse;
import br.com.idolink.api.dto.response.SingleQuickPayResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.SingleQuickPayMapper;
import br.com.idolink.api.mapper.SingleQuickPayProductMapper;
import br.com.idolink.api.model.ClientQuickPay;
import br.com.idolink.api.model.Notification;
import br.com.idolink.api.model.NotificationSetting;
import br.com.idolink.api.model.PaymentType;
import br.com.idolink.api.model.SingleQuickPay;
import br.com.idolink.api.model.SingleQuickPayProduct;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.enums.NotificationType;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.model.enums.QuickPayType;
import br.com.idolink.api.model.enums.TypeShipping;
import br.com.idolink.api.repository.NotificationSettingRepository;
import br.com.idolink.api.repository.PaymentTypeRepository;
import br.com.idolink.api.repository.SingleQuickPayRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.service.MessagePropertieService;
import br.com.idolink.api.service.NotificationService;
import br.com.idolink.api.service.SingleQuickPayService;
import br.com.idolink.api.utils.IdoStringUtils;

@Service
public class SingleQuickPayServiceImpl implements SingleQuickPayService {

	@Autowired
	private SingleQuickPayRepository repository;
	
	@Autowired
	private SingleQuickPayMapper mapper;
	
	@Autowired
	private SingleQuickPayProductMapper productMapper;
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	
	@Autowired
	private NotificationSettingRepository notificationServiceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	@Override
	public List<SingleQuickPayResponse> listByUser(Long userId) {
		
		Optional<User> user = userRepository.findById(userId);
		validate(user, "User", "api.error.user.not.found");
		
		List<SingleQuickPay> model = repository.findByUser(user.get());
		return mapper.modelToResponse(model);
	}

	@Override
	public SingleQuickPayResponse findById(Long id) {
		
		Optional<SingleQuickPay> model =  repository.findById(id);
		validate(model, "SingleQuickPay", "api.error.single.quick.pay.not.found");
		
		return mapper.modelToResponse(model.get());
	}
	
	
	@Override
	@Transactional
	public SingleQuickPayCheckoutResponse createCheckoutSingleQuickPay(Long userId, SingleQuickPayCheckoutRequest profileRequest) {
		
		Optional<User> user = userRepository.findById(userId);
		validate(user, "User", "api.error.user.not.found");
		
		if(user.get().getValidateEmail()) {
		
		SingleQuickPay modelPay = new SingleQuickPay();
		
		
		if(profileRequest.getType().equals(QuickPayType.SINGLE)) {
			
			validateSingleRequest(profileRequest);
			
			modelPay = SingleQuickPay.builder()
					.singlePrice(profileRequest.getSinglePrice())
					.singleQuantity(profileRequest.getSingleQuantity())
					.type(profileRequest.getType())
					.observation(profileRequest.getObservation()).build();
		}else {
			validateProductRequest(profileRequest);
			List<SingleQuickPayProduct> products = productMapper.requestToModel(profileRequest.getProducts());
			
			for (SingleQuickPayProduct singleQuickPayProduct : products) {
				singleQuickPayProduct.setSingleQuickPay(modelPay);
			}
			
			modelPay.setProducts(products);
		}			
		
		//tipo padrao para dinheiro
		Optional<PaymentType> payment =  paymentTypeRepository.findById(1L);
		modelPay.setPaymentType(payment.get());
		modelPay.setDiscountValue(profileRequest.getDiscountValue());
		modelPay.setAdditionalValue(profileRequest.getAdditionalValue());
		modelPay.setType(profileRequest.getType());
		modelPay.setUser(user.get());
		modelPay.setOrderNumber(IdoStringUtils.generateNumberOrder());
		modelPay.setStatusPayment(QuickPayPaymentStatus.WAITINGPAYMENT);
		modelPay.setStatusSeding(QuickPaySedingStatus.PENDING);
		
		modelPay.setTypeShipping(TypeShipping.LOCALPICKUP);
		modelPay.setShippingDescription("");
		
		modelPay = repository.save(modelPay);
		
		createNotification(user.get(), profileRequest.getSinglePrice());
		
		SingleQuickPayCheckoutResponse response = SingleQuickPayCheckoutResponse.builder()
				.singleQuickPayId(modelPay.getId())
				.singlePrice(modelPay.getSinglePrice())
				.singleQuantity(modelPay.getSingleQuantity())
				.type(modelPay.getType())
				.products(productMapper.modelToResponseList(modelPay.getProducts()))
				.observation(modelPay.getObservation())
				.discountValue(modelPay.getDiscountValue())
				.additionalValue(modelPay.getAdditionalValue())
				.statusPayment(modelPay.getStatusPayment())
				.statusSeding(modelPay.getStatusSeding())
				.build();
		
		return response;
		
		} else {
			
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Email","api.error.email.authorization.conflict");
			
		}
	}
	@Override
	@Transactional
	public GenericDetailOrderSingleResponse findByDetailsSingle(Long userId, Long singleQuickPayId) {
	
		Optional<SingleQuickPay> model =  repository.findByIdByUser(userId,singleQuickPayId);
		validate(model, "ShopQuickPay", "api.error.shop.quick.pay.not.found");
		
		Optional<User> user = userRepository.findById(userId);
		validate(user, "User", "api.error.user.not.found");
		SingleQuickPayResponse responseSingle = mapper.modelToResponse(model.get());
	
		GenericDetailOrderSingleResponse response = GenericDetailOrderSingleResponse.builder()
				.id(singleQuickPayId)
				.discountValue(responseSingle.getDiscountValue())
				.additionalValue(responseSingle.getAdditionalValue())
				.products(responseSingle.getProducts())
				.singlePrice(responseSingle.getSinglePrice())
				.observation(responseSingle.getObservation())
				.singleQuantity(responseSingle.getSingleQuantity())
				.finalizationType(responseSingle.getFinalizationType())
				.hasDeliveryAdress(responseSingle.getHasDeliveryAdress())
				.orderNumber(responseSingle.getOrderNumber())
				.paymentType(responseSingle.getPaymentType())
				.clientName(responseSingle.getClientName())
				.clientEmail(responseSingle.getClientEmail())
				.clientPhone(responseSingle.getClientPhone())
				.clientAddress(responseSingle.getClientAddress())
				.created(responseSingle.getCreatedIn())
				.statusPayment(responseSingle.getStatusPayment())
				.statusSeding(responseSingle.getStatusSeding())
				.subTotalValue(responseSingle.getSubTotalValue())
				.totalValue(responseSingle.getTotalValue())
				.typeShipping(responseSingle.getTypeShipping())
				.shippingDescription(responseSingle.getShippingDescription())
				.build();
		
		
		
		return response;
		
		
		
	}
	@Override
	@Transactional
	public GenericDetailOrderSingleResponse findByDetailsSingleByOrder(Long userId, String orderNumber) {
	
		Optional<SingleQuickPay> model =  repository.findByOrderByUser(userId, orderNumber);
		validate(model, "ShopQuickPay", "api.error.shop.quick.pay.not.found");
		
		Optional<User> user = userRepository.findById(userId);
		validate(user, "User", "api.error.user.not.found");
		SingleQuickPayResponse responseSingle = mapper.modelToResponse(model.get());
	
		GenericDetailOrderSingleResponse response = GenericDetailOrderSingleResponse.builder()
				.id(model.get().getId())
				.discountValue(responseSingle.getDiscountValue())
				.additionalValue(responseSingle.getAdditionalValue())
				.products(responseSingle.getProducts())
				.singlePrice(responseSingle.getSinglePrice())
				.observation(responseSingle.getObservation())
				.singleQuantity(responseSingle.getSingleQuantity())
				.finalizationType(responseSingle.getFinalizationType())
				.hasDeliveryAdress(responseSingle.getHasDeliveryAdress())
				.orderNumber(responseSingle.getOrderNumber())
				.paymentType(responseSingle.getPaymentType())
				.clientName(responseSingle.getClientName())
				.clientEmail(responseSingle.getClientEmail())
				.clientPhone(responseSingle.getClientPhone())
				.clientAddress(responseSingle.getClientAddress())
				.created(responseSingle.getCreatedIn())
				.statusPayment(responseSingle.getStatusPayment())
				.statusSeding(responseSingle.getStatusSeding())
				.subTotalValue(responseSingle.getSubTotalValue())
				.totalValue(responseSingle.getTotalValue())
				.typeShipping(responseSingle.getTypeShipping())
				.shippingDescription(responseSingle.getShippingDescription())
				.build();
		
		
		
		return response;
		
		
		
	}
	
	@Override
	@Transactional
	public SingleQuickPayResponse updateOrderSingleQuickPay(Long singleQuickPayId, QuickPayOrderRequest request) {
		
		validateDataClient(request);
		
		Optional<SingleQuickPay> modelOpt =  repository.findById(singleQuickPayId);
		validate(modelOpt, "SingleQuickPay", "api.error.single.quick.pay.not.found");
		
		SingleQuickPay model = modelOpt.get();
		
		model.setFinalizationType(request.getFinalizationType());
		
		ClientQuickPay client = new ClientQuickPay();
		
		if(request.getHasClientData()) {
			client.setClientDocument(request.getClientDocument());
			client.setClientEmail(request.getClientEmail());
			client.setClientName(request.getClientName());
			if(Objects.nonNull(request.getDialCode()) && Objects.nonNull(request.getClientPhone())) {
				client.setClientPhone(request.getDialCode()+";"+request.getClientPhone());
			}
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
	public SingleQuickPayResponse orderPaymentAndSeding(Long singleQuickPayId, QuickPayFinishRequest request) {
		return finishSingleQuickPay(singleQuickPayId, request, QuickPayPaymentStatus.PAID, QuickPaySedingStatus.DELIVERED);
	}
	
	@Override
	public SingleQuickPayResponse orderPaymentAndWaitingDelivery(Long singleQuickPayId, QuickPayFinishRequest request) {
		return finishSingleQuickPay(singleQuickPayId, request, QuickPayPaymentStatus.PAID, QuickPaySedingStatus.PENDING);
	}
	
	@Transactional
	private SingleQuickPayResponse finishSingleQuickPay(Long singleQuickPayId, QuickPayFinishRequest request, QuickPayPaymentStatus statusPayment, QuickPaySedingStatus statusSeding) {
		
		Optional<SingleQuickPay> modelOpt =  repository.findById(singleQuickPayId);
		validate(modelOpt, "SingleQuickPay", "api.error.single.quick.pay.not.found");
		
		Optional<PaymentType> payment =  paymentTypeRepository.findById(request.getPaymentTypeId());
		validate(modelOpt, "PaymentType", "api.error.payment.type.not.found");
		
		SingleQuickPay model = modelOpt.get();
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
		
	private void validateSingleRequest(SingleQuickPayCheckoutRequest request) {

		
		if (Objects.isNull(request.getSinglePrice())) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Value", "api.error.single.quick.pay.value.not.found");
		}
		if (Objects.isNull(request.getSingleQuantity())) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Quantity", "api.error.single.quick.pay.quantity.not.found");
		}
	}
	
	private void validateProductRequest(SingleQuickPayCheckoutRequest request) {

		if(Objects.isNull(request.getProducts()) || request.getProducts().isEmpty()) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Products", "api.error.single.quick.pay.product.not.found");
		}
		
		for (SingleQuickPayProductRequest product : request.getProducts()) {
			
			if (Objects.isNull(product.getName())) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "Name", "api.error.single.quick.pay.product.name.not.found");
			}
			
			if (Objects.isNull(product.getPrice())) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "Price", "api.error.single.quick.pay.product.price.not.found");
			}
			
			if (Objects.isNull(product.getQuantity())) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "Quantity", "api.error.single.quick.pay.product.quantity.not.found");
			}
		}
	}
	
	private void validateDataClient(QuickPayOrderRequest request) {
		
		if(request.getHasClientData() && 
				Objects.isNull(request.getClientName())){
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Client", "api.error.single.quick.pay.name.not.found");
		}
		
		if(request.getHasDeliveryAdress() && Objects.isNull(request.getClientAddress())){
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Client", "api.error.single.quick.pay.address.not.found");
		}
		
	}
	
	private void createNotification(User user,BigDecimal singlePrice ) {
		
		NotificationSetting settingNotification = notificationServiceRepository.findBySettingUserId(user.getId());
		
		if(settingNotification.getRequests()) {
			
		Notification notification =	Notification.builder()
		.title(messagePropertieService.getMessageAttribute("api.notification.user.title.pedido"))
		.message(messagePropertieService.getMessageAttribute("api.notification.user.request"))
		.icon("8de9e347-0c11-4a54-b1a8-eef9bdd92791")
		.user(user)
		.creationDate(LocalDate.now())
		.value(singlePrice)
		.typeNotification(NotificationType.REQUEST).build();
		
		
		notificationService.create( notification );
		} 
	} 
}
