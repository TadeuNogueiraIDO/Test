package br.com.idolink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.PoliciesTermsControllerSwagger;
import br.com.idolink.api.dto.request.PoliciesTermsUseRequest;
import br.com.idolink.api.dto.response.PoliciesTermsUseResponse;
import br.com.idolink.api.service.PoliciesTermsUseService;

@RestController
@RequestMapping("/policiesTermsUse")
public class PoliciesTermsUseController implements PoliciesTermsControllerSwagger {

	@Autowired
	PoliciesTermsUseService service;

	
	@Override
	@GetMapping(path = "/shop/{shop_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<PoliciesTermsUseResponse> findById(@PathVariable(name = "shop_id") Long shopId){
		
		PoliciesTermsUseResponse response = service.findById(shopId);
		
		return ResponseEntity.ok(response);
	}

	@Override
	@DeleteMapping(path = "/shop/{shop_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> delete(@PathVariable(name = "shop_id") Long shopId) {

		service.deleteById(shopId);

		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping(path = "/shop/{shop_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<PoliciesTermsUseResponse> update(@RequestBody PoliciesTermsUseRequest request, @PathVariable(name = "shop_id") Long shopId) {

		PoliciesTermsUseResponse response = service.update(request, shopId);

		return ResponseEntity.ok(response);

	}

}
