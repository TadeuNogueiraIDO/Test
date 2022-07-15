package br.com.idolink.api.controller;

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
import br.com.idolink.api.controller.swagger.AccessReportControllerSwagger;
import br.com.idolink.api.dto.request.AccessReportRequest;
import br.com.idolink.api.dto.response.AccessReportResponse;
import br.com.idolink.api.dto.response.VisitorsClicksResponse;
import br.com.idolink.api.model.enums.ReportPeriod;
import br.com.idolink.api.service.AccessReportService;

@RestController
@RequestMapping("/dashboard")
public class AccessReportController  implements AccessReportControllerSwagger{
	
	@Autowired
	private AccessReportService service;
		
	@Autowired
	private IdolinkSecurity idoLinkSecurity;
	
	@CheckSecurity.User.PodeAcessar
	@PostMapping
	@Override
	public ResponseEntity<AccessReportResponse> create(@Valid @RequestBody AccessReportRequest request) {
		AccessReportResponse response = service.create(request);
		return ResponseEntity.ok(response);
	}
			
	@CheckSecurity.User.PodeAcessar
	@DeleteMapping(path = "/{id}")
	@Override
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping(path = "/clicks-visitors")
    @CheckSecurity.User.PodeAcessar
	public ResponseEntity<VisitorsClicksResponse> getVisitorsAndCliscks( String idoIds, @RequestParam ReportPeriod period) {
		Long userId = idoLinkSecurity.getUsuarioId();
		
		VisitorsClicksResponse response= service.getVisitorsAndCliscks(userId, service.validateIsNull(idoIds), period);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping(path = "/dashboard-send-email")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<VisitorsClicksResponse> ConditionEmailSend(Boolean sendEmail) {

		Long userId = idoLinkSecurity.getUsuarioId();

		return ResponseEntity.ok(service.canSendReportEmailWeek(userId, sendEmail));
	}
}
