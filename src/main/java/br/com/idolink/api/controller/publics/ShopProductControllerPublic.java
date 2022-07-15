package br.com.idolink.api.controller.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.controller.swagger.publics.ShopProductControllerSwaggerPublic;
import br.com.idolink.api.dto.response.ShopProductDigitalResponse;
import br.com.idolink.api.dto.response.ShopProductPhysicalResponse;
import br.com.idolink.api.service.ShopProductService;

@RestController
@RequestMapping("/public/shop-service-product")
public class ShopProductControllerPublic implements ShopProductControllerSwaggerPublic{
	
	@Autowired
	private ShopProductService service;
	
	@Override
	@GetMapping(path = "/physical/{id}")
	public ResponseEntity<ShopProductPhysicalResponse> findPhysicalById(@PathVariable(name = "id") Long id) {
		ShopProductPhysicalResponse response = service.findPhysicalById(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping(path = "/digital/{id}")
	public ResponseEntity<ShopProductDigitalResponse> findDigitalById(@PathVariable(name = "id") Long id) {
		ShopProductDigitalResponse response = service.findDigitalbyId(id);
		return ResponseEntity.ok(response);
	}

}
