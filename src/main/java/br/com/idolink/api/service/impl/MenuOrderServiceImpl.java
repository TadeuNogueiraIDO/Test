package br.com.idolink.api.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.idolink.api.dto.request.MenuOrderCancelItemRequest;
import br.com.idolink.api.dto.request.MenuOrderCancelSingleItemRequest;
import br.com.idolink.api.dto.response.MenuOrderDeliveryResponse;
import br.com.idolink.api.dto.response.MenuOrderDetailItemResponse;
import br.com.idolink.api.dto.response.MenuOrderPaymentResponse;
import br.com.idolink.api.dto.response.MenuOrderResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.filter.MenuOrderFilter;
import br.com.idolink.api.mapper.MenuOrderMapper;
import br.com.idolink.api.model.PaymentType;
import br.com.idolink.api.model.ShopProductVariation;
import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.ShopQuickPayProduct;
import br.com.idolink.api.model.SingleQuickPay;
import br.com.idolink.api.model.SingleQuickPayProduct;
import br.com.idolink.api.model.enums.QuickPayFinalizationType;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.model.enums.TypeShipping;
import br.com.idolink.api.repository.MenuOrderRepository;
import br.com.idolink.api.repository.PaymentTypeRepository;
import br.com.idolink.api.repository.ShopProductVariationRepository;
import br.com.idolink.api.repository.ShopQuickPayRepository;
import br.com.idolink.api.repository.SingleQuickPayRepository;
import br.com.idolink.api.service.MenuOrderService;
import br.com.idolink.api.utils.IdoStringUtils;
import br.com.idolink.api.utils.MenuDateUtils;

@Service
public class MenuOrderServiceImpl implements MenuOrderService {

	@Autowired
	private MenuOrderRepository repository;

	@Autowired
	private MenuOrderMapper mapper;

	@Autowired
	private SingleQuickPayRepository singleQuickPayRepository;

	@Autowired
	private ShopQuickPayRepository shopQuickPayRepository;

	@Autowired
	private ShopProductVariationRepository shopProductVariationRepository;

	@Autowired
	private PaymentTypeRepository paymentTypeRepository;	

	
	@Override
	public List<MenuOrderResponse> findMenuOrder(Long userId, MenuOrderFilter filter) {

		List<LocalDateTime> dates = new ArrayList<>();

		if (filter.getPeriod() != null) {
			dates.addAll(MenuDateUtils.convertEnumToDate(filter.getPeriod()));
		} else {
			if (filter.getInitDate() != null)
				dates.addAll(validateDate(filter.getInitDate(), filter.getEndDate()));
		}

		List<SingleQuickPay> singleQuickPay = new ArrayList<>();
		List<ShopQuickPay> shopQuickPay = new ArrayList<>();

		if (filter.getTypeSale() != null) {

			switch (filter.getTypeSale()) {
			case ONLINE:
				break;
			case QUICKPAY:
				shopQuickPay = repository.findShopQuiqkPay(userId, filter, dates);
				break;
			case QUICKSIMPLE:
				singleQuickPay = repository.findSingleQuiqkPay(userId, filter, dates);
				break;
			default:
				break;
			}
		} else {
			shopQuickPay = repository.findShopQuiqkPay(userId, filter, dates);
			singleQuickPay = repository.findSingleQuiqkPay(userId, filter, dates);
		}

		return mapper.singleQuickPayToMenuOrderResponse(singleQuickPay, shopQuickPay);

	}

	@Override
	public MenuOrderDetailItemResponse findMenuOrderSingleDetail(Long userId, Long orderId) {
		SingleQuickPay model = searchOrFailSingle(userId, orderId);
		return mapper.singleQuickPayToMenuOrderDetailResponse(model);
	}

	@Override
	public MenuOrderDetailItemResponse findMenuOrderShopDetail(Long userId, Long orderId) {
		ShopQuickPay model = searchOrFailShop(userId, orderId);
		return mapper.singleQuickPayToMenuOrderDetailResponse(model);
	}

	@Override
	public MenuOrderPaymentResponse findMenuOrderShopPayment(Long userId, Long orderId) {

		ShopQuickPay model = searchOrFailShop(userId, orderId);
		return mapper.singleQuickPayToMenuOrderPaymentResponse(model);
	}

	@Override
	public MenuOrderPaymentResponse findMenuOrderSinglePayment(Long userId, Long orderId) {

		SingleQuickPay model = searchOrFailSingle(userId, orderId);
		return mapper.singleQuickPayToMenuOrderPaymentResponse(model);
	}

	@Override
	public MenuOrderDeliveryResponse findMenuOrderShopDelivery(Long userId, Long orderId) {
		ShopQuickPay model = searchOrFailShop(userId, orderId);

		return mapper.shopQuickPayToMenuOrderDeliveryResponse(model);
	}

	@Override
	public MenuOrderDeliveryResponse findMenuOrderSingleDelivery(Long userId, Long orderId) {
		SingleQuickPay model = searchOrFailSingle(userId, orderId);

		return mapper.singleQuickPayToMenuOrderDeliveryResponse(model);
	}

	@Override
	@Transactional
	public MenuOrderDetailItemResponse cancelMenuOrderShopItem(Long userId, Long orderId,
			MenuOrderCancelItemRequest request) {

		ShopQuickPay model = searchOrFailShop(userId, orderId);

		if (model.getStatusPayment() == QuickPayPaymentStatus.PAID
				&& model.getStatusSeding() == QuickPaySedingStatus.DELIVERED) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "MenuOrder",
					"api.error.menu.order.bad.request.not.canceled");
		}

		if (model != null) {
			List<ShopQuickPayProduct> products = new ArrayList<>();

			List<ShopQuickPayProduct> listByStock = new ArrayList<>();

			List<Long> itemsId = IdoStringUtils.convertStringLong(request.getItems());

			model.getProducts().forEach(p -> {
				if (!itemsId.contains(p.getId())) {
					products.add(p);
				} else {
					listByStock.add(p);

				}
			});
			if (request.getStock()) {

				for (ShopQuickPayProduct product : listByStock) {

					Optional<ShopProductVariation> variation = shopProductVariationRepository
							.findById(product.getShopProductVariation().getId());

					variation.get().setAmount(variation.get().getAmount() + product.getQuantity());
					shopProductVariationRepository.save(variation.get());

				}

			}

			if (products.size() > 0) {
				model.getProducts().clear();
				model.getProducts().addAll(products);
				return mapper.singleQuickPayToMenuOrderDetailResponse(shopQuickPayRepository.save(model));
			} else {
				shopQuickPayRepository.delete(model);
			}
		}
		return null;
	}

	@Override
	@Transactional
	public MenuOrderDetailItemResponse cancelMenuOrderSingleItem(Long userId, Long orderId,
			MenuOrderCancelSingleItemRequest request) {

		SingleQuickPay model = searchOrFailSingle(userId, orderId);

		if (model.getStatusPayment() == QuickPayPaymentStatus.PAID
				&& model.getStatusSeding() == QuickPaySedingStatus.DELIVERED) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "MenuOrder",
					"api.error.menu.order.bad.request.not.canceled");
		}
		if (model != null && request.getItems() != null) {
			List<SingleQuickPayProduct> products = new ArrayList<>();

			List<Long> itemsId = IdoStringUtils.convertStringLong(request.getItems());

			model.getProducts().forEach(p -> {

				if (!itemsId.contains(p.getId())) {
					products.add(p);
				}
			});
			if (products.size() > 0) {
				model.getProducts().clear();
				model.getProducts().addAll(products);
				return mapper.singleQuickPayToMenuOrderDetailResponse(singleQuickPayRepository.save(model));
			} else {
				singleQuickPayRepository.delete(model);
			}
		} else {
			singleQuickPayRepository.delete(model);
		}
		return null;

	}

	@Override
	@Transactional
	public MenuOrderDeliveryResponse updateMenuOrderDeliverySingleStatus(Long userId, Long orderId,
			QuickPaySedingStatus statusSeding) {

		SingleQuickPay model = searchOrFailSingle(userId, orderId);

		model.setStatusSeding(statusSeding);

		return mapper.singleQuickPayToMenuOrderDeliveryResponse(singleQuickPayRepository.save(model));
	}

	@Override
	@Transactional
	public MenuOrderDeliveryResponse updateMenuOrderDeliveryShopStatus(Long userId, Long orderId,
			QuickPaySedingStatus statusSeding) {

		ShopQuickPay model = searchOrFailShop(userId, orderId);

		model.setStatusSeding(statusSeding);

		return mapper.shopQuickPayToMenuOrderDeliveryResponse(shopQuickPayRepository.save(model));
	}

	@Override
	@Transactional
	public MenuOrderPaymentResponse updateMenuOrderPaymentSingleStatus(Long userId, Long orderId,
			QuickPayPaymentStatus statusPayment) {

		SingleQuickPay model = searchOrFailSingle(userId, orderId);

		model.setStatusPayment(statusPayment);
		return mapper.singleQuickPayToMenuOrderPaymentResponse(singleQuickPayRepository.save(model));
	}

	@Override
	@Transactional
	public MenuOrderPaymentResponse updateMenuOrderPaymentShopStatus(Long userId, Long orderId,
			QuickPayPaymentStatus statusPayment) {

		ShopQuickPay model = searchOrFailShop(userId, orderId);

		model.setStatusPayment(statusPayment);

		return mapper.singleQuickPayToMenuOrderPaymentResponse(shopQuickPayRepository.save(model));
	}

	@Override
	@Transactional
	public MenuOrderDeliveryResponse updateMenuOrderDeliverySinglefreight(Long userId, Long orderId, String freigth,
			TypeShipping typeFreigth) {

		SingleQuickPay model = searchOrFailSingle(userId, orderId);

		model.setTypeShipping(typeFreigth);
		model.setShippingDescription(freigth);
		model.setStatusSeding(QuickPaySedingStatus.SENT);

		return mapper.singleQuickPayToMenuOrderDeliveryResponse(singleQuickPayRepository.save(model));

	}

	@Override
	@Transactional
	public MenuOrderDeliveryResponse updateMenuOrderDeliveryShopfreight(Long userId, Long orderId, String freigth,
			TypeShipping typeFreigth) {

		ShopQuickPay model = searchOrFailShop(userId, orderId);
		model.setTypeShipping(typeFreigth);
		model.setShippingDescription(freigth);

		model.setStatusSeding(QuickPaySedingStatus.SENT);

		return mapper.shopQuickPayToMenuOrderDeliveryResponse(shopQuickPayRepository.save(model));
	}

	@Override
	public ImmutablePair<List<SingleQuickPay>, List<ShopQuickPay>> findOrderInvoice(LocalDate date, List<Long> idosId,
			Long userId) {

		ImmutablePair<List<SingleQuickPay>, List<ShopQuickPay>> orderInvoices = new ImmutablePair<List<SingleQuickPay>, List<ShopQuickPay>>(
				repository.findSingleQuickPayInvoice(date, idosId, userId),
				repository.findShopQuickPayInvoice(date, idosId, userId));
		return orderInvoices;
	}

	@Override
	public ImmutablePair<List<SingleQuickPay>, List<ShopQuickPay>> findOrderDashboard(List<LocalDateTime> dates,
			Long userId, Long shopId) {

		ImmutablePair<List<SingleQuickPay>, List<ShopQuickPay>> orderDashboard = new ImmutablePair<List<SingleQuickPay>, List<ShopQuickPay>>(
				repository.findSingleQuickPayDashboard(dates, userId, shopId),
				repository.findShopQuickPayDashboard(dates, userId, shopId));
		return orderDashboard;
	}

	@Override
	@Transactional
	public MenuOrderPaymentResponse updateMenuOrderShopPaymentType(Long userId, Long orderId,
			QuickPayFinalizationType finalizationType, Long paymentTypeId, String anotherPaymentType) {

		ShopQuickPay model = searchOrFailShop(userId, orderId);

		if (Objects.nonNull(finalizationType)) {
			model.setFinalizationType(finalizationType);
		}

		if (finalizationType.equals(QuickPayFinalizationType.SENDPROPOUSAL)) {

		}

		if (Objects.nonNull(paymentTypeId)) {
			Optional<PaymentType> paymentOpt = paymentTypeRepository.findById(paymentTypeId);
			validate(paymentOpt, "PaymentType", "api.error.payment.type.not.found");
			model.setPaymentType(paymentOpt.get());
		}

		if (Objects.nonNull(Objects.nonNull(anotherPaymentType))) {
			model.setAnotherPaymentType(anotherPaymentType);
		}

		return mapper.singleQuickPayToMenuOrderPaymentResponse(shopQuickPayRepository.save(model));

	}

	@Override
	@Transactional
	public MenuOrderPaymentResponse updateMenuOrderSinglePaymentType(Long userId, Long orderId,
			QuickPayFinalizationType finalizationType, Long paymentTypeId, String anotherPaymentType) {

		SingleQuickPay model = searchOrFailSingle(userId, orderId);

		if (Objects.nonNull(finalizationType)) {
			model.setFinalizationType(finalizationType);
		}

		if (Objects.nonNull(paymentTypeId)) {
			Optional<PaymentType> paymentOpt = paymentTypeRepository.findById(paymentTypeId);
			validate(paymentOpt, "PaymentType", "api.error.payment.type.not.found");
			model.setPaymentType(paymentOpt.get());
		} else {
			if (Objects.nonNull(Objects.nonNull(anotherPaymentType))) {
				model.setAnotherPaymentType(anotherPaymentType);
			}
		}

		return mapper.singleQuickPayToMenuOrderPaymentResponse(singleQuickPayRepository.save(model));
	}

	// PRIVATE METHODS

	private List<LocalDateTime> validateDate(LocalDateTime dateInit, LocalDateTime endDate) {

		List<LocalDateTime> dates = new ArrayList<>();
		if (dateInit != null && endDate != null) {
			if (dateInit.compareTo(endDate) < 0) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "LocalDateTime",
						"api.error.menu.order.bad.request.initial.date");
			}
			dates.add(dateInit);
			dates.add(endDate);
			return dates;
		} else {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "LocalDateTime",
					"api.error.menu.order.bad.request.date.is.needed");
		}
	}

	private SingleQuickPay searchOrFailSingle(Long userId, Long orderId) {

		SingleQuickPay model = singleQuickPayRepository.findByUserIdAndId(userId, orderId);

		if (model != null) {
			return model;
		}
		throw new BaseFullException(HttpStatus.NOT_FOUND, "SingleQuickPay", "api.error.single.quick.pay.not.found");
	}

	private ShopQuickPay searchOrFailShop(Long userId, Long orderId) {
		ShopQuickPay model = shopQuickPayRepository.findByUserIdAndId(userId, orderId);

		if (model != null) {
			return model;
		}
		throw new BaseFullException(HttpStatus.NOT_FOUND, "ShopQuickPay", "api.error.shop.quick.pay.not.found");
	}

	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}


}