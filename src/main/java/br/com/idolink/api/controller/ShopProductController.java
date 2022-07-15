package br.com.idolink.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import br.com.idolink.api.controller.swagger.ShopProductControllerSwagger;
import br.com.idolink.api.dto.request.ShopProductDigitalRequest;
import br.com.idolink.api.dto.request.ShopProductDigitalVariationRequest;
import br.com.idolink.api.dto.request.ShopProductPhysicalRequest;
import br.com.idolink.api.dto.request.ShopProductPhysicalVariationRequest;
import br.com.idolink.api.dto.request.ShopProductRequest;
import br.com.idolink.api.dto.response.EnumUnitResponse;
import br.com.idolink.api.dto.response.ProductTypeResponse;
import br.com.idolink.api.dto.response.ShopProductDigitalResponse;
import br.com.idolink.api.dto.response.ShopProductPhysicalResponse;
import br.com.idolink.api.dto.response.ShopProductResponse;
import br.com.idolink.api.dto.response.ShopSimpleProductResponse;
import br.com.idolink.api.service.ProductTypeService;
import br.com.idolink.api.service.ShopProductService;

@RestController
@RequestMapping("/shop-service-product")
public class ShopProductController implements ShopProductControllerSwagger {
	
	@Autowired
	private ShopProductService service;
	
	@Autowired
	private ProductTypeService productTypeService;

	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{category_id}")
	public ResponseEntity<List<ShopSimpleProductResponse>> findByShopCategory(@PathVariable(name = "category_id") Long id) {
		List<ShopSimpleProductResponse> response = service.findByShopCategory(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<ProductTypeResponse>> findAll() {
		List<ProductTypeResponse> response = productTypeService.list();
		return ResponseEntity.ok(response);
	}
	
	
	@Override
	@GetMapping(path = "/shop/{shop_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<ShopProductPhysicalResponse>> findAllByShopByName(Pageable pageable, @PathVariable(name = "shop_id") Long shopId, @RequestParam(required = false) String name, Boolean stock) {
		List<ShopProductPhysicalResponse> response = service.findByShopByName(pageable, shopId, name, stock);
		return ResponseEntity.ok(response);
	}
	
	//###START DIGITAL PRODUCT
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/digital/{category_id}")
	public ResponseEntity<ShopProductDigitalResponse> createDigital(@PathVariable(name = "category_id") Long categoryId, @Valid @RequestBody ShopProductDigitalRequest request) {
		ShopProductDigitalResponse response = service.createDigital(categoryId, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/digital/{id}")
	public ResponseEntity<ShopProductDigitalResponse> findDigitalById(@PathVariable(name = "id") Long id) {
		ShopProductDigitalResponse response = service.findDigitalbyId(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/digital/{id}")
	public ResponseEntity<ShopProductResponse> updateDigital(@RequestParam(value="id") Long productId, @Valid @RequestBody ShopProductDigitalRequest request) {
		ShopProductResponse response = service.updateDigital(productId,request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/digital/{product_id}/variation")
	public ResponseEntity<ShopProductDigitalResponse> createDigitalVariation(@PathVariable(name = "product_id") Long idShopProduct, @Valid @RequestBody ShopProductDigitalVariationRequest request) {
		ShopProductDigitalResponse response = service.createDigitalVariation(idShopProduct, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/digital/{product_id}/variation/{product_variation_id}")
	public ResponseEntity<ShopProductDigitalResponse> updateDigitalVariation(@PathVariable(name = "product_id") Long productId,  @PathVariable(name = "product_variation_id") Long variationId, @Valid @RequestBody ShopProductDigitalVariationRequest request) {
		ShopProductDigitalResponse response = service.updateDigitalVariation(productId, variationId, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@DeleteMapping(path = "/digital/{product_id}/variation/{product_variation_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> deleteDigitalVariation(@PathVariable(name = "product_id") Long productId, @PathVariable(name = "product_variation_id") Long id) {
		service.deleteVariation(productId, id);
		return ResponseEntity.noContent().build();
	}
	
	//###END DIGITAL PRODUCT
	
	//###START PHYSICAL PRODUCT
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/physical/{category_id}")
	public ResponseEntity<ShopProductPhysicalResponse> createPhysical(@PathVariable(name = "category_id") Long categoryId, @Valid @RequestBody ShopProductPhysicalRequest request) {
		ShopProductPhysicalResponse response = service.createPhysical(categoryId, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/physical/{id}")
	public ResponseEntity<ShopProductPhysicalResponse> findPhysicalById(@PathVariable(name = "id") Long id) {
		ShopProductPhysicalResponse response = service.findPhysicalById(id);
		return ResponseEntity.ok(response);

	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/physical/{product_id}")
	public ResponseEntity<ShopProductResponse> updatePhysical(@PathVariable(value="product_id") Long productId, @Valid @RequestBody ShopProductPhysicalRequest request) {
		ShopProductResponse response = service.updatePhysical(productId,request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/physical/{product_id}/variation")
	public ResponseEntity<ShopProductPhysicalResponse> createPhysicalVariation(@PathVariable(name = "product_id") Long idShopProduct, @Valid @RequestBody ShopProductPhysicalVariationRequest request) {
		ShopProductPhysicalResponse response = service.createPhysicalVariation(idShopProduct, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/physical/{product_id}/variation/{product_variation_id}")
	public ResponseEntity<ShopProductPhysicalResponse> updatePhysicalVariation(@PathVariable(name = "product_id") Long productId, @PathVariable(name = "product_variation_id") Long variationId, @Valid @RequestBody ShopProductPhysicalVariationRequest request) {
		ShopProductPhysicalResponse response = service.updatePhysicalVariation(productId, variationId, request);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@DeleteMapping(path = "/physical/{product_id}/variation/{product_variation_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> deletePhysicalVariation(@PathVariable(name = "product_id") Long productId, @PathVariable(name = "product_variation_id") Long id) {
		service.deleteVariation(productId, id);
		return ResponseEntity.noContent().build();
	}
	
	//###END PHYSICAL PRODUCT
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{id}")
	public ResponseEntity<ShopProductResponse> update(@RequestParam(value="category_id", required=false) Long categoryId, @Valid @RequestBody ShopProductRequest request,
			@PathVariable(name = "id") Long id) {
		ShopProductResponse response = service.update(id, request,categoryId);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{id}/update-vitrine-show")
	public ResponseEntity<Void> updateisVitrineShow(@PathVariable Long id) {
		service.updateisVitrineShow(id);
		return ResponseEntity.ok().build();
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{id}/enable-disable")
	public ResponseEntity<Void> enableAndDisable(@PathVariable Long id) {
		service.enableAndDisable(id);
		return ResponseEntity.ok().build();
	}
	
	
	@Override
	@DeleteMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@GetMapping(path = "/units")
	public ResponseEntity<EnumUnitResponse> getUnits() {
		return ResponseEntity.ok(service.getEnumUnits());

	}
	
}
