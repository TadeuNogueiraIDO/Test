package br.com.idolink.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.MenuOrderControllerSwagger;
import br.com.idolink.api.dto.response.MenuOrderResponse;
import br.com.idolink.api.filter.MenuOrderFilter;
import br.com.idolink.api.service.MenuOrderService;

@RestController
@RequestMapping("/order")
public class MenuOrderController implements MenuOrderControllerSwagger {

	@Autowired
	private IdolinkSecurity security;

	@Autowired
	private MenuOrderService service;

	@Override
	@GetMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<MenuOrderResponse>> findMenuOrder(MenuOrderFilter filter) {
		return ResponseEntity.ok(service.findMenuOrder(security.getUsuarioId(), filter));
	}

	

}
