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
import br.com.idolink.api.controller.swagger.PaymentSetupControllerSwagger;
import br.com.idolink.api.dto.request.PaymentSetupRequest;
import br.com.idolink.api.dto.response.PaymentSetupResponse;
import br.com.idolink.api.service.PaymentSetupService;

@RestController
@RequestMapping("/payment-setup")
public class PaymentSetupController implements PaymentSetupControllerSwagger{

	@Autowired
	private PaymentSetupService service;
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping
	public ResponseEntity<List<PaymentSetupResponse>> findAll() {
		List<PaymentSetupResponse> response = service.list();

		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/shop/{shop_id}")
	public ResponseEntity<PaymentSetupResponse> findByIdo(@PathVariable(name = "shop_id") Long id) {
		PaymentSetupResponse response = service.findByShop(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/shop/{shop_id}")
	public ResponseEntity<PaymentSetupResponse> create(@PathVariable(name = "shop_id") Long shopId, 
			@Valid @RequestBody PaymentSetupRequest request) {
		PaymentSetupResponse response = service.create(shopId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{id}")
	public ResponseEntity<PaymentSetupResponse> update(@Valid @RequestBody PaymentSetupRequest request,
			@PathVariable(name = "id") Long id) {
		PaymentSetupResponse response = service.update(id, request);

		return ResponseEntity.ok(response);
	}
}
