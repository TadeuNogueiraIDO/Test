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
import br.com.idolink.api.controller.swagger.ShopControllerSwagger;
import br.com.idolink.api.dto.request.ShopRequest;
import br.com.idolink.api.dto.request.UpdateFormatCardShopRequest;
import br.com.idolink.api.dto.response.ShopResponse;
import br.com.idolink.api.service.ShopService;

@RestController
@RequestMapping("/shop")
public class ShopController implements ShopControllerSwagger{

	@Autowired
	private ShopService service;
	
	@Autowired
	private IdolinkSecurity idoLinkSecurity;

	@Override
	@GetMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ShopResponse> findByIdo(@PathVariable(name = "ido_id") Long idoId) {
		ShopResponse response = service.findByIdo(idoId);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ShopResponse> findById(@PathVariable(name = "id") Long id) {
		ShopResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<ShopResponse>> findAllByUser() {
		List<ShopResponse> response = service.findByUser(idoLinkSecurity.getUsuarioId());
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ShopResponse> create(@PathVariable(name = "ido_id") Long idoId, @Valid @RequestBody ShopRequest request) {

		ShopResponse response = service.create(idoId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ShopResponse> update(@PathVariable Long id,
			@Valid @RequestBody ShopRequest request) {

		ShopResponse response = service.update(id, request);
		return ResponseEntity.ok(response);

	}
	@Override
	@PutMapping(path = "/status/{shop_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ShopResponse> updateShopStatus(@PathVariable(name = "shop_id") Long id, @RequestParam Boolean status){
		
	ShopResponse response = service.updateStatus(id, status);
	
	return ResponseEntity.ok(response);
		
	}
	
	
	@Override
	@PutMapping("/format-card/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ShopResponse> updateFormatCardShop(@PathVariable(name = "id") Long id,
			@Valid @RequestBody UpdateFormatCardShopRequest request) {
		ShopResponse response = service.updateFormatCardShop(id, request);
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
