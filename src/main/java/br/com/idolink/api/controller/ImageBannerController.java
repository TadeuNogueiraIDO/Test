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
import br.com.idolink.api.controller.swagger.ImageBannerControllerSwagger;
import br.com.idolink.api.dto.request.ImageBannerRequest;
import br.com.idolink.api.dto.response.ImageBannerResponse;
import br.com.idolink.api.service.ImageBannerService;


@RestController
@RequestMapping("/image-banner")
public class ImageBannerController implements ImageBannerControllerSwagger{

	@Autowired
	private ImageBannerService service;
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}")
	public ResponseEntity<ImageBannerResponse> findById(@PathVariable(name = "id") Long id) {
		ImageBannerResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/ido/{ido_id}")
	public ResponseEntity<List<ImageBannerResponse>> findByIdo(@PathVariable(name = "ido_id") Long id) {
		List<ImageBannerResponse> response = service.findByIdo(id);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping(path = "/ido/{ido_id}")
	public ResponseEntity<ImageBannerResponse> create(@PathVariable(name = "ido_id") Long idoId, @Valid @RequestBody ImageBannerRequest request) {
		ImageBannerResponse response = service.save(idoId, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@PutMapping(path = "/{id}")
	public ResponseEntity<ImageBannerResponse> update(@Valid @RequestBody ImageBannerRequest request, @PathVariable("id") Long id) {
		ImageBannerResponse response = service.update(request, id);
		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
