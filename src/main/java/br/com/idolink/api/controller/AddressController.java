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
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.AddressControllerSwagger;
import br.com.idolink.api.dto.request.AddressRequest;
import br.com.idolink.api.dto.response.AddressResponse;
import br.com.idolink.api.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController implements AddressControllerSwagger {

	@Autowired
	private AddressService service;
	
	@Autowired
	private IdolinkSecurity idoLinkSecurity;
	
	
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping
	public ResponseEntity<List<AddressResponse>> findAll() {
		List<AddressResponse> response = service.list();

		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path ="/logged-user-address")
	public ResponseEntity<List<AddressResponse>> listByaddressUserLogged(){
		
		List<AddressResponse> response = service.listByaddressUserLogged(idoLinkSecurity.getUsuarioId());

		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}")
	public ResponseEntity<AddressResponse> findById(@PathVariable(name = "id") Long id) {
		AddressResponse response = service.findById(id);
		return ResponseEntity.ok(response);

	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/user")
	public ResponseEntity<AddressResponse>create( @Valid @RequestBody AddressRequest request) {
		AddressResponse response = service.create(idoLinkSecurity.getUsuarioId(), request);
		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{id}")
	public ResponseEntity<AddressResponse> update(@Valid @RequestBody AddressRequest request,
			@PathVariable(name = "id") Long id) {
		AddressResponse response = service.update(id, request);

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
