package br.com.idolink.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.ShopStockControllerSwagger;
import br.com.idolink.api.dto.request.StockRequest;
import br.com.idolink.api.dto.response.ShopCategoryStockResponse;
import br.com.idolink.api.dto.response.ShopProductDigitalResponse;
import br.com.idolink.api.dto.response.ShopProductPhysicalResponse;
import br.com.idolink.api.service.ShopCategoryService;
import br.com.idolink.api.service.ShopProductService;

@RestController
@RequestMapping("/shop-stock")
public class ShopStockController implements ShopStockControllerSwagger{
	
	@Autowired
	private ShopCategoryService service;
	

	@Autowired
	private ShopProductService serviceShopProduct;
	
	@Override
	@GetMapping(path = "/{shop_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<ShopCategoryStockResponse>> findAllByShop(@PathVariable(name = "shop_id") Long shopId) {
		List<ShopCategoryStockResponse> response = service.findStockByShop(shopId);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/physical/{product_id}/variation/{product_variation_id}")
	public ResponseEntity<ShopProductPhysicalResponse> updateStockVariationPhysical(@PathVariable(name = "product_id") Long productId, @PathVariable(name = "product_variation_id") Long variationId, @Valid @RequestBody StockRequest request) {
		ShopProductPhysicalResponse response = serviceShopProduct.updateStockVariationPhysical(productId, variationId, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/digital/{product_id}/variation/{product_variation_id}")
	public ResponseEntity<ShopProductDigitalResponse> updateStockVariationDigital(@PathVariable(name = "product_id") Long productId,  @PathVariable(name = "product_variation_id") Long variationId, @Valid @RequestBody StockRequest request) {
		ShopProductDigitalResponse response = serviceShopProduct.updateStockVariationDigital(productId, variationId, request);
		return ResponseEntity.ok(response);
	}

}
