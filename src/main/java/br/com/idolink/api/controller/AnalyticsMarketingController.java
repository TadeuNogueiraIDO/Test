package br.com.idolink.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.AnalyticsMarketingControllerSwagger;
import br.com.idolink.api.dto.request.IdoAnalyticsMarketingRequestList;
import br.com.idolink.api.dto.response.AnalyticsMarketingResponse;
import br.com.idolink.api.dto.response.IdoAnalyticsMarketingResponse;
import br.com.idolink.api.service.AnalyticsMarketingService;
import br.com.idolink.api.service.IdoAnalyticsMarketingService;

@RestController
@RequestMapping("/analytics-marketing")
public class AnalyticsMarketingController implements AnalyticsMarketingControllerSwagger {
	
	@Autowired
	private IdoAnalyticsMarketingService service;
	
	@Autowired
	private AnalyticsMarketingService analyticsMarketingService;
	
	@Override
	@GetMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<AnalyticsMarketingResponse>> findAllAnalyticsMarketing(){
		List<AnalyticsMarketingResponse> responses = analyticsMarketingService.findAll();
		return ResponseEntity.ok(responses);
	}
	
	@Override
	@GetMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<IdoAnalyticsMarketingResponse>> findConfigAnalyticsMarketingByIdo(@RequestParam Long idoId){
		List<IdoAnalyticsMarketingResponse> responses = service.listByIdo(idoId);
		return ResponseEntity.ok(responses);
	}
	
	@Override
	@PutMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<IdoAnalyticsMarketingResponse>> configAnalyticsMarketing(@RequestParam(required = true, name = "ido_id") Long idoId, @RequestBody IdoAnalyticsMarketingRequestList request){
		List<IdoAnalyticsMarketingResponse> responses = service.createOrUpdate(idoId,request);
		return ResponseEntity.ok(responses);
	}
	
}
