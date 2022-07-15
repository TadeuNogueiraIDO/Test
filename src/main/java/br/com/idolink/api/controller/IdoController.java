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
import br.com.idolink.api.controller.swagger.IdoControllerSwagger;
import br.com.idolink.api.dto.request.IdoContactRequest;
import br.com.idolink.api.dto.request.IdoSettingsRequest;
import br.com.idolink.api.dto.request.WallpaperIdoRequest;
import br.com.idolink.api.dto.request.ido.IdoProfileRequest;
import br.com.idolink.api.dto.response.CategoryResponse;
import br.com.idolink.api.dto.response.IdoContactResponse;
import br.com.idolink.api.dto.response.IdoResponse;
import br.com.idolink.api.dto.response.PredefinedModelResponse;
import br.com.idolink.api.dto.response.WallpaperIdoResponse;
import br.com.idolink.api.model.enums.PredefinedModelsTemplate;
import br.com.idolink.api.service.CategoryService;
import br.com.idolink.api.service.IdoDraftService;
import br.com.idolink.api.service.IdoService;
import br.com.idolink.api.service.PredefinedModelService;

@RestController
@RequestMapping("/ido")
public class IdoController implements IdoControllerSwagger {

	@Autowired
	private IdoService service;
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private IdolinkSecurity idoLinkSecurity;
	
	@Autowired
	private PredefinedModelService predefineModelService;
	
	@Autowired
	private IdoDraftService idoDraftService;
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/contact/{ido_id}")
	public ResponseEntity<IdoContactResponse> saveContact(@PathVariable(name = "ido_id") Long idoId, @Valid @RequestBody IdoContactRequest request) {
		IdoContactResponse response = service.saveContact(idoId, request, false);
		return ResponseEntity.ok(response);
	}
		
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/contact/{ido_id}")
	public ResponseEntity<IdoContactResponse> updateContact(@PathVariable(name = "ido_id") Long idoId, @Valid @RequestBody IdoContactRequest request) {
		IdoContactResponse response = service.saveContact(idoId, request, true);
		return ResponseEntity.ok(response);
	}
		
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}/contact")
	public ResponseEntity<IdoContactResponse> getContact(@PathVariable Long id) {
		IdoContactResponse response = service.getContact(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@DeleteMapping(path = "/contact/{id}")
	public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
		service.deleteContact(id);
		return ResponseEntity.noContent().build();
	}
	

	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/create-structure")
	public ResponseEntity<?> createProfile(@Valid @RequestBody IdoProfileRequest request) {
		IdoResponse response = service.createProfile(request, idoLinkSecurity.getUsuarioId());
		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/find-categories")
	public ResponseEntity<List<?>> findCategories(@RequestParam(value="name", required=false) String name) {
		List<CategoryResponse> response = categoryService.findByFilterContainingIgnoreCase(name);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/all-predefined-models")
	public ResponseEntity<List<?>> findAllPredefinedModels() {
		List<PredefinedModelResponse> response = predefineModelService.list();
		return ResponseEntity.ok(response);
	}
	
	
		
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/validate-domain")
	public ResponseEntity<List<?>> validateDomain(@RequestParam(value="domain", required=true) String domain) {
		service.validateDomain(domain);
		return ResponseEntity.ok(null);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/validate-change-domain/{ido_id}")
	public ResponseEntity<List<?>> validateChangeDomain(@PathVariable(name = "ido_id") Long idoId) {
		service.validateChangeDomain(idoId);
		return ResponseEntity.ok(null);
	}
			
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/find-my-idos")
	public ResponseEntity<List<?>> findMyIdos() {
		List<IdoResponse> response = service.findIdoByUser(idoLinkSecurity.getUsuarioId());
		return ResponseEntity.ok(response);
	}
	
	@Override
	@PutMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<IdoResponse> update(@PathVariable Long id, @Valid @RequestBody IdoProfileRequest request) {
		IdoResponse response = service.update(id, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
		IdoResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}
		
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/template/{ido_id}")
	public ResponseEntity<IdoResponse> updateTemplate(PredefinedModelsTemplate template, @PathVariable(name = "ido_id") Long idIdo) {
		IdoResponse response = service.saveTemplateIdo(template, idIdo, false);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/template/{ido_id}")
	public ResponseEntity<IdoResponse> setNewTemplate(PredefinedModelsTemplate template, @PathVariable(name = "ido_id") Long idIdo) {
		IdoResponse response = service.saveTemplateIdo(template, idIdo, true);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/wallpaper/{ido_id}")
	public ResponseEntity<WallpaperIdoResponse> setWallpaper(@Valid @RequestBody WallpaperIdoRequest request, @PathVariable(name = "ido_id") Long idIdo) {
		WallpaperIdoResponse response = service.setWallpaperIdo(idIdo, request);
		return ResponseEntity.ok(response);
	}
	
		
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/photo/{ido_id}")
	public  ResponseEntity<IdoResponse> updatePhotoIdo(@PathVariable(name = "ido_id") Long idoId, @RequestParam(value="icon_id", required=true) Long iconId) {
		IdoResponse response = service.UpdatePhotoIdo(iconId, idoId);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/draft/{ido_id}")
	public  ResponseEntity<IdoResponse> createDraftIdo(@PathVariable(name = "ido_id") Long idoId) {
		IdoResponse response = idoDraftService.loadDraftIdo(idoId);
		response.setContacts(service.getContact(response.getId()));
		return ResponseEntity.ok(response);
	} 
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/draft/publish/{ido_id}")
	public  ResponseEntity<IdoResponse> publishIdo(@PathVariable(name = "ido_id") Long idoId) {
		IdoResponse response = idoDraftService.publishIdo(idoId);
		response.setContacts(service.getContact(response.getId()));
		return ResponseEntity.ok(response);
	} 
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/settings/{ido_id}")
	public  ResponseEntity<IdoResponse> changeSettings(@PathVariable(name = "ido_id") Long idoId, @RequestBody IdoSettingsRequest request ) {
		IdoResponse response = service.changeSettings(idoId, request, idoLinkSecurity.getUsuarioId());
		return ResponseEntity.ok(response);
	}
	
	
	@Override
	@DeleteMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
		
}
