package br.com.idolink.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.TimezoneControllerSwagger;
import br.com.idolink.api.dto.response.TimezoneResponse;
import br.com.idolink.api.service.TimezoneService;

@RestController
@RequestMapping("/timezone")
public class TimezoneController implements TimezoneControllerSwagger {

	@Autowired
	private TimezoneService service;

	@CheckSecurity.User.PodeAcessar
	@GetMapping
	public ResponseEntity<List<TimezoneResponse>> findAll() {
		
		List<TimezoneResponse> response = service.list();

		return ResponseEntity.ok(response);
	}
	
}
