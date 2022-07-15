package br.com.idolink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.GatewayIdoControllerSwagger;
import br.com.idolink.api.dto.response.GatewayIdoResponse;
import br.com.idolink.api.service.GatewayIdoService;

@RestController
@RequestMapping("gateway-ido")
public class GatewayIdoController implements GatewayIdoControllerSwagger{

	@Autowired
	private GatewayIdoService service;
	
	@Autowired
	private IdolinkSecurity security;
	
	
	@Override
	@GetMapping("/{walletId}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<GatewayIdoResponse> findGatewayId(@PathVariable Long walletId) {
		return ResponseEntity.ok(service.findGatewayId(security.getUsuarioId(), walletId));
	}


	@Override
	@PostMapping("/{walletId}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<GatewayIdoResponse> toWithdraw(Long walletId) {
		service.toWithdraw(security.getUsuarioId(), walletId);
		return ResponseEntity.ok().build();
	}

}
