package br.com.idolink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.CustomDomainControllerSwagger;
import br.com.idolink.api.dto.request.CustomDomainRequest;
import br.com.idolink.api.dto.response.CustomDomainResponse;
import br.com.idolink.api.service.CustomDomainService;

@RestController
@RequestMapping("/custom-domain")
public class CustomDomainController implements CustomDomainControllerSwagger{

	@Autowired
	private CustomDomainService service;	

	@GetMapping(path = "ido/{id}/domain")
	@CheckSecurity.User.PodeAcessar
	@Override
	public ResponseEntity<CustomDomainResponse> getByIdo(@PathVariable("id") Long idoId) {
		CustomDomainResponse response = service.findByIdo(idoId);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(path = "ido/{id}/domain")
	@CheckSecurity.User.PodeAcessar
	@Override
	public ResponseEntity<CustomDomainResponse> create(@RequestBody CustomDomainRequest request, @PathVariable("id") Long idoId) {
		CustomDomainResponse response = service.create(request, idoId);
		return ResponseEntity.ok(response);
		
	}
	@DeleteMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	@Override
	public ResponseEntity<Void> delete( @PathVariable("id") Long ido) {
		 service.delete(ido);
		return ResponseEntity.noContent().build();
		
	}
	@PutMapping(path = "/{ido}/domain")
	@CheckSecurity.User.PodeAcessar
	@Override
	public ResponseEntity<CustomDomainResponse> update (@RequestBody CustomDomainRequest request, @PathVariable("ido") Long idoId) {
		CustomDomainResponse response = service.update(request, idoId);
		return ResponseEntity.ok(response);
		
	}

}
