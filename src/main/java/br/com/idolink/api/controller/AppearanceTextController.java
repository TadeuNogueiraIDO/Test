package br.com.idolink.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.AppearanceTextControllerSwagger;
import br.com.idolink.api.dto.request.AppearanceTextRequest;
import br.com.idolink.api.dto.response.AppearanceTextResponse;
import br.com.idolink.api.dto.response.ColorsResponse;
import br.com.idolink.api.dto.response.TextFontResponse;
import br.com.idolink.api.service.AppearanceTextService;
import br.com.idolink.api.service.ColorsService;
import br.com.idolink.api.service.PredefinedModelService;
import br.com.idolink.api.service.TextFontService;

@RestController
@RequestMapping("/appearance-text")
public class AppearanceTextController implements AppearanceTextControllerSwagger {

	@Autowired
	private AppearanceTextService service;
	
	@Autowired
	private TextFontService fontService;
	
	@Autowired
	private ColorsService colorService;
	
	@Autowired
	private PredefinedModelService predefinedModelService;
	
	@GetMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	@Override
	public ResponseEntity<AppearanceTextResponse> findByIdo(@PathVariable(name = "ido_id") Long id) {
		AppearanceTextResponse response = service.findByIdo(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping("/fonts")
	public ResponseEntity<List<TextFontResponse>> findFonts() {
		List<TextFontResponse> response = fontService.list();

		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping("/colors")
	public ResponseEntity<List<ColorsResponse>> findColors() {
		List<ColorsResponse> response = colorService.list();

		return ResponseEntity.ok(response);
	}
	
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping
	public ResponseEntity<List<AppearanceTextResponse>> findAll() {
		List<AppearanceTextResponse> response = service.list();

		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/ido/{ido_id}")
	public ResponseEntity<AppearanceTextResponse> create(@PathVariable(name = "ido_id") Long idoId, @Valid @RequestBody AppearanceTextRequest request) {
		AppearanceTextResponse response = service.create(idoId, request);

		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{id}")
	public ResponseEntity<AppearanceTextResponse> update(@Valid @RequestBody AppearanceTextRequest request,
			@PathVariable(name = "id") Long id) {
		AppearanceTextResponse response = service.update(id, request);

		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/template/{ido_id}")
	public ResponseEntity<AppearanceTextResponse> getAppearanceTextTemplate(@PathVariable(name = "ido_id") Long idoId) {
		AppearanceTextResponse response = predefinedModelService.getTemplateAppearanceText(idoId);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/template/{ido_id}")
	public ResponseEntity<AppearanceTextResponse> setAppearanceTextTemplate(@PathVariable(name = "ido_id") Long id) {
		AppearanceTextResponse response = predefinedModelService.setTemplateAppearanceText(id);
		return ResponseEntity.ok(response);
	}
}
