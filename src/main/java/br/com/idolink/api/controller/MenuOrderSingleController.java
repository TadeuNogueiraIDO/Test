package br.com.idolink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.MenuOrderSingleControllerSwagger;
import br.com.idolink.api.dto.request.MenuOrderCancelSingleItemRequest;
import br.com.idolink.api.dto.response.GenericDetailOrderSingleResponse;
import br.com.idolink.api.dto.response.MenuOrderDeliveryResponse;
import br.com.idolink.api.dto.response.MenuOrderDetailItemResponse;
import br.com.idolink.api.dto.response.MenuOrderPaymentResponse;
import br.com.idolink.api.model.enums.QuickPayFinalizationType;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.model.enums.TypeShipping;
import br.com.idolink.api.service.MenuOrderService;
import br.com.idolink.api.service.SingleQuickPayService;

@RestController
@RequestMapping("/order/single")
public class MenuOrderSingleController implements MenuOrderSingleControllerSwagger {
	
	@Autowired
	private IdolinkSecurity security;

	@Autowired
	private MenuOrderService service;
	
	@Autowired
	private SingleQuickPayService singleQuickPayService;
	
	@Override
	@GetMapping("/{orderId}/detail")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderDetailItemResponse> findMenuOrderSingleDetailItem(@PathVariable Long orderId) {
		return ResponseEntity.ok(service.findMenuOrderSingleDetail(security.getUsuarioId(), orderId));
	}

	@Override
	@GetMapping("/{orderId}/payment")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderPaymentResponse> findMenuOrderSinglePayment(@PathVariable Long orderId) {
		return ResponseEntity.ok(service.findMenuOrderSinglePayment(security.getUsuarioId(), orderId));
	}

	@Override
	@GetMapping("/{orderId}/delivery")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderDeliveryResponse> findMenuOrderSingleDelivery(@PathVariable Long orderId) {
		return ResponseEntity.ok(service.findMenuOrderSingleDelivery(security.getUsuarioId(), orderId));
	}

	@Override
	@PutMapping("/{orderId}/cancel")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderDetailItemResponse> cancelOrderSingle(@PathVariable Long orderId,
			MenuOrderCancelSingleItemRequest request) {
		MenuOrderDetailItemResponse model = service.cancelMenuOrderSingleItem(security.getUsuarioId(), orderId,
				request);
		if (model != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(model);
	}
	@Override
	@GetMapping("/{orderId}/")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<GenericDetailOrderSingleResponse> findSingleQuickPaydetails(@PathVariable Long orderId){
		
		GenericDetailOrderSingleResponse response = singleQuickPayService.findByDetailsSingle(security.getUsuarioId(), orderId);
		
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping("/{orderId}/delivery/status")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderDeliveryResponse> updateMenuOrderDeliverySingleStatus(@PathVariable Long orderId,
			QuickPaySedingStatus statusSeding) {

		return ResponseEntity
				.ok(service.updateMenuOrderDeliverySingleStatus(security.getUsuarioId(), orderId, statusSeding));

	}

	@Override
	@PutMapping("/{orderId}/payment/status")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderPaymentResponse> updateMenuOrderPaymentSingleStatus(@PathVariable Long orderId,
			QuickPayPaymentStatus statusPayment) {

		return ResponseEntity
				.ok(service.updateMenuOrderPaymentSingleStatus(security.getUsuarioId(), orderId, statusPayment));
	}

	@Override
	@PutMapping("/{orderId}/delivery/freight")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderDeliveryResponse> updateMenuOrderDeliverySinglefreight(@PathVariable Long orderId,
			String freigth, TypeShipping typeFreigth) {

		return ResponseEntity.ok(
				service.updateMenuOrderDeliverySinglefreight(security.getUsuarioId(), orderId, freigth, typeFreigth));
	}

	@Override
	@PutMapping("/{orderId}/payment/type")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuOrderPaymentResponse> updateMenuOrderSinglePaymentType(@PathVariable Long orderId,
			QuickPayFinalizationType finalizationType, @RequestParam(required = false) Long paymentTypeId, String anotherPaymentType) {
		return ResponseEntity.ok(service.updateMenuOrderSinglePaymentType(security.getUsuarioId(), orderId,
				finalizationType, paymentTypeId, anotherPaymentType));
	}

}
