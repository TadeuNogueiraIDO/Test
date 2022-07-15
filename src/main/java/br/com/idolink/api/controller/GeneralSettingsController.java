//package br.com.idolink.api.controller;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.idolink.api.annotation.CheckSecurity;
//import br.com.idolink.api.config.security.IdolinkSecurity;
//import br.com.idolink.api.controller.swagger.GeneralSettingsControllerSwagger;
//import br.com.idolink.api.dto.request.GeneralSettingsRequest;
//import br.com.idolink.api.dto.request.UserStatusRequest;
//import br.com.idolink.api.dto.response.GeneralSettingsResponse;
//import br.com.idolink.api.dto.response.LanguageResponse;
//import br.com.idolink.api.dto.response.UserStatusResponse;
//import br.com.idolink.api.service.GeneralSettingsService;
//import br.com.idolink.api.service.LanguageService;
//
//@RestController
//@RequestMapping("/general-settings")
//public class GeneralSettingsController implements GeneralSettingsControllerSwagger {
//
//	@Autowired
//	private GeneralSettingsService service;
//
//	
//	@Autowired
//	private LanguageService languageService;
//
//	@Autowired
//	public IdolinkSecurity idoLinkSecurity;
//
//	@Override
//	@CheckSecurity.User.PodeAcessar
//	@GetMapping("/languages")
//	public ResponseEntity<List<LanguageResponse>> findLanguages() {
//		List<LanguageResponse> response = languageService.list();
//
//		return ResponseEntity.ok(response);
//	}
//
//	@Override
//	@CheckSecurity.User.PodeAcessar
//	@PutMapping(path = "/{id}")
//	public ResponseEntity<GeneralSettingsResponse> update(@Valid @RequestBody GeneralSettingsRequest request,
//			@PathVariable(name = "id") Long id) {
//		GeneralSettingsResponse response = service.update(id, request);
//
//		return ResponseEntity.ok(response);
//	}
//
//	@Override
//	@CheckSecurity.User.PodeAcessar
//	@PutMapping(path = "status/{id}")
//	public ResponseEntity<UserStatusResponse> updateStatus(@Valid @RequestBody UserStatusRequest request,
//			@PathVariable(name = "id") Long id) {
//		UserStatusResponse response = service.updateStatus(id, request);
//
//		return ResponseEntity.ok(response);
//	}
//		
//}
