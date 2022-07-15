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
import br.com.idolink.api.controller.swagger.BannerPublicityControllerSwagger;
import br.com.idolink.api.dto.request.BannerPublicityRequest;
import br.com.idolink.api.dto.response.BannerPublicityResponse;
import br.com.idolink.api.service.BannerPublicityService;

@RestController
@RequestMapping("/banner-publicity")
public class BannerPublicityController  implements BannerPublicityControllerSwagger{
	
	@Autowired
	private BannerPublicityService service;
	
	@CheckSecurity.User.PodeAcessar
	@PostMapping
	@Override
	public ResponseEntity<BannerPublicityResponse> create(@Valid @RequestBody BannerPublicityRequest request) {
		BannerPublicityResponse response = service.create(request);
		return ResponseEntity.ok(response);
	}
	
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{id}")
	@Override
	public ResponseEntity<BannerPublicityResponse> update(@Valid @RequestBody BannerPublicityRequest request, @PathVariable(name = "id") Long id) {
		BannerPublicityResponse response = service.update(id, request);
		return ResponseEntity.ok(response);
	
	}
	
	@CheckSecurity.User.PodeAcessar
	@DeleteMapping(path = "/{id}")
	@Override
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
			
	@CheckSecurity.User.PodeAcessar
	@GetMapping
	@Override
	public ResponseEntity<BannerPublicityResponse> findCarrousel() {
		BannerPublicityResponse response = service.findCarousel();
		return ResponseEntity.ok(response);
	}
}
