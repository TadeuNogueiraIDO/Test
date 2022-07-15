package br.com.idolink.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.SingleQuickPayControllerSwagger;
import br.com.idolink.api.dto.request.QuickPayFinishRequest;
import br.com.idolink.api.dto.request.QuickPayOrderRequest;
import br.com.idolink.api.dto.request.SingleQuickPayCheckoutRequest;
import br.com.idolink.api.dto.response.SingleQuickPayCheckoutResponse;
import br.com.idolink.api.dto.response.SingleQuickPayResponse;
import br.com.idolink.api.service.SingleQuickPayService;

@RestController
@RequestMapping("/single-quick-pay")
public class SingleQuickPayController  implements SingleQuickPayControllerSwagger{
	
	@Autowired
	private SingleQuickPayService service;
	
	@Autowired
	private IdolinkSecurity idoLinkSecurity;
		
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
		SingleQuickPayResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping()
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<SingleQuickPayResponse>> findSingleQuickPay() {
		List<SingleQuickPayResponse> response = service.listByUser(idoLinkSecurity.getUsuarioId());
		return ResponseEntity.ok(response);
	}
	
	@CheckSecurity.User.PodeAcessar
	@PostMapping()
	@Override
	public ResponseEntity<SingleQuickPayCheckoutResponse> createSingleQuickPay(@Valid @RequestBody SingleQuickPayCheckoutRequest profileRequest) {
		SingleQuickPayCheckoutResponse response = service.createCheckoutSingleQuickPay(idoLinkSecurity.getUsuarioId(), profileRequest);
		return ResponseEntity.ok(response);
	}
	
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/order/{quickpay_id}")
	@Override
	public ResponseEntity<SingleQuickPayResponse> updateOrderSingleQuickPay(@PathVariable(name = "quickpay_id") Long payId, @Valid @RequestBody QuickPayOrderRequest profileRequest) {
		SingleQuickPayResponse response = service.updateOrderSingleQuickPay(payId, profileRequest);
		return ResponseEntity.ok(response);
	}
	
	
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/paid-delivered/{quickpay_id}")
	@Override
	public ResponseEntity<SingleQuickPayResponse> orderPaymentAndSeding(@PathVariable(name = "quickpay_id") Long payId, @Valid @RequestBody QuickPayFinishRequest profileRequest) {
		SingleQuickPayResponse response = service.orderPaymentAndSeding(payId, profileRequest);
		return ResponseEntity.ok(response);
	}
	
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/paid/{quickpay_id}")
	@Override
	public ResponseEntity<SingleQuickPayResponse> orderPaymentAndWaitingDelivery(@PathVariable(name = "quickpay_id") Long payId, @Valid @RequestBody QuickPayFinishRequest profileRequest) {
		SingleQuickPayResponse response = service.orderPaymentAndWaitingDelivery(payId, profileRequest);
		return ResponseEntity.ok(response);
	}
	
}
