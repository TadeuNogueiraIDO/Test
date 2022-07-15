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
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.AttachedPdfControllerSwagger;
import br.com.idolink.api.dto.request.AttachedPdfLeadsRequest;
import br.com.idolink.api.dto.request.AttachedPdfRequest;
import br.com.idolink.api.dto.response.AttachedPdfLeadsResponse;
import br.com.idolink.api.dto.response.AttachedPdfResponse;
import br.com.idolink.api.service.AttachedPdfService;

@RestController
@RequestMapping("/attached-pdf")
public class AttachedPdfController implements AttachedPdfControllerSwagger {
	
	@Autowired
	private AttachedPdfService service;
	
	@Autowired
	public IdolinkSecurity idoLinkSecurity;

	
	@Override
	@GetMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<AttachedPdfResponse>> getAttachedPdfByIdo(@PathVariable(name = "ido_id") Long idoId) {
		List<AttachedPdfResponse> response = service.findByIdo(idoId);
		return ResponseEntity.ok(response);
	}
		
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
		AttachedPdfResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}
	
	
	@Override
	@PostMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<AttachedPdfResponse> create(@PathVariable(name = "ido_id") Long idoId, @Valid @RequestBody AttachedPdfRequest request) {
		AttachedPdfResponse response = service.save(idoId, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@PutMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<AttachedPdfResponse> update(@PathVariable Long id, @Valid @RequestBody AttachedPdfRequest request) {
		AttachedPdfResponse response = service.update(id, request);
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
	@PostMapping(path = "/leads/{pdf_id}")
	public ResponseEntity<AttachedPdfLeadsResponse> saveLeads(@PathVariable(name = "pdf_id") Long idPdf, @Valid @RequestBody AttachedPdfLeadsRequest request){
		AttachedPdfLeadsResponse response = service.saveLeads(idPdf, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/leads/{id}")
	public ResponseEntity<?> findLeadsById(@PathVariable(name = "id") Long id) {
		AttachedPdfLeadsResponse response = service.findLeadsById(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping(path = "/leads/pdf/{pdf_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<AttachedPdfLeadsResponse>> findLeadsByAttachedPdf(@RequestParam(value="pdf_id", required=true) Long pdfId ) {
		List<AttachedPdfLeadsResponse> response = service.findLeadsByAttachedPdf(pdfId);
		return ResponseEntity.ok(response);
		
	}
	
	
	
}
