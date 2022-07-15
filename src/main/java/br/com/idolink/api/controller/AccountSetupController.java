package br.com.idolink.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.AccountSetupControllerSwagger;
import br.com.idolink.api.dto.request.NotificationSettingRequest;
import br.com.idolink.api.dto.response.NotificationSettingResponse;
import br.com.idolink.api.dto.response.UserResponse;
import br.com.idolink.api.service.NotificationSettingService;
import br.com.idolink.api.service.UserService;

@RestController
@RequestMapping("/account-setup")
public class AccountSetupController implements AccountSetupControllerSwagger {
		@Autowired
	public UserService userService;
	
	@Autowired
	private  NotificationSettingService notificationSettingService;
	
	@Autowired
	public IdolinkSecurity idoLinkSecurity;
			
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{id}")
	public ResponseEntity<NotificationSettingResponse> update(@PathVariable(name = "id") Long id, @Valid @RequestBody NotificationSettingRequest request) {
		NotificationSettingResponse response = notificationSettingService.update(id, request);

		return ResponseEntity.ok(response);
	}
	
	@CheckSecurity.User.PodeAcessar
	@DeleteMapping(path ="/logged")
	public ResponseEntity<UserResponse> deleteAccount() {
		
		Long loggeduser = idoLinkSecurity.getUsuarioId();
		userService.delete(loggeduser);
		return ResponseEntity.noContent().build();
	}
}
