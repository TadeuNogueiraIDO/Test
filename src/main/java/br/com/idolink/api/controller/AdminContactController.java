package br.com.idolink.api.controller;

import java.util.List;

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
import br.com.idolink.api.controller.swagger.AdminContactControllerSwagger;
import br.com.idolink.api.dto.request.AdminContactRequest;
import br.com.idolink.api.dto.response.AdminContactResponse;
import br.com.idolink.api.service.AdminContactService;

@RestController
@RequestMapping("/admin-contact")
public class AdminContactController implements AdminContactControllerSwagger{
	
	@Autowired
	private AdminContactService service;
	

	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping
	public ResponseEntity<List<AdminContactResponse>> findAll() {
		List<AdminContactResponse> response = service.list();

		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping
	public ResponseEntity<AdminContactResponse> create(@Valid @RequestBody AdminContactRequest request) {
		AdminContactResponse response = service.create(request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{id}")
	public ResponseEntity<AdminContactResponse> update(@Valid @RequestBody AdminContactRequest request,
			@PathVariable(name = "id") Long id) {
		AdminContactResponse response = service.update(id, request);

		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
