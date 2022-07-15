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
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.ShopCategoryControllerSwagger;
import br.com.idolink.api.dto.request.ShopCategoryRequest;
import br.com.idolink.api.dto.response.ShopCategoryResponse;
import br.com.idolink.api.dto.response.ShopCategorySimpleResponse;
import br.com.idolink.api.service.ShopCategoryService;

@RestController
@RequestMapping("/shop-category")
public class ShopCategoryController implements ShopCategoryControllerSwagger {

	@Autowired
	private ShopCategoryService service;

	@Override
	@GetMapping(path = "/ido/{shop_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<ShopCategorySimpleResponse>> findAllByShop(@PathVariable(name = "shop_id") Long shopId) {
		List<ShopCategorySimpleResponse> response = service.findByShop(shopId);
		return ResponseEntity.ok(response);
	}

	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{id}")
	public ResponseEntity<ShopCategoryResponse> findById(@PathVariable(name = "id") Long id) {
		ShopCategoryResponse response = service.findById(id);
		return ResponseEntity.ok(response);

	}

	@Override
	@PostMapping(path = "/ido/{shop_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ShopCategoryResponse> create(@PathVariable(name = "shop_id") Long shopId,
			@Valid @RequestBody ShopCategoryRequest request) {

		ShopCategoryResponse response = service.create(shopId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ShopCategoryResponse> update(@PathVariable Long id,
			@Valid @RequestBody ShopCategoryRequest request) {

		ShopCategoryResponse response = service.update(id, request);
		return ResponseEntity.ok(response);

	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{id}/update-hide-product")
	public ResponseEntity<Void> updateHideProduct(@PathVariable Long id) {
		service.updateHideProduct(id);
		return ResponseEntity.ok().build();
	}

	@Override
	@DeleteMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
