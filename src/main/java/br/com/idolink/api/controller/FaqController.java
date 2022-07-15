package br.com.idolink.api.controller;

import javax.validation.Valid;

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
import br.com.idolink.api.controller.swagger.FaqControllerSwagger;
import br.com.idolink.api.dto.request.ConfigFaqRequest;
import br.com.idolink.api.dto.response.ConfigFaqResponse;
import br.com.idolink.api.service.ConfigFaqService;

@RestController
@RequestMapping("/faq")
public class FaqController implements FaqControllerSwagger{
	
	@Autowired
	private ConfigFaqService service;
	
	@Override
	@GetMapping("/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ConfigFaqResponse> findById(@PathVariable(name = "id")Long id) {
		ConfigFaqResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping("/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ConfigFaqResponse> findByIdo(@PathVariable(name = "ido_id") Long idoId) {
		ConfigFaqResponse response = service.findByIdo(idoId);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@PostMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ConfigFaqResponse> create(@PathVariable(name = "ido_id") Long idIdo, @Valid @RequestBody ConfigFaqRequest request) {
		ConfigFaqResponse response = service.create(idIdo, request);
		return ResponseEntity.ok(response);
	}
	
	
	@Override
	@PutMapping("/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ConfigFaqResponse> update(@Valid @RequestBody ConfigFaqRequest request, @PathVariable(name = "id") Long id) {
		ConfigFaqResponse response = service.update(request, id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@DeleteMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
		
}
