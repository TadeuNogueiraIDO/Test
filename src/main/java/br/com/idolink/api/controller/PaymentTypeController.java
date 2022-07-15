package br.com.idolink.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.PaymentTypeControllerSwagger;
import br.com.idolink.api.dto.response.PaymentTypeResponse;
import br.com.idolink.api.service.PaymentTypeService;

@RestController
@RequestMapping("/payment-type")
public class PaymentTypeController  implements PaymentTypeControllerSwagger{
	
	@Autowired
	private PaymentTypeService service;
			
	@Override
	@GetMapping()
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<PaymentTypeResponse>> listPaymentTypes() {
		List<PaymentTypeResponse> response= service.listPaymentTypes();
		return ResponseEntity.ok(response);
	}

	@Override
	@GetMapping(path = "/{id}")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<PaymentTypeResponse> findPaymentTypeById(@PathVariable(name = "id") Long id) {
		PaymentTypeResponse response= service.findPaymentTypeById(id);
		return ResponseEntity.ok(response);
	}
			
}
