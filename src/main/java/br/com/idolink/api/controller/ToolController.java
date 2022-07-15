package br.com.idolink.api.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.ToolControllerSwagger;
import br.com.idolink.api.dto.request.IdoToolRequest;
import br.com.idolink.api.dto.request.ToolActivatedRequest;
import br.com.idolink.api.dto.request.ToolRequest;
import br.com.idolink.api.dto.response.CurrencyResponse;
import br.com.idolink.api.dto.response.IdoToolResponse;
import br.com.idolink.api.dto.response.ToolActivatedResponse;
import br.com.idolink.api.dto.response.ToolResponse;
import br.com.idolink.api.dto.response.ToolStatusListResponse;
import br.com.idolink.api.filter.ToolFilter;
import br.com.idolink.api.model.enums.IdoToolStatus;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.service.CurrencyService;
import br.com.idolink.api.service.IdoToolService;
import br.com.idolink.api.service.ToolService;

@RestController
@RequestMapping("/tool")
public class ToolController implements ToolControllerSwagger {

	@Autowired
	private ToolService service;
	
	@Autowired
	private IdoToolService idoToolService;
	
	@Autowired
	private CurrencyService toolCoinService;
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping("/coins")
	public ResponseEntity<List<CurrencyResponse>> findCoins() {
		List<CurrencyResponse> response = toolCoinService.list();

		return ResponseEntity.ok(response);
	}
	


	@Override
	@GetMapping
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<ToolResponse>> findAll(ToolFilter filter) {

		List<ToolResponse> response = service.list(filter);
		return ResponseEntity.ok(response);

	}
	
	@Override
	@GetMapping(path = "/{id}")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<ToolResponse> findById(@PathVariable Long id) {
		
		ToolResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<ToolResponse> create(@Valid @RequestBody ToolRequest request) {

		ToolResponse response = service.create(request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping(path = "/{id}")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<ToolResponse> update(@PathVariable Long id, @Valid @RequestBody ToolRequest request) {

		ToolResponse response = service.update(id, request);
		
		return ResponseEntity.ok(response);

	}
	

	@Override
	@PutMapping(path = "/cod_name/{cod_name}/id/{id}")
    @CheckSecurity.User.PodeAcessar
	public  ResponseEntity<ToolActivatedResponse> enableDisableTool(@PathVariable("cod_name") ToolCodName codName,  @PathVariable(name = "id") Long id,@Valid @RequestBody ToolActivatedRequest activatedRequest) {
		
		ToolActivatedResponse response =service.enableDisableTool(codName, id, activatedRequest);
		
		return ResponseEntity.ok(response);		
	}

	@Override
	@DeleteMapping(path = "/{id}") 
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@Override
	@GetMapping(path = "/app-version") 
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<String> isSuportedTools(@RequestParam String appVersion, @RequestParam String toolsVersion) {
		Boolean verify = service.isSuportedTools(appVersion,toolsVersion);
		return ResponseEntity.ok(verify.toString());
	}
	
	
	//IDO_TOOLS METHODS
	
	@Override
	@GetMapping(path="/ido-tools/{ido_id}")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<ToolStatusListResponse> findAllIdoTools(@PathVariable(name = "ido_id") Long idoId, ToolFilter filter) {
		ToolStatusListResponse response = idoToolService.findAllByIdo(idoId, filter);
		return ResponseEntity.ok(response);

	}
	
	@Override
	@PostMapping(path="/ido-tools/{ido_id}")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<IdoToolResponse> createIdoTool(@PathVariable(name = "ido_id") Long idoId, IdoToolRequest request ) {
		IdoToolResponse response = idoToolService.create(idoId, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping(path="/ido-tools/id/{id}")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<IdoToolResponse> findIdoToolById(@PathVariable(name = "id") Long id) {
		IdoToolResponse response = idoToolService.findById(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@PutMapping(path="/ido-tools/id/{id}")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<IdoToolResponse> updateIdoTool(@PathVariable Long id, @Valid @RequestParam IdoToolStatus status) {
		IdoToolResponse response = idoToolService.update(id, status);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping(path="/ido-tools/{ido_id}/{tool_id}")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<ToolStatusListResponse> findByIdoAndTools(@PathVariable(name = "ido_id") Long idoId, @PathVariable(name = "tool_id") Long toolId ) {
		ToolStatusListResponse response = idoToolService.findByIdoAndTool(idoId, toolId);
		return ResponseEntity.ok(response);

	}
	
	@PostMapping("/associate-tool")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> associateTool(ToolCodName toolCodName, Long idoId){
		idoToolService.saveAssociateTool(toolCodName, idoId);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/avaliable-associate-tool")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> avaliableAssociateIdoTool(ToolCodName toolCodName, Long idoId){
		idoToolService.avaliableAssociateIdoTool(toolCodName, idoId);
		return ResponseEntity.ok().build();
	}
	
}
