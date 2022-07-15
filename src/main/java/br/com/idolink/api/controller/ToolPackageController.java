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

import br.com.idolink.api.controller.swagger.ToolPackageControllerSwagger;
import br.com.idolink.api.dto.request.ToolConfigPackageRequest;
import br.com.idolink.api.dto.response.ToolPlanResponse;
import br.com.idolink.api.service.ToolConfigPackageService;

@RestController
@RequestMapping("config-tool-package")
public class ToolPackageController implements ToolPackageControllerSwagger{

	@Autowired
	private ToolConfigPackageService service;
	
	@Override
	@PostMapping
	public ResponseEntity<ToolPlanResponse> create(@Valid @RequestBody ToolConfigPackageRequest request) {
		return ResponseEntity.ok(service.create(request));
	}

	@Override
	@GetMapping
	public ResponseEntity<List<ToolPlanResponse>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ToolPlanResponse> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<ToolPlanResponse> update(@Valid @RequestBody ToolConfigPackageRequest request, @PathVariable Long id) {
		return ResponseEntity.ok(service.update(request, id));
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<ToolPlanResponse> delete(Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}


}
