package br.com.idolink.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.UserPlanPackageControllerSwagger;
import br.com.idolink.api.dto.request.IdoPlanPackageRequest;
import br.com.idolink.api.dto.response.IdoPlanPackageResponse;
import br.com.idolink.api.service.UserPlanPackageService;

@RestController
@RequestMapping("/tool-plan-package/ido")
public class UserPlanPackageController implements UserPlanPackageControllerSwagger{
	
	@Autowired
	private IdolinkSecurity security;

	@Autowired
	private UserPlanPackageService service;
	
	@Override
	@PostMapping("{ido_id}/vinculate")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<IdoPlanPackageResponse> create(@Valid @RequestBody IdoPlanPackageRequest request, @PathVariable(name = "ido_id") Long idoId,
			UriComponentsBuilder uri) {
		IdoPlanPackageResponse response = service.create(request, idoId);
		return ResponseEntity.created(uri.path("/tool-plan-package/user").buildAndExpand(response).toUri()).build();
	}

	@Override
	@GetMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<IdoPlanPackageResponse>> findAll(String idoId) {
		return ResponseEntity.ok(service.findAll(security.getUsuarioId(), idoId));
	}
	
	

	@Override
	@DeleteMapping("/unlink")
	public ResponseEntity<IdoPlanPackageResponse> delete(Long planId, Long idoId) {
		service.delete(planId, idoId);
		return ResponseEntity.noContent().build();
	}

}
