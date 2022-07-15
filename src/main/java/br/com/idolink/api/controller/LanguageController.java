package br.com.idolink.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.LanguageControllerSwagger;
import br.com.idolink.api.dto.response.LanguageResponse;
import br.com.idolink.api.service.LanguageService;

@RestController
@RequestMapping("/languages")
public class LanguageController implements LanguageControllerSwagger {
	
	@Autowired
	private LanguageService languageService;

	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping
	public ResponseEntity<List<LanguageResponse>> findLanguages() {
		List<LanguageResponse> response = languageService.list();
		return ResponseEntity.ok(response);
	}

}
