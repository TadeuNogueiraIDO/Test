package br.com.idolink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.MenuOrderShopControllerSwagger;
import br.com.idolink.api.dto.request.MenuOrderCancelItemRequest;
import br.com.idolink.api.dto.response.GenericDetailOrderResponse;
import br.com.idolink.api.dto.response.MenuOrderDeliveryResponse;
import br.com.idolink.api.dto.response.MenuOrderDetailItemResponse;
import br.com.idolink.api.dto.response.MenuOrderPaymentResponse;
import br.com.idolink.api.model.enums.QuickPayFinalizationType;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.model.enums.TypeShipping;
import br.com.idolink.api.service.MenuOrderService;
import br.com.idolink.api.service.ShopQuickPayService;

@RestController
@RequestMapping("/order/shop")
public class MenuOrderShopController implements MenuOrderShopControllerSwagger{
	
	@Autowired
	private IdolinkSecurity security;
	
	@Autowired
	private MenuOrderService service;
	
	@Autowired
	private ShopQuickPayService shopQuickPayService;

	@Override
	@GetMapping("/{orderId}/detail")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderDetailItemResponse> findMenuOrderShopDetailItem(@PathVariable Long orderId) {
		return ResponseEntity.ok(service.findMenuOrderShopDetail(security.getUsuarioId(), orderId));
	}
	
	@Override
	@GetMapping("/{orderId}/payment")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderPaymentResponse> findMenuOrderShopPayment(@PathVariable Long orderId) {
		return ResponseEntity.ok(service.findMenuOrderShopPayment(security.getUsuarioId(), orderId));
	}
	
	@Override
	@GetMapping("/{orderId}/delivery")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderDeliveryResponse> findMenuOrderShopDelivery(@PathVariable Long orderId) {
		return ResponseEntity.ok(service.findMenuOrderShopDelivery(security.getUsuarioId(), orderId));
	}
	
	@Override
	@PutMapping("/{orderId}/cancel")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderDetailItemResponse> cancelOrderShop(@PathVariable Long orderId, MenuOrderCancelItemRequest request) {
		MenuOrderDetailItemResponse model = service.cancelMenuOrderShopItem(security.getUsuarioId(), orderId, request);
		if(model != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(model);
	}
	
	@Override
	@GetMapping("/{orderId}/")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<GenericDetailOrderResponse> findShopQuickPaydetails(@PathVariable Long orderId){
		
		GenericDetailOrderResponse response = shopQuickPayService.findByDetailsShop(security.getUsuarioId(), orderId);
		
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping("/{orderId}/delivery/status")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderDeliveryResponse> updateMenuOrderDeliveryShopStatus(@PathVariable Long orderId,
			QuickPaySedingStatus statusSeding) {
		
		return ResponseEntity.ok(service.updateMenuOrderDeliveryShopStatus(security.getUsuarioId(), orderId, statusSeding));

	}
	@Override
	@PutMapping("/{orderId}/payment/status")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderPaymentResponse> updateMenuOrderPaymentShopStatus(@PathVariable Long orderId,
			QuickPayPaymentStatus statusPayment) {
		
		return ResponseEntity.ok(service.updateMenuOrderPaymentShopStatus(security.getUsuarioId(), orderId, statusPayment));
	}

	@Override
	@PutMapping("/{orderId}/delivery/freight")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderDeliveryResponse> updateMenuOrderDeliveryShopfreight(@PathVariable Long orderId,
			String freigth, TypeShipping typeFreigth) {
		return ResponseEntity.ok(service.updateMenuOrderDeliveryShopfreight(security.getUsuarioId(), orderId, freigth, typeFreigth));
	}

	@Override
	@PutMapping("/{orderId}/payment/type")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderPaymentResponse> updateMenuOrderShopPaymentType(@PathVariable Long orderId, QuickPayFinalizationType finalizationType, Long paymentTypeId, String anotherPaymentType ) {
		return ResponseEntity.ok(service.updateMenuOrderShopPaymentType(security.getUsuarioId(),orderId,  finalizationType, paymentTypeId, anotherPaymentType));
	}
}
