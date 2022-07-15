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
import br.com.idolink.api.controller.swagger.VideoBannerControllerSwagger;
import br.com.idolink.api.dto.request.VideoBannerRequest;
import br.com.idolink.api.dto.response.VideoBannerResponse;
import br.com.idolink.api.service.VideoBannerService;

@RestController
@RequestMapping("/video-banner")
public class VideoBannerController implements VideoBannerControllerSwagger {

	@Autowired
	private VideoBannerService service;
	
	@GetMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	@Override
	public ResponseEntity<VideoBannerResponse> findById(@PathVariable(name = "id") Long id) {
		VideoBannerResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	@Override
	public ResponseEntity<List<VideoBannerResponse>> findByIdo(@PathVariable(name = "ido_id") Long id) {
		List<VideoBannerResponse> response = service.findByIdo(id);
		return ResponseEntity.ok(response);
	}

	@PostMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	@Override
	public ResponseEntity<VideoBannerResponse> create(@PathVariable(name = "ido_id") Long idIdo, @Valid @RequestBody VideoBannerRequest request) {
		VideoBannerResponse response = service.save(idIdo, request);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	@CheckSecurity.User.PodeAcessar
	@Override
	public ResponseEntity<VideoBannerResponse> update(@Valid @RequestBody VideoBannerRequest request, @PathVariable(name = "id")Long id) {
		VideoBannerResponse response = service.update(request, id);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	@Override
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	
}
