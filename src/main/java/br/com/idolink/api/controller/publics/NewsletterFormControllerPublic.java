package br.com.idolink.api.controller.publics;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.controller.swagger.publics.NewsletterFormControllerSwaggerPublic;
import br.com.idolink.api.dto.request.NewsletterFormRequest;
import br.com.idolink.api.dto.response.NewsletterFormResponse;
import br.com.idolink.api.service.NewsletterFormService;


@RestController
@RequestMapping("/public/newsletter-form")
public class NewsletterFormControllerPublic implements NewsletterFormControllerSwaggerPublic {

	@Autowired
	public NewsletterFormService service;

	@Override
	@PostMapping(path = "/{config_newsletterForm_id}")
	public ResponseEntity<NewsletterFormResponse> create(@PathVariable(name = "config_newsletterForm_id") Long configNewsletterFormId, @Valid @RequestBody NewsletterFormRequest request ) {
		NewsletterFormResponse response = service.create(configNewsletterFormId, request);
		return ResponseEntity.ok(response);
	}
	
}
