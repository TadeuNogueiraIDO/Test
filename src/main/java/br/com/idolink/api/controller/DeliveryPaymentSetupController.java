package br.com.idolink.api.controller;

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
import br.com.idolink.api.controller.swagger.DeliveryPaymentSetupControllerSwagger;
import br.com.idolink.api.dto.request.DeliveryPaymentSetupRequest;
import br.com.idolink.api.dto.response.DeliveryPaymentSetupResponse;
import br.com.idolink.api.service.DeliveryPaymentSetupService;

@RestController
@RequestMapping("/delivery-payment-setup")
public class DeliveryPaymentSetupController implements DeliveryPaymentSetupControllerSwagger{

	@Autowired
	private DeliveryPaymentSetupService service;
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/shop/{shop_id}")
	public ResponseEntity<DeliveryPaymentSetupResponse> findByShop(@PathVariable(name = "shop_id") Long id) {
		DeliveryPaymentSetupResponse response = service.findByShop(id);
		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/shop/{shop_id}")
	public ResponseEntity<DeliveryPaymentSetupResponse> create(@PathVariable(name = "shop_id") Long shopId,
			@Valid @RequestBody DeliveryPaymentSetupRequest request) {
		DeliveryPaymentSetupResponse response = service.create(shopId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{shop_id}")
	public ResponseEntity<DeliveryPaymentSetupResponse> update(@Valid @RequestBody DeliveryPaymentSetupRequest request,
			@PathVariable(name = "shop_id") Long id) {
		DeliveryPaymentSetupResponse response = service.update(id, request);
		return ResponseEntity.ok(response);
	}

}