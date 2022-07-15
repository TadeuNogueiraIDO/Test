package br.com.idolink.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.MyShopControllerSwagger;
import br.com.idolink.api.dto.response.MyShopResponse;
import br.com.idolink.api.dto.response.ShopCategoryStockResponse;
import br.com.idolink.api.filter.ShopCategoryStockFilter;
import br.com.idolink.api.service.MyShopService;
import br.com.idolink.api.service.ShopCategoryService;

@RestController
@RequestMapping("/my-shop")
public class MyShopController implements MyShopControllerSwagger{
	
	@Autowired
	private MyShopService service;
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@Override
	@GetMapping(path = "/{shop_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<ShopCategoryStockResponse>> findAllByShop(@PathVariable(name = "shop_id") Long shopId, ShopCategoryStockFilter filter) {
		List<ShopCategoryStockResponse> response = shopCategoryService.findMyProductsByShop(shopId, filter);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping(path = "/ido/{ido_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<MyShopResponse> findByIdo(@PathVariable(name = "ido_id") Long id) {
		MyShopResponse response = service.dashboardMyShop(id, true);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{category_id}/hide-category")
	public ResponseEntity<Void> updateHideProduct(@PathVariable(name = "category_id") Long category_id) {
		service.hideCategory(category_id);
		return ResponseEntity.ok().build();
	}


}
