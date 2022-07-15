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
import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.controller.swagger.DiscountCouponControllerSwagger;
import br.com.idolink.api.dto.request.NewCouponRequest;
import br.com.idolink.api.dto.response.DiscountCouponResponse;
import br.com.idolink.api.service.DiscountCouponService;

@RestController
@RequestMapping("/discount-coupon")
public class DiscountCouponController implements DiscountCouponControllerSwagger{

	@Autowired
	private DiscountCouponService service;
	
	@Autowired
	private IdolinkSecurity idoLinkSecurity;
	
	@Override
	@GetMapping(path = "/shop/{shop_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<DiscountCouponResponse>> findByShop (@PathVariable(name = "shop_id") Long shopId){
		
		List<DiscountCouponResponse> response = service.findyByListShopCoupon(shopId, idoLinkSecurity.getUsuarioId());
		return ResponseEntity.ok(response);
	}
	
	@Override
	@PostMapping(path = "/shop/{shop_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<DiscountCouponResponse> create (@Valid @RequestBody NewCouponRequest request, @PathVariable(name = "shop_id") Long shopId){
		
		DiscountCouponResponse response = service.create(request, shopId);
	   return ResponseEntity.ok(response);		
	}
	
	@Override
	@DeleteMapping(path = "/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<DiscountCouponResponse> deleteById(@PathVariable Long id){
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@Override
	@PutMapping(path = "/shop/{couponId}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<DiscountCouponResponse> update (@Valid @RequestBody NewCouponRequest request, @PathVariable(name = "couponId") Long idCoupon, Boolean status, Long shopId){
		
		DiscountCouponResponse response = service.update(request, idCoupon, status, shopId);
		
		   return ResponseEntity.ok(response);		
		
		
	}
	@Override
	@GetMapping(path = "/{id_coupon}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<DiscountCouponResponse> findById ( @PathVariable(name = "id_coupon") Long idCoupon){
		
		DiscountCouponResponse response = service.findById(idCoupon, idoLinkSecurity.getUsuarioId()) ;
		
		 return ResponseEntity.ok(response);		
			
		
	}
	

}
