package br.com.idolink.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.IdoToolPositionControllerSwagger;
import br.com.idolink.api.dto.request.IdoToolPositionRequest;
import br.com.idolink.api.dto.response.IdoToolPositionResponse;
import br.com.idolink.api.service.IdoToolPositionService;

@RestController
@RequestMapping("/reposition-tool")
public class IdoToolPositionController implements IdoToolPositionControllerSwagger {

	@Autowired
	private IdoToolPositionService service;
	
	@Override
	@GetMapping(path = "/{ido_id}")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<IdoToolPositionResponse>> findByIdo(@PathVariable(name = "ido_id") Long idoId) {
		List<IdoToolPositionResponse> response= service.findIdoToolPositionByIdo(idoId);
		return ResponseEntity.ok(response);			
	}

	@Override
	@PutMapping(path = "/{ido_id}")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<IdoToolPositionResponse>> update(@RequestBody List<IdoToolPositionRequest> requestList,
			Long idoId) {
		List<IdoToolPositionResponse> response= service.updateAllPositions(requestList, idoId);
		return ResponseEntity.ok(response);		
	}
	
}
