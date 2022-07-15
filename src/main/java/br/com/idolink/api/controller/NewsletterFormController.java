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
import br.com.idolink.api.controller.swagger.NewsletterFormControllerSwagger;
import br.com.idolink.api.dto.request.ConfigNewsletterFormRequest;
import br.com.idolink.api.dto.request.NewsletterFormRequest;
import br.com.idolink.api.dto.response.ConfigNewsletterFormResponse;
import br.com.idolink.api.dto.response.NewsletterFormResponse;
import br.com.idolink.api.service.ConfigNewsletterFormService;
import br.com.idolink.api.service.NewsletterFormService;


@RestController
@RequestMapping("/newsletter-form")
public class NewsletterFormController implements NewsletterFormControllerSwagger {

	@Autowired
	public NewsletterFormService service;
	
	@Autowired
	public ConfigNewsletterFormService configService;
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}")
	public ResponseEntity<NewsletterFormResponse> findById(@PathVariable(name = "id") Long id) {
		NewsletterFormResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping(path = "/{config_newsletterForm_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<NewsletterFormResponse> create(@PathVariable(name = "config_newsletterForm_id") Long configNewsletterFormId, @Valid @RequestBody NewsletterFormRequest request) {
		NewsletterFormResponse response = service.create(configNewsletterFormId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/newsletter-list/{config_id}")
	public ResponseEntity <List<NewsletterFormResponse>> findByConfig(@PathVariable(name = "config_id") Long id) {
		List<NewsletterFormResponse> response = service.findByConfig(id);
		return ResponseEntity.ok(response);
	}
	
	//CONFIG METHODS
	
	@Override
	@GetMapping(path = "/config")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ConfigNewsletterFormResponse> findByIdo(Long idoId) {
		ConfigNewsletterFormResponse response = configService.findByIdo(idoId);
		return ResponseEntity.ok(response);
		
	}

	@Override
	@PostMapping(path = "/config/{ido_id}")
	public ResponseEntity<ConfigNewsletterFormResponse> createConfig(@PathVariable(name = "ido_id") Long idoId, @Valid @RequestBody ConfigNewsletterFormRequest request) {
		ConfigNewsletterFormResponse response = configService.save(idoId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping(path = "/config/{id}")
	public ResponseEntity<ConfigNewsletterFormResponse> updateConfig(@PathVariable(name = "id") Long id, @Valid @RequestBody ConfigNewsletterFormRequest request) {
		ConfigNewsletterFormResponse response = configService.update(id, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@GetMapping(path = "/config/{id}")
	public ResponseEntity<?> findConfigById(@PathVariable Long id) {
		ConfigNewsletterFormResponse response = configService.findById(id);
		return ResponseEntity.ok(response);
	}

	@Override
	@DeleteMapping(path = "/config/{id}")
	public ResponseEntity<Void> deleteConfig(@PathVariable(name = "id") Long id) {
		configService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
