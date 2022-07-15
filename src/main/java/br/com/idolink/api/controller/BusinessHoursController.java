package br.com.idolink.api.controller;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.BusinessHourControllerSwagger;
import br.com.idolink.api.dto.request.BusinessHourRequest;
import br.com.idolink.api.dto.response.BusinessHourResponse;
import br.com.idolink.api.service.BusinessHourService;

@RestController
@RequestMapping("/business-hour")
public class BusinessHoursController implements BusinessHourControllerSwagger{
	
	@Autowired
	private BusinessHourService service;
	
	@CheckSecurity.User.PodeAcessar
	@GetMapping
	@Override
	public ResponseEntity<Page<BusinessHourResponse>> findAll(Pageable pageable) {
		
		Page<BusinessHourResponse> response = service.list(pageable);
		
		return ResponseEntity.ok(response);
	}

	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}")
	@Override
	public ResponseEntity<BusinessHourResponse> findById(@PathVariable(name = "id") Long id) {
		
		BusinessHourResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}
	
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/ido/{ido_id}")
	@Override
	public ResponseEntity<BusinessHourResponse> findByIdo(@PathVariable(name = "ido_id") Long id) {
		
		BusinessHourResponse response = service.findByIdo(id);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping(path = "/ido/{ido_id}")
	public ResponseEntity<BusinessHourResponse> create(@PathVariable(name = "ido_id") Long idoId, @Valid @RequestBody BusinessHourRequest request) {
		
		BusinessHourResponse response = service.create(idoId, request);
		return ResponseEntity.ok(response);
	}

	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{id}")
	@Override
	public ResponseEntity<BusinessHourResponse> update(@Valid @RequestBody BusinessHourRequest request,
			@PathVariable(name = "id") Long id) {
		
		BusinessHourResponse response = service.update(id, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@DeleteMapping(path = "/{id}") 
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
