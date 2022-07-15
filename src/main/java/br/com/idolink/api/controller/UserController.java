package br.com.idolink.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
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
import br.com.idolink.api.controller.swagger.UserControllerSwagger;
import br.com.idolink.api.dto.request.GeneralSettingsRequest;
import br.com.idolink.api.dto.request.PasswordRecoveryRequest;
import br.com.idolink.api.dto.request.PersonalDataUserRequest;
import br.com.idolink.api.dto.request.UserPasswordRequest;
import br.com.idolink.api.dto.request.UserRequest;
import br.com.idolink.api.dto.request.UserStatusRequest;
import br.com.idolink.api.dto.response.GeneralSettingsResponse;
import br.com.idolink.api.dto.response.LanguageResponse;
import br.com.idolink.api.dto.response.PersonalDataUserResponse;
import br.com.idolink.api.dto.response.UserResponse;
import br.com.idolink.api.dto.response.UserStatusResponse;
import br.com.idolink.api.service.GeneralSettingsService;
import br.com.idolink.api.service.LanguageService;
import br.com.idolink.api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController implements UserControllerSwagger {

	@Autowired
	public UserService service;
	
	@Autowired
	private GeneralSettingsService generalSettingsService;
	
	@Autowired
	private LanguageService languageService;

	@Autowired
	public IdolinkSecurity idoLinkSecurity;

	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping
	public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable) {
		Page<UserResponse> response = service.list(pageable);

		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping("/languages")
	public ResponseEntity<List<LanguageResponse>> findLanguages() {
		List<LanguageResponse> response = languageService.list();

		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/logged")
	public ResponseEntity<GeneralSettingsResponse> update(@Valid @RequestBody GeneralSettingsRequest request) {
		
		Long loggedUser = idoLinkSecurity.getUsuarioId();
		
		GeneralSettingsResponse response = generalSettingsService.update(loggedUser, request);

		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/country/{country_id}/logged")
	public ResponseEntity<UserResponse> updateCountry(@PathVariable(name = "country_id") Long idCountry){
		
		UserResponse response = service.updateCountry(idoLinkSecurity.getUsuarioId(), idCountry);
		
		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "status/logged")
	public ResponseEntity<UserStatusResponse> updateStatus(@Valid @RequestBody UserStatusRequest request) {
		
		Long loggedUser = idoLinkSecurity.getUsuarioId();
		
		UserStatusResponse response = generalSettingsService.updateStatus(loggedUser, request);

		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}")
	public ResponseEntity<UserResponse> findById(@PathVariable(name = "id") Long id) {
		UserResponse response = service.findById(id);

		return ResponseEntity.ok(response);

	}

	@Override
	@PostMapping
	public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {
		UserResponse response = service.create(request);

		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<UserResponse> delete(@PathVariable("id") Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/update-password")
	public ResponseEntity<UserResponse> updatePassword(@Valid @RequestBody UserPasswordRequest password) {
		
		Long loggedUser = idoLinkSecurity.getUsuarioId();
		
		UserResponse response = service.updatePassword(password,loggedUser);
		return ResponseEntity.ok(response);
	}

	@Override
	@GetMapping(path = "/info/logged")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<UserResponse> userlogged() {

		Long loggedUser = idoLinkSecurity.getUsuarioId();

		UserResponse response = service.findById(loggedUser);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping(path = "/generate-recovery")
	public ResponseEntity<UserResponse> generatePasswordReccovery(@RequestParam String email) {
		
		service.generatePasswordRecovery(email);
		return ResponseEntity.ok().build();
	}
	
	@Override
	@PutMapping(path = "/recovery-password")
	public ResponseEntity<UserResponse> recoverPassword(@RequestBody @Valid PasswordRecoveryRequest request) {
		
		service.recoverPasswordWithCode(request);
		return ResponseEntity.ok().build();
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/email/logged")
	public ResponseEntity<UserResponse> updateEmailUser( @RequestParam(value="email") String email) {
		
		Long loggedUser = idoLinkSecurity.getUsuarioId();
		
		UserResponse response = service.updateEmailUser(email, loggedUser);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/avatar/logged")
	public ResponseEntity<UserResponse> updateAvatarUser(@RequestParam(value="fileAvatar") Long avatar) {
		
		Long loggedUser = idoLinkSecurity.getUsuarioId();
		
		UserResponse response = service.updateAvatarUser(avatar, loggedUser);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/personal-data/logged")
	public ResponseEntity<PersonalDataUserResponse> updateStatus(@Valid @RequestBody PersonalDataUserRequest request) {
		
		Long loggedUser = idoLinkSecurity.getUsuarioId();
		
		PersonalDataUserResponse response = service.updatePersonalData(loggedUser, request);

		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping(path = "/email/authorization")
	public ResponseEntity<String> emailAuthorization(@RequestParam String token){
	
		return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(service.emailAutorization(token));
	}
	
	@PostMapping(path = "/validate-account")
	public ResponseEntity<UserResponse> validateAccount() {
		
		service.validateAccountByEmail(idoLinkSecurity.getUsuarioId());
		return ResponseEntity.ok().build();
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@DeleteMapping(path ="/logged")
	public ResponseEntity<UserResponse> deleteUserLogged() {
		
		Long loggedUser = idoLinkSecurity.getUsuarioId();
		
		service.delete(loggedUser);
	
		return ResponseEntity.noContent().build();
	}

}
