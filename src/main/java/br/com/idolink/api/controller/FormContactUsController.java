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
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.FormContactUsControllerSwagger;
import br.com.idolink.api.dto.request.ConfigContactUsRequest;
import br.com.idolink.api.dto.request.FormContactUsRequest;
import br.com.idolink.api.dto.response.ConfigContactUsResponse;
import br.com.idolink.api.dto.response.FormContactUsResponse;
import br.com.idolink.api.service.ConfigContactUsService;
import br.com.idolink.api.service.FormContactUsService;

@RestController
@RequestMapping("/contact-us")
public class FormContactUsController implements FormContactUsControllerSwagger {

	@Autowired
	public FormContactUsService service;
	
	@Autowired
	public ConfigContactUsService configService;
	
	@Autowired
	public IdolinkSecurity idoLinkSecurity;

	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
		FormContactUsResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping(path = "/{config_contact_id}")
	public ResponseEntity<?> create(@PathVariable(name = "config_contact_id") Long configContactId, @Valid @RequestBody FormContactUsRequest request) {
		FormContactUsResponse response = service.create(configContactId, request, idoLinkSecurity.getUsuarioId());
		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
		
	//CONFIG METHODS
	
	@Override
	@GetMapping(path = "/config")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ConfigContactUsResponse> findConfigContactUsByIdo(Long idoId) {
		ConfigContactUsResponse response = configService.findByIdo(idoId);
		return ResponseEntity.ok(response);
		
	}

	@Override
	@PostMapping(path = "/config/{ido_id}")
	public ResponseEntity<?> createConfig(@PathVariable(name = "ido_id") Long idoId, @Valid ConfigContactUsRequest request) {
		ConfigContactUsResponse response = configService.save(idoId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping(path = "/config/{id}")
	public ResponseEntity<?> updateConfig(@PathVariable(name = "id") Long id, @Valid ConfigContactUsRequest request) {
		ConfigContactUsResponse response = configService.update(id, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@GetMapping(path = "/config/{id}")
	public ResponseEntity<?> findConfigById(Long id) {
		ConfigContactUsResponse response = configService.findById(id);
		return ResponseEntity.ok(response);
	}

	@Override
	@DeleteMapping(path = "/config/{id}")
	public ResponseEntity<Void> deleteConfig(@PathVariable(name = "id")Long id) {
		configService.delete(id);
		return ResponseEntity.noContent().build();
	}
		

}