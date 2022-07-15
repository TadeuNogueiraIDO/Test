package br.com.idolink.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.ContactControllerSwagger;
import br.com.idolink.api.dto.request.ContactRequest;
import br.com.idolink.api.dto.response.ContactResponse;
import br.com.idolink.api.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController implements ContactControllerSwagger {
	
	@Autowired
	private ContactService service;
	
	@Override
	@GetMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Page<ContactResponse>> findAll(Pageable pageable) {
		
		Page<ContactResponse> response = service.findAll( pageable);
		
		return ResponseEntity.ok(response);
		
	}
		
	@Override
	@PostMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ContactResponse> create(@Valid @RequestBody ContactRequest request) {
		
		ContactResponse response = service.save(request);
		
		return ResponseEntity.ok(response);
	}
	
	@Override
	@PutMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ContactResponse> update(@PathVariable Long id, @Valid @RequestBody ContactRequest request) {
		
		ContactResponse response = service.update(id, request);
		
		return ResponseEntity.ok(response);
		
	}
	
	@Override
	@PutMapping(path = "/status/{contact_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ContactResponse> updateStatus(@PathVariable(name = "contact_id") Long idContact, @RequestParam Boolean status){
		
		ContactResponse response = service.updateStatus(idContact, status);
		
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
