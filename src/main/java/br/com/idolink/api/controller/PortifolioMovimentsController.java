package br.com.idolink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.PortifolioMovementsControllerSwagger;
import br.com.idolink.api.dto.response.PortifolioMovimentationGeneralResponse;
import br.com.idolink.api.filter.PortifolioMovimentationFilter;
import br.com.idolink.api.service.PortifolioMovimentsService;

@RestController
@RequestMapping("portifolio-moviments")
public class PortifolioMovimentsController implements PortifolioMovementsControllerSwagger{

	@Autowired
	private PortifolioMovimentsService service;
	
	@Autowired
	private IdolinkSecurity security;
	
	@Override
	@GetMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<PortifolioMovimentationGeneralResponse> findPortifolioMovements(
			PortifolioMovimentationFilter filter) {
		return ResponseEntity.ok(service.findPortifolioMovements(filter, security.getUsuarioId()));
	}

}
