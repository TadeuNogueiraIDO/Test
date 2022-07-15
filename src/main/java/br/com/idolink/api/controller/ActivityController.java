package br.com.idolink.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.ActivityControllerSwagger;
import br.com.idolink.api.dto.request.ActivityRequest;
import br.com.idolink.api.dto.response.ActivityFullResponse;
import br.com.idolink.api.dto.response.ActivityResponse;
import br.com.idolink.api.model.enums.TypeActivity;
import br.com.idolink.api.service.ActivityService;

@RestController
@RequestMapping("/activity")
public class ActivityController  implements ActivityControllerSwagger{
	
	@Autowired
	private ActivityService service;
	
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/activity")
	@Override
	public ResponseEntity<ActivityResponse> create(@Valid @RequestBody ActivityRequest request) {
		ActivityResponse response = service.create(request);
		return ResponseEntity.ok(response);
	}
	
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/activity/{id}")
	@Override
	public ResponseEntity<ActivityResponse> update(@Valid @RequestBody ActivityRequest request, @PathVariable(name = "id") Long id) {
		ActivityResponse response = service.update(id, request);
		return ResponseEntity.ok(response);
	
	}
	
	@CheckSecurity.User.PodeAcessar
	@DeleteMapping(path = "/activity/{id}")
	@Override
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{type_activity}")
	@Override
	public ResponseEntity<List<ActivityResponse>> findByTypeActivity(@PathVariable("type_activity") TypeActivity typeActivity) {
		List<ActivityResponse> response = service.listSegment(typeActivity);
		return ResponseEntity.ok(response);
	}
	
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/activity/{id}")
	@Override
	public ResponseEntity<ActivityResponse> findById(@PathVariable Long id) {
		ActivityResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}
	
	@CheckSecurity.User.PodeAcessar
	@GetMapping
	@Override
	public ResponseEntity<Page<ActivityFullResponse>> findAll(Pageable pageable) {
		Page<ActivityFullResponse> response = service.findAll(pageable);
		return ResponseEntity.ok(response);
	}
	
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/filter")
	@Override
	public ResponseEntity<Page<ActivityFullResponse>> findAllByName(@RequestParam(name = "name" , required = false) String name, Pageable pageable) {
		Page<ActivityFullResponse> response = service.findByFilterContainingIgnoreCase(name, pageable);
		return ResponseEntity.ok(response);
	}
	
}
