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
import br.com.idolink.api.controller.swagger.AppearanceButtonControllerSwagger;
import br.com.idolink.api.dto.request.AppearanceButtonRequest;
import br.com.idolink.api.dto.response.AppearanceButtonResponse;
import br.com.idolink.api.dto.response.ButtonShapeResponse;
import br.com.idolink.api.dto.response.ColorsResponse;
import br.com.idolink.api.dto.response.TextFontResponse;
import br.com.idolink.api.service.AppearanceButtonService;
import br.com.idolink.api.service.ButtonShapeService;
import br.com.idolink.api.service.ColorsService;
import br.com.idolink.api.service.PredefinedModelService;
import br.com.idolink.api.service.TextFontService;

@RestController
@RequestMapping("/appearance-button")
public class AppearanceButtonController implements AppearanceButtonControllerSwagger {

	@Autowired
	private AppearanceButtonService service;
	
	@Autowired
	private ButtonShapeService shapeService;
	
	@Autowired
	private TextFontService fontService;
	
	@Autowired
	private ColorsService colorService;
	
	@Autowired
	private PredefinedModelService predefinedModelService;
	
	@GetMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	@Override
	public ResponseEntity<AppearanceButtonResponse> findByIdo(@PathVariable(name = "ido_id") Long id) {
		AppearanceButtonResponse response = service.findByIdo(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping("/shapes")
	public ResponseEntity<List<ButtonShapeResponse>> findShapes() {
		List<ButtonShapeResponse> response = shapeService.list();

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
	public ResponseEntity<List<AppearanceButtonResponse>> findAll() {
		List<AppearanceButtonResponse> response = service.list();

		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}")
	public ResponseEntity<AppearanceButtonResponse> findById(@PathVariable(name = "id") Long id) {
		AppearanceButtonResponse response = service.findById(id);

		return ResponseEntity.ok(response);

	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/ido/{ido_id}")
	public ResponseEntity<AppearanceButtonResponse> create(@PathVariable(name = "ido_id") Long idoId, @Valid @RequestBody AppearanceButtonRequest request) {
		AppearanceButtonResponse response = service.create(idoId, request);

		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{id}")
	public ResponseEntity<AppearanceButtonResponse> update(@Valid @RequestBody AppearanceButtonRequest request,
			@PathVariable(name = "id") Long id) {
		AppearanceButtonResponse response = service.update(id, request);

		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/template/{ido_id}")
	public ResponseEntity<AppearanceButtonResponse> getAppearanceButtonTemplate(@PathVariable(name = "ido_id") Long id) {
		AppearanceButtonResponse response = predefinedModelService.getTemplateAppearanceButton(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/template/{ido_id}")
	public ResponseEntity<AppearanceButtonResponse> setAppearanceButtonTemplate(@PathVariable(name = "ido_id") Long id) {
		AppearanceButtonResponse response = predefinedModelService.setTemplateAppearanceButton(id);
		return ResponseEntity.ok(response);
	}
	
}
