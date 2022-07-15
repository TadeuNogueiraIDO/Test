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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.WalletControllerSwagger;
import br.com.idolink.api.dto.request.WalletRequest;
import br.com.idolink.api.dto.response.WalletDetailsResponse;
import br.com.idolink.api.dto.response.WalletGeneralResponse;
import br.com.idolink.api.dto.response.WalletResponse;
import br.com.idolink.api.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController implements WalletControllerSwagger{

	@Autowired
	private IdolinkSecurity security;
	
	@Autowired
	private WalletService service;
	
	@Override
	@PostMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<WalletDetailsResponse> createWallet(@Valid WalletRequest request, UriComponentsBuilder uri) {
		WalletDetailsResponse response = service.create(security.getUsuarioId(), request);

		return ResponseEntity.created(uri.path("/wallet/{id}").buildAndExpand(response.getId()).toUri()).body(response);
	}

	@Override
	@GetMapping("/details/{id}")
	public ResponseEntity<WalletDetailsResponse> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@Override
	@GetMapping
	public ResponseEntity<List<WalletResponse>> findAll() {
		return ResponseEntity.ok(service.findAll(security.getUsuarioId()));
	}

	@Override
	@GetMapping("/general")
	public ResponseEntity<WalletGeneralResponse> findWalletGeneral() {
		return ResponseEntity.ok(service.findGeneralWallet(security.getUsuarioId()));
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<WalletDetailsResponse> updateWallet(@PathVariable Long id, @RequestBody WalletRequest request) {
		
		return ResponseEntity.ok(service.update(id, request));
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<WalletDetailsResponse> deleteWallet(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
