package br.com.idolink.api.controller.publics;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.controller.swagger.publics.FormContactUsControllerSwaggerPublic;
import br.com.idolink.api.dto.request.FormContactUsRequest;
import br.com.idolink.api.dto.response.FormContactUsResponse;
import br.com.idolink.api.service.FormContactUsService;

@RestController
@RequestMapping("/public/contact-us")
public class FormContactUsControllerPublic implements FormContactUsControllerSwaggerPublic {

	@Autowired
	public FormContactUsService service;

	@Override
	@PostMapping(path = "/{ido_id}")
	public ResponseEntity<FormContactUsResponse> create(@PathVariable(name = "ido_id") Long idoId, @Valid @RequestBody FormContactUsRequest request) {
		FormContactUsResponse response = service.publicCreate(idoId, request);
		return ResponseEntity.ok(response);
	}

}