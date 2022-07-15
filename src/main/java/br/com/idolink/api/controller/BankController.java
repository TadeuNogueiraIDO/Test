package br.com.idolink.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.controller.swagger.BankControllerSwagger;
import br.com.idolink.api.dto.response.BankResponse;
import br.com.idolink.api.service.BankService;

@RestController
@RequestMapping("bank")
public class BankController implements BankControllerSwagger{

	
	@Autowired
	private BankService service;
	
	@Override
	@GetMapping
	public ResponseEntity<List<BankResponse>> getAllBanks() {
		return ResponseEntity.ok(service.findAllBanks());
	}

	
}
