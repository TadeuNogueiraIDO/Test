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
import br.com.idolink.api.controller.swagger.ShopQuickPayControllerSwagger;
import br.com.idolink.api.dto.request.QuickPayFinishRequest;
import br.com.idolink.api.dto.request.QuickPayOrderRequest;
import br.com.idolink.api.dto.request.ShopQuickPayCheckoutRequest;
import br.com.idolink.api.dto.response.ShopQuickPayCheckoutResponse;
import br.com.idolink.api.dto.response.ShopQuickPayResponse;
import br.com.idolink.api.service.ShopQuickPayService;

@RestController
@RequestMapping("/shop-quick-pay")
public class ShopQuickPayController  implements ShopQuickPayControllerSwagger{
	
	@Autowired
	private ShopQuickPayService service;
	
	@Autowired
	private IdolinkSecurity idoLinkSecurity;
		
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
		ShopQuickPayResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<ShopQuickPayResponse>> findShopQuickPayByUser() {
		List<ShopQuickPayResponse> response = service.listByUser(idoLinkSecurity.getUsuarioId());
		return ResponseEntity.ok(response);
	}
	
	@CheckSecurity.User.PodeAcessar
	@PostMapping
	@Override
	public ResponseEntity<ShopQuickPayCheckoutResponse> createShopQuickPay(@Valid @RequestBody ShopQuickPayCheckoutRequest profileRequest) {
		ShopQuickPayCheckoutResponse response = service.createCheckoutShopQuickPay(idoLinkSecurity.getUsuarioId(), profileRequest);
		return ResponseEntity.ok(response);
	}
	
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/order/{quickpay_id}")
	@Override
	public ResponseEntity<ShopQuickPayResponse> updateOrderShopQuickPay(@PathVariable(name = "quickpay_id") Long payId, @Valid @RequestBody QuickPayOrderRequest profileRequest) {
		ShopQuickPayResponse response = service.updateOrderShopQuickPay(payId, profileRequest);
		return ResponseEntity.ok(response);
	}
	
	
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/paid-delivered/{quickpay_id}")
	@Override
	public ResponseEntity<ShopQuickPayResponse> orderPaymentAndSeding(@PathVariable(name = "quickpay_id") Long payId, @Valid @RequestBody QuickPayFinishRequest profileRequest) {
		ShopQuickPayResponse response = service.orderPaymentAndSeding(payId, profileRequest);
		return ResponseEntity.ok(response);
	}
	
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/paid/{quickpay_id}")
	@Override
	public ResponseEntity<ShopQuickPayResponse> orderPaymentAndWaitingDelivery(@PathVariable(name = "quickpay_id") Long payId, @Valid @RequestBody QuickPayFinishRequest profileRequest) {
		ShopQuickPayResponse response = service.orderPaymentAndWaitingDelivery(payId, profileRequest);
		return ResponseEntity.ok(response);
	}
	
}
