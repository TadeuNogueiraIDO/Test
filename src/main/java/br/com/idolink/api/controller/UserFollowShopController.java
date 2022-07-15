package br.com.idolink.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.UserFollowShopControllerSwagger;
import br.com.idolink.api.dto.response.ShopResponse;
import br.com.idolink.api.service.UserFollowShopService;

@RestController
@RequestMapping("/user-shop-follow")
public class UserFollowShopController implements UserFollowShopControllerSwagger{

	@Autowired
	private IdolinkSecurity security;
	
	@Autowired
	private UserFollowShopService service;
	
	@Override
	@GetMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<ShopResponse>> find(String name) {
		return ResponseEntity.ok(service.find(security.getUsuarioId(), name));
	}

	@Override
	@PostMapping
	@CheckSecurity.User.PodeAcessar	
	public ResponseEntity<ShopResponse> vinculate(Long shopId) {
		return ResponseEntity.ok(service.vinculate(security.getUsuarioId(), shopId));
	}
	
	@Override
	@DeleteMapping
	public ResponseEntity<ShopResponse> delete(Long shopId) {
		service.unlink(security.getUsuarioId(), shopId);
		return ResponseEntity.noContent().build();
	}

}
