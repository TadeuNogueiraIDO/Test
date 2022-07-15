package br.com.idolink.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.ShopDashboardControllerSwagger;
import br.com.idolink.api.dto.response.ShopDashboardResponse;
import br.com.idolink.api.filter.ShopDashboardFilter;
import br.com.idolink.api.service.ShopDashboardService;

@RestController
@RequestMapping("/shop-dashboard")
public class ShopDashboardController implements ShopDashboardControllerSwagger{

	
	@Autowired
	private IdolinkSecurity security;
	
	@Autowired
	private ShopDashboardService service;
	
	@Override
	@GetMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ShopDashboardResponse> find(@Valid ShopDashboardFilter filter) {
		return ResponseEntity.ok(service.find(security.getUsuarioId(), filter));
	}
	
	@Override
	@PutMapping(path = "/dashboard-send-email-month")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ShopDashboardResponse> conditionEmailSendMonth(boolean sendEmail) {
		
		return ResponseEntity.ok(service.canSendReportEmailMonth(security.getUsuarioId(), sendEmail));
	}
	
}
