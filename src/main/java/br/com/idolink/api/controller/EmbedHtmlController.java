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

import br.com.idolink.api.controller.swagger.EmbedHtmlControllerSwagger;
import br.com.idolink.api.dto.request.EmbedHtmlRequest;
import br.com.idolink.api.dto.response.EmbedHtmlResponse;
import br.com.idolink.api.service.EmbedHtmlService;

@RestController
@RequestMapping("/embed-html")
public class EmbedHtmlController implements EmbedHtmlControllerSwagger {
	
	@Autowired
	private EmbedHtmlService service;
	
	@Override
	@GetMapping(path = "/ido/{ido_id}")
	//@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<EmbedHtmlResponse>> findAllByIdo(@PathVariable(name = "ido_id") Long idIdo) {
		List<EmbedHtmlResponse> response = service.listByIdo(idIdo);
		return ResponseEntity.ok(response);
	}
		
	@Override
	@PostMapping(path = "/ido/{ido_id}")
	//@CheckSecurity.User.PodeAcessar
	public ResponseEntity<EmbedHtmlResponse> create(@PathVariable(name = "ido_id") Long idoId, @Valid @RequestBody EmbedHtmlRequest request) {
		EmbedHtmlResponse response = service.create(idoId, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@PutMapping(path = "/{id}")
	//@CheckSecurity.User.PodeAcessar
	public ResponseEntity<EmbedHtmlResponse> update(@PathVariable Long id, @Valid @RequestBody EmbedHtmlRequest request) {
		EmbedHtmlResponse response = service.update(id, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@DeleteMapping(path = "/{id}")
	//@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	//@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}")
	public ResponseEntity<EmbedHtmlResponse> findById(@PathVariable(name = "id") Long id) {
		EmbedHtmlResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}

}
