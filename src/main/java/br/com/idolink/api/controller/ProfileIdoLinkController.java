package br.com.idolink.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.ProfileIdoLinkControllerSwagger;
import br.com.idolink.api.dto.request.ProfileIdoLinkRequest;
import br.com.idolink.api.dto.response.ProfileIdoLinkResponse;
import br.com.idolink.api.model.enums.ProfileIdoCodName;
import br.com.idolink.api.service.ProfileIdoLinkService;

@RestController
@RequestMapping("/profile-ido-link")
public class ProfileIdoLinkController implements ProfileIdoLinkControllerSwagger {
	
	@Autowired
	private  ProfileIdoLinkService service;
	
	@Override
	@GetMapping(path="/cod_name")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<ProfileIdoLinkResponse> findByCodName(@RequestParam ProfileIdoCodName codName) {
		
		ProfileIdoLinkResponse response = service.findByCodName(codName);
		
		return ResponseEntity.ok(response);

	}
		
	@Override
	@PostMapping
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<ProfileIdoLinkResponse> create(@Valid @RequestBody ProfileIdoLinkRequest request) {

		ProfileIdoLinkResponse response = service.create(request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping(path = "/{id}")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<ProfileIdoLinkResponse> update(@PathVariable Long id, @Valid @RequestBody ProfileIdoLinkRequest request) {

		ProfileIdoLinkResponse response = service.update(id, request);
		return ResponseEntity.ok(response);

	}
	

}
