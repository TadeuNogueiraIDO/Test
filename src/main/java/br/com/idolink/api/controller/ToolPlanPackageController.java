package br.com.idolink.api.controller;

import java.util.List;

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
import br.com.idolink.api.controller.swagger.ToolPlanPackageControllerSwagger;
import br.com.idolink.api.dto.request.ToolPlanPackagesRequest;
import br.com.idolink.api.dto.response.ToolPlanPackagesResponse;
import br.com.idolink.api.model.enums.ToolPlanPackageType;
import br.com.idolink.api.service.ToolPlanPackageService;

@RestController
@RequestMapping("tool-plan-package")
public class ToolPlanPackageController implements ToolPlanPackageControllerSwagger{

	@Autowired
	private ToolPlanPackageService service;
	

	@Override
	@GetMapping("/type")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ToolPlanPackagesResponse> findToolPlanPackage(@RequestParam(required = true) ToolPlanPackageType toolType) {
		return ResponseEntity.ok(service.findToolPlanPackage(toolType));
	}
	
	@Override
	@GetMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<ToolPlanPackagesResponse>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@PostMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ToolPlanPackagesResponse> create(@Valid @RequestBody ToolPlanPackagesRequest request) {
		return ResponseEntity.ok(service.create(request));
	}

	@Override
	@PutMapping("/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ToolPlanPackagesResponse> update(@Valid @RequestBody ToolPlanPackagesRequest request, @PathVariable Long id) {
		return ResponseEntity.ok(service.update(request, id));
	}

}
