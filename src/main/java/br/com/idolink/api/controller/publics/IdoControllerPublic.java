package br.com.idolink.api.controller.publics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.controller.swagger.publics.IdoControllerSwaggerPublic;
import br.com.idolink.api.dto.response.IdoResponse;
import br.com.idolink.api.dto.response.IdoToolPositionResponse;
import br.com.idolink.api.dto.response.ShopCategoryStockResponse;
import br.com.idolink.api.service.IdoDraftService;
import br.com.idolink.api.service.IdoService;
import br.com.idolink.api.service.IdoToolPositionService;
import br.com.idolink.api.service.ShopCategoryService;

@RestController
@RequestMapping("/public/ido")
public class IdoControllerPublic implements IdoControllerSwaggerPublic {

	@Autowired
	private IdoService service;
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@Autowired
	private IdoToolPositionService idoToolPositionService;
	
	@Autowired
	private IdoDraftService draftService;
	
	@Override
	@GetMapping(path = "/{id}")
	public ResponseEntity<IdoResponse> publicFindById(@PathVariable(name = "id") Long id) {
		IdoResponse response = service.publicFindById(id);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping(path = "/domain/{name}")
	public ResponseEntity<IdoResponse> findByDomain(@PathVariable(name = "name") String domain) {
		IdoResponse response = service.findByDomainPublic(domain);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(path = "/shop/{shop_id}")
	public ResponseEntity<List<ShopCategoryStockResponse>> findAllByShop(@PathVariable(name = "shop_id") Long shopId) {
		List<ShopCategoryStockResponse> response = shopCategoryService.findStockByShop(shopId);
		return ResponseEntity.ok(response);
	}
	
	@Override
	@GetMapping(path = "/reposition-tool/{ido_id}")
	public ResponseEntity<List<IdoToolPositionResponse>> findByIdo(@PathVariable(name = "ido_id") Long idoId) {
		List<IdoToolPositionResponse> response= idoToolPositionService.findIdoToolPositionByIdo(idoId);
		return ResponseEntity.ok(response);			
	}
	
	@Override
	@GetMapping(path = "/draft/{name}")
	public ResponseEntity<IdoResponse> findByIdo(@PathVariable(name = "name") String domain) {
		IdoResponse response = draftService.loadDraftByIdoPublic(domain);
		return ResponseEntity.ok(response);			
	}
	
}
