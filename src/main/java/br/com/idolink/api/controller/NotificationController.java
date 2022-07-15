package br.com.idolink.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.NotificationControllerSwagger;
import br.com.idolink.api.dto.response.NotificationFilterResponse;
import br.com.idolink.api.dto.response.NotificationResponse;
import br.com.idolink.api.model.enums.NotificationType;
import br.com.idolink.api.service.NotificationService;


@RestController
@RequestMapping("/notification")
public class NotificationController implements NotificationControllerSwagger {

	@Autowired
	private NotificationService service;

	@Autowired
	private IdolinkSecurity idoLinkSecurity;
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/user")
	public ResponseEntity<List<NotificationFilterResponse>> listByUserLogged() {
		
		List<NotificationFilterResponse> response = service.findByUserByDate(idoLinkSecurity.getUsuarioId());

		return ResponseEntity.ok(response);
	}	
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/type")
	public ResponseEntity<List<NotificationResponse>>listByNotificationBytype(@RequestParam  NotificationType type){
		
		List<NotificationResponse> response = service.findAllbyIdType(type,idoLinkSecurity.getUsuarioId());
		
		return ResponseEntity.ok(response);
		
	}
	
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping
	public ResponseEntity<List<NotificationResponse>> findAllNotification() {
		
		List<NotificationResponse> response = service.list();

		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@DeleteMapping(path = "/user/{id_user}")
	public ResponseEntity<Void> deleteAll(@PathVariable(name = "id_user") Long userId) {

		service.deleteAll(userId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{status}")
	public ResponseEntity<List<NotificationResponse>> updatRead (@PathVariable(name = "status") Boolean read, String idsNotifications){
		
		List<NotificationResponse> response = service.update(idoLinkSecurity.getUsuarioId(), read, idsNotifications);
		
		return ResponseEntity.ok(response);
		
		
		
	}
	
	
	



}
