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
import br.com.idolink.api.controller.swagger.ImageCarouselControllerSwagger;
import br.com.idolink.api.dto.request.ImageCarouselRequest;
import br.com.idolink.api.dto.response.ImageCarouselResponse;
import br.com.idolink.api.service.ImageCarouselService;

@RestController
@RequestMapping("/image-carousel")
public class ImageCarouselController implements ImageCarouselControllerSwagger{

	@Autowired
	private ImageCarouselService service;
	
	@Override
	@GetMapping("/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ImageCarouselResponse> findById(Long id) {
		ImageCarouselResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}

	@Override
	@GetMapping("/ido/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<ImageCarouselResponse>> findByIdo(Long id) {
		List<ImageCarouselResponse> response = service.findByIdo(id);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping("/ido/{ido}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ImageCarouselResponse> create(@PathVariable(name = "ido") Long idoId, @Valid @RequestBody ImageCarouselRequest request) {
		ImageCarouselResponse response = service.create(idoId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping("/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ImageCarouselResponse> update(@RequestBody ImageCarouselRequest request, @PathVariable(name ="id") Long id) {
		ImageCarouselResponse response = service.update(id, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@DeleteMapping("/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<?> delete(@PathVariable(name ="id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
