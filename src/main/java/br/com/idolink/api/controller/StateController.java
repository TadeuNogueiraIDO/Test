package br.com.idolink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.StateControllerSwagger;
import br.com.idolink.api.dto.response.StateResponse;
import br.com.idolink.api.service.StateService;

@RestController
@RequestMapping("/state")
public class StateController implements StateControllerSwagger {

	@Autowired
	public StateService service;

	
	@CheckSecurity.User.PodeAcessar
	@GetMapping
	public ResponseEntity<Page<StateResponse>> findAll(Pageable pageable) {
		Page<StateResponse> response = service.list(pageable);

		return ResponseEntity.ok(response);
	}
}
