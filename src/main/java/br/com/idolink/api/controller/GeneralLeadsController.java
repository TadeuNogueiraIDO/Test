package br.com.idolink.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import br.com.idolink.api.controller.swagger.GeneralLeadsSwagger;
import br.com.idolink.api.dto.response.GeneralLeadsResponse;
import br.com.idolink.api.model.enums.StatusLeads;
import br.com.idolink.api.model.enums.TypeLeads;
import br.com.idolink.api.service.GeneralLeadsService;

@RestController
@RequestMapping("/general-leads")
public class GeneralLeadsController implements GeneralLeadsSwagger {
	
	@Autowired
	private GeneralLeadsService service;
	
	@Autowired
	private IdolinkSecurity idoLinkSecurity;
	

	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/file")
	public ResponseEntity <byte[]> generateFile(Boolean noDataLimit){
		HttpHeaders headers = new HttpHeaders();
		 headers.add("Content-Disposition", "attachment; filename=lead.csv");
				    headers.add("Content-Type", "xls/csv");
		
		return new ResponseEntity<>(service.generateLeads(idoLinkSecurity.getUsuarioId(), noDataLimit), headers,  HttpStatus.OK);
		
	}
	
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/user")
	public ResponseEntity<List<GeneralLeadsResponse>> listByUser(){
		
		return ResponseEntity.ok(service.listByUser(idoLinkSecurity.getUsuarioId()));
		
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{id_lead}")
	public ResponseEntity<List<GeneralLeadsResponse>> updateStatus(@RequestParam StatusLeads status, TypeLeads type, @PathVariable(name = "id_lead")String idLead){
		
		List<GeneralLeadsResponse> response = service.update(idoLinkSecurity.getUsuarioId(), idLead, type, status);
		
		return ResponseEntity.ok(response);
				
	}
	
	@Override
	@CheckSecurity
	@DeleteMapping(path = "/{idLead}")
	public  ResponseEntity<GeneralLeadsResponse> delete (@PathVariable(name = "idLead")Long idLead, @RequestParam TypeLeads type){
		
		service.deleteLeads(idoLinkSecurity.getUsuarioId(), type, idLead);
		
		return ResponseEntity.noContent().build();
		
		
	}
	
	
	
	
	
	
	
}

