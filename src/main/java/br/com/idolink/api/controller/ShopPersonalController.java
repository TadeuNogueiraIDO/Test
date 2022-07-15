package br.com.idolink.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
//import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.ShopPersonalControllerSwagger;
import br.com.idolink.api.dto.response.PersonalHomeShopResponse;
import br.com.idolink.api.dto.response.PersonalProductShop;
import br.com.idolink.api.dto.response.PersonalShopProfile;
import br.com.idolink.api.dto.response.ShopGenericResponse;
import br.com.idolink.api.filter.PersonalHomeFilter;
import br.com.idolink.api.service.ShopProductService;
import br.com.idolink.api.service.ShopService;

@RestController
@RequestMapping("/personal")
public class ShopPersonalController implements ShopPersonalControllerSwagger{

	@SuppressWarnings("unused")
	@Autowired
	private IdolinkSecurity security;
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private ShopProductService productService;
	
	@Override
	@GetMapping("/profile-shop")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<PersonalShopProfile> findProfileShop(@RequestParam(required = true) Long  id) {
		return ResponseEntity.ok(shopService.findProfileShop(id));
	}

	@Override
	@GetMapping("/product")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<PersonalProductShop> findProfileProduct(Long id) {
		return ResponseEntity.ok(productService.findPersonalProduct(id));
	}

	@Override
	@GetMapping
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<ShopGenericResponse>> findAllProfileShop(String filter) {
		return ResponseEntity.ok(shopService.findAllShopProfile(filter));
	}

	@Override
	@GetMapping("/home")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<PersonalHomeShopResponse>> findHomeShop(PersonalHomeFilter filter) {
		return ResponseEntity.ok(productService.findPersonalHome(filter));
	}
}
