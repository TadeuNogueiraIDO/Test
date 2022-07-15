package br.com.idolink.api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

import br.com.idolink.api.dto.request.MenuOrderCancelItemRequest;
import br.com.idolink.api.dto.request.MenuOrderCancelSingleItemRequest;
import br.com.idolink.api.dto.response.MenuOrderDeliveryResponse;
import br.com.idolink.api.dto.response.MenuOrderDetailItemResponse;
import br.com.idolink.api.dto.response.MenuOrderPaymentResponse;
import br.com.idolink.api.dto.response.MenuOrderResponse;
import br.com.idolink.api.filter.MenuOrderFilter;
import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.SingleQuickPay;
import br.com.idolink.api.model.enums.QuickPayFinalizationType;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.model.enums.TypeShipping;

public interface MenuOrderService {
	
	List<MenuOrderResponse> findMenuOrder(Long userId, MenuOrderFilter filter);
	
	MenuOrderDetailItemResponse findMenuOrderSingleDetail(Long userId, Long orderId);
	
	MenuOrderDetailItemResponse findMenuOrderShopDetail(Long userId, Long orderId);
	
	
	MenuOrderPaymentResponse findMenuOrderSinglePayment(Long userId, Long orderId);
	
	MenuOrderPaymentResponse findMenuOrderShopPayment(Long userId, Long orderId);
	 

	MenuOrderDeliveryResponse findMenuOrderSingleDelivery(Long userId, Long orderId);
	
	MenuOrderDeliveryResponse findMenuOrderShopDelivery(Long userId, Long orderId);
	

	MenuOrderDeliveryResponse updateMenuOrderDeliveryShopStatus(Long userId, Long orderId, QuickPaySedingStatus statusSeding);
	
	MenuOrderDeliveryResponse updateMenuOrderDeliverySingleStatus(Long userId, Long orderId, QuickPaySedingStatus statusSeding);
	
	
	MenuOrderPaymentResponse updateMenuOrderPaymentSingleStatus(Long userId, Long orderId, QuickPayPaymentStatus statusPayment);

	MenuOrderPaymentResponse updateMenuOrderPaymentShopStatus(Long userId, Long orderId, QuickPayPaymentStatus statusPayment);

	MenuOrderDetailItemResponse cancelMenuOrderSingleItem(Long userId, Long orderId, MenuOrderCancelSingleItemRequest request);
	
	MenuOrderDetailItemResponse cancelMenuOrderShopItem(Long userId,Long orderId, MenuOrderCancelItemRequest request);
	
	MenuOrderDeliveryResponse updateMenuOrderDeliverySinglefreight(Long userId, Long orderId,String freigth, TypeShipping typeFreigth);
	
	MenuOrderDeliveryResponse updateMenuOrderDeliveryShopfreight(Long userId, Long orderId,String freigth, TypeShipping typeFreigth);
	
	ImmutablePair<List<SingleQuickPay>, List<ShopQuickPay>> findOrderInvoice(LocalDate date, List<Long> idosId, Long userId);
	
	ImmutablePair<List<SingleQuickPay>, List<ShopQuickPay>> findOrderDashboard(List<LocalDateTime> dates, Long userId, Long shopId);

	MenuOrderPaymentResponse updateMenuOrderShopPaymentType(Long userId, Long orderId,
			QuickPayFinalizationType finalizationType, Long paymentTypeId, String anotherPaymentType );

	MenuOrderPaymentResponse updateMenuOrderSinglePaymentType(Long userId, Long orderId,
			QuickPayFinalizationType finalizationType, Long paymentTypeId, String anotherPaymentType );


}
