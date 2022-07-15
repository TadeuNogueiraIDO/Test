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
import br.com.idolink.api.controller.swagger.MenuFooterControllerSwagger;
import br.com.idolink.api.dto.request.ItemMenuFooterRequest;
import br.com.idolink.api.dto.request.MenuFooterRequest;
import br.com.idolink.api.dto.request.UpdateMenuFooterRequest;
import br.com.idolink.api.dto.response.ItemMenuFooterResponse;
import br.com.idolink.api.dto.response.MenuFooterResponse;
import br.com.idolink.api.dto.response.ShopCategoryForMenuFooterReponse;
import br.com.idolink.api.dto.response.ToolsIdoReponse;
import br.com.idolink.api.dto.response.UpdateMenuFooterResponse;
import br.com.idolink.api.service.MenuFooterService;
import br.com.idolink.api.service.ShopCategoryService;

@RestController
@RequestMapping("/menu-footer")
public class MenuFooterController implements MenuFooterControllerSwagger {

	@Autowired
	private MenuFooterService service;

	@Autowired
	private ShopCategoryService shopCategoryService;

	@Override
	@GetMapping(path = "/shop/{shop_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<ShopCategoryForMenuFooterReponse>> findAllByShop(
			@PathVariable(name = "shop_id") Long shopId) {
		List<ShopCategoryForMenuFooterReponse> response = shopCategoryService.findCategoryByShop(shopId);
		return ResponseEntity.ok(response);
	}

	@Override
	@GetMapping(path = "/ido_tools/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<ToolsIdoReponse>> findToolsByIdo(@PathVariable(name = "ido_id") Long idoId) {
		List<ToolsIdoReponse> response = service.findToolByIdo(idoId);
		return ResponseEntity.ok(response);
	}

	@Override
	@GetMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuFooterResponse> findByIdo(@PathVariable(name = "ido_id") Long idoId) {
		MenuFooterResponse response = service.findByIdo(idoId);
		return ResponseEntity.ok(response);
	}

	@Override
	@GetMapping("/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuFooterResponse> findById(@PathVariable(name = "id") Long id) {
		MenuFooterResponse response = service.findById(id);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping("/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuFooterResponse> create(@PathVariable(name = "ido_id") Long idoId,
			@Valid @RequestBody MenuFooterRequest request) {
		MenuFooterResponse response = service.create(request, idoId);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping("/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<UpdateMenuFooterResponse> updateMenuFooter(@RequestBody UpdateMenuFooterRequest request,
			@PathVariable(name = "id") Long id) {
		UpdateMenuFooterResponse response = service.updateMenuFooter(id, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@PutMapping("/update/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MenuFooterResponse> update(@RequestBody MenuFooterRequest request, @PathVariable(name = "id") Long id ){

        MenuFooterResponse response = service.update(request, id);	
		return ResponseEntity.ok(response);		
	}

	@Override
	@PutMapping("/{menu_id}/item/{item_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ItemMenuFooterResponse> updateItemMenuFooter(@RequestBody ItemMenuFooterRequest request,
			@PathVariable(name = "item_id") Long itemId, Long menuId) {
		ItemMenuFooterResponse response = service.updateItemMenuFooter(itemId, menuId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping("/{menu_id}/item")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ItemMenuFooterResponse> addItem(@RequestBody ItemMenuFooterRequest request,
			@PathVariable(name = "menu_id") Long menuId) {
		ItemMenuFooterResponse response = service.addItem(request, menuId);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping("/{menu_id}/item/{item_id}/hide")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ItemMenuFooterResponse> hideItem(@PathVariable(name = "item_id") Long itemId,
			@PathVariable(name = "menu_id") Long menuId) {
		ItemMenuFooterResponse response = service.hideItem(itemId, menuId);
		return ResponseEntity.ok(response);
	}

	@Override
	@DeleteMapping("/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<?> deleteMenuFooter(@PathVariable(name = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/{menu_id}/item/{item_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<?> delete(@PathVariable(name = "item_id") Long itemId,
			@PathVariable(name = "menu_id") Long menuId) {
		service.deleteItemMenuFooter(itemId, menuId);
		return ResponseEntity.noContent().build();
	}

}
