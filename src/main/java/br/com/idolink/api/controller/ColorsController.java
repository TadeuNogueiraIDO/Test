package br.com.idolink.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.ColorsControllerSwagger;
import br.com.idolink.api.dto.response.ColorsResponse;
import br.com.idolink.api.service.ColorsService;

@RestController
@RequestMapping("/colors")
public class ColorsController implements ColorsControllerSwagger{

	@Autowired
	ColorsService service;
	
	@CheckSecurity.User.PodeAcessar
	@GetMapping
	public ResponseEntity<List <ColorsResponse>> findAll() {
		List <ColorsResponse> response = service.list();

		return ResponseEntity.ok(response);
	}
}
