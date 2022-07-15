package br.com.idolink.api.controller.publics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.controller.swagger.publics.CountryControllerSwaggerPublic;
import br.com.idolink.api.dto.response.CountryResponse;
import br.com.idolink.api.service.CountryService;

@RestController
@RequestMapping("/public/country")
public class CountryControllerPublic implements CountryControllerSwaggerPublic{
	
	@Autowired
	CountryService service;
	
	@Override
	@GetMapping
	public ResponseEntity<List<CountryResponse>> findAll() {
		List<CountryResponse> response = service.listByBraEuaCad();
		return ResponseEntity.ok(response);
	}

}
