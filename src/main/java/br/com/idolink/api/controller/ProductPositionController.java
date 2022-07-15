package br.com.idolink.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.ProductPositionControllerSwagger;
import br.com.idolink.api.dto.request.ProductPositionRequest;
import br.com.idolink.api.dto.response.ProductPositionResponse;
import br.com.idolink.api.service.ProductPositionService;

@RestController
@RequestMapping("/reposition-producy")
public class ProductPositionController implements ProductPositionControllerSwagger {

	
	@Autowired
	private ProductPositionService service;
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@GetMapping(path = "/{shop_category_id}")
	public ResponseEntity<List<ProductPositionResponse>> findByProduct(@PathVariable("shop_category_id") Long shopCategoryId) {
		List<ProductPositionResponse> response= service.findProductBypositionType(shopCategoryId);
		return ResponseEntity.ok(response);		
	}
	
	@Override
	@CheckSecurity.User.PodeAcessar
	@PostMapping(path = "/{shop_category_id}")
	public ResponseEntity<List<ProductPositionResponse>> create(@PathVariable("shop_category_id") Long shopCategoryId, @Valid @RequestBody List<ProductPositionRequest> request) {
		List<ProductPositionResponse> response = service.create(shopCategoryId, request);
		return ResponseEntity.ok(response);
	}
		
	@Override
	@CheckSecurity.User.PodeAcessar
	@PutMapping(path = "/{shop_category_id}")
  	public ResponseEntity<List<ProductPositionResponse>> update(@RequestBody List<ProductPositionRequest> requestList, @PathVariable("shop_category_id") Long shopCategoryId) {
		List<ProductPositionResponse> response= service.updateAllPositions(requestList, shopCategoryId);
		return ResponseEntity.ok(response);		
	}
	
	
	
}