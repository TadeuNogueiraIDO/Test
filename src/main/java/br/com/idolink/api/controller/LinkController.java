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
import br.com.idolink.api.controller.swagger.LinkControllerSwagger;
import br.com.idolink.api.dto.request.LinkRequest;
import br.com.idolink.api.dto.response.LinkResponse;
import br.com.idolink.api.service.LinkService;

@RestController
@RequestMapping("/link")
public class LinkController implements LinkControllerSwagger {
	
	@Autowired
	private LinkService service;
	
	@Override
	@GetMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<LinkResponse>> findAllByIdo(@PathVariable(name = "ido_id") Long idIdo) {
		List<LinkResponse> response = service.listByIdo(idIdo);
		return ResponseEntity.ok(response);
	}
		
	@Override
	@PostMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<LinkResponse> create(@PathVariable(name = "ido_id") Long idoId, @Valid @RequestBody LinkRequest request) {
		LinkResponse response = service.create(idoId, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@PutMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<LinkResponse> update(@PathVariable Long id, @Valid @RequestBody LinkRequest request) {
		LinkResponse response = service.update(id, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@DeleteMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}")
	public ResponseEntity<LinkResponse> findById(@PathVariable(name = "id") Long id) {
		LinkResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}

}
