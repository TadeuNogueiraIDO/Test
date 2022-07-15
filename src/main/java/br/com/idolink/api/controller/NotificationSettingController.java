package br.com.idolink.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.NotificationSettingControllerSwagger;
import br.com.idolink.api.dto.request.NotificationSettingRequest;
import br.com.idolink.api.dto.response.NotificationSettingResponse;
import br.com.idolink.api.service.NotificationSettingService;

@RestController
@RequestMapping("/notification-setting")
public class NotificationSettingController implements NotificationSettingControllerSwagger {

	@Autowired
	private  NotificationSettingService service;
	
	@Autowired
	private IdolinkSecurity idoLinkSecurity;

	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping
	public ResponseEntity<NotificationSettingResponse> listByNotificationStettingUserLogged(){
		
		NotificationSettingResponse response = service.listByNotificationStettingUserLogged(idoLinkSecurity.getUsuarioId());

		return ResponseEntity.ok(response);
	}
	
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{id}")
	public ResponseEntity<NotificationSettingResponse> update(@PathVariable(name = "id") Long id, @Valid @RequestBody NotificationSettingRequest request) {
		NotificationSettingResponse response = service.update(id, request);

		return ResponseEntity.ok(response);
	}

}
