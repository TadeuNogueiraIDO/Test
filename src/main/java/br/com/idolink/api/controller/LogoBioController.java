package br.com.idolink.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.LogoBioControllerSwagger;
import br.com.idolink.api.dto.request.LogoBioRequest;
import br.com.idolink.api.dto.response.LogoBioResponse;
import br.com.idolink.api.service.LogoBioService;

@RestController
@RequestMapping("/logo-bio")
public class LogoBioController implements LogoBioControllerSwagger{
	
	@Autowired
	private LogoBioService service;	
		
	@Override
	@GetMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<LogoBioResponse>findByIdo(@PathVariable(name = "ido_id") Long idoId) {
		LogoBioResponse response= service.findByIdo(idoId);
		return ResponseEntity.ok(response);			
	}

	@Override
	@PutMapping(path = "/ido/{ido_id}")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<LogoBioResponse>updateByIdo(@PathVariable(name = "ido_id")Long idoId,@RequestBody LogoBioRequest request) {
		LogoBioResponse response= service.updateByIdo(idoId, request);
		return ResponseEntity.ok(response);		
	}
		
}
	

