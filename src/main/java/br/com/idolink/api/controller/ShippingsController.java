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
import br.com.idolink.api.controller.swagger.ShippingsControllerSwagger;
import br.com.idolink.api.dto.request.DigitalDeliveryRequest;
import br.com.idolink.api.dto.request.LocalPickupRequest;
import br.com.idolink.api.dto.request.OwnShippingRequest;
import br.com.idolink.api.dto.request.PostOfficeShippingRequest;
import br.com.idolink.api.dto.request.ShippingSettingsRequest;
import br.com.idolink.api.dto.response.DigitalDeliveryResponse;
import br.com.idolink.api.dto.response.LocalPickupResponse;
import br.com.idolink.api.dto.response.OwnShippingResponse;
import br.com.idolink.api.dto.response.PostOfficeShippingResponse;
import br.com.idolink.api.dto.response.ShippingSettingsResponse;
import br.com.idolink.api.service.DigitalDeliveryService;
import br.com.idolink.api.service.LocalPickupService;
import br.com.idolink.api.service.OwnShippingService;
import br.com.idolink.api.service.PostOfficeShippingService;
import br.com.idolink.api.service.ShippingSettingsService;

@RestController
@RequestMapping("/shippings")
public class ShippingsController implements ShippingsControllerSwagger {

	@Autowired
	private ShippingSettingsService settingsService;

	@Autowired
	private OwnShippingService ownService;
	
	@Autowired
	private PostOfficeShippingService postOfficeService;
	
	@Autowired
	private LocalPickupService localPickupService;
	
	@Autowired
	private DigitalDeliveryService digitalDeliveryService;
	

	@Override
	@GetMapping(path = "/setting/{shop_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ShippingSettingsResponse> findByShop(@PathVariable(name = "shop_id") Long shopId) {
		ShippingSettingsResponse response = settingsService.findByShop(shopId);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping(path = "/setting/{shop_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ShippingSettingsResponse> createSettings(@PathVariable(name = "shop_id") Long shopId,
			@Valid @RequestBody ShippingSettingsRequest request) {
		ShippingSettingsResponse response = settingsService.create(shopId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping(path = "/setting/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<ShippingSettingsResponse> updateSettings(@PathVariable Long id,
			@Valid @RequestBody ShippingSettingsRequest request) {
		ShippingSettingsResponse response = settingsService.update(id, request);
		return ResponseEntity.ok(response);

	}

	@Override
	@DeleteMapping(path = "/setting/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> deleteSettings(@PathVariable Long id) {

		settingsService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// OWN SHIPPING

	@Override
	@GetMapping(path = "/own/{setting_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<OwnShippingResponse> findByShippingSettings(
			@PathVariable(name = "setting_id") Long settingId) {
		OwnShippingResponse response = ownService.findByShippingSettings(settingId);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping(path = "/own/{setting_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<OwnShippingResponse> createOwnShipping(@PathVariable(name = "setting_id") Long settingId,
			@Valid @RequestBody OwnShippingRequest request) {
		OwnShippingResponse response = ownService.create(settingId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping(path = "/own/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<OwnShippingResponse> updateOwnShipping(@PathVariable Long id,
			@Valid @RequestBody OwnShippingRequest request) {
		OwnShippingResponse response = ownService.update(id, request);
		return ResponseEntity.ok(response);

	}

	@Override
	@DeleteMapping(path = "/own/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> deleteOwnShipping(@PathVariable Long id) {
		ownService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// POST OFFICE

	@Override
	@GetMapping(path = "/post-office/{setting_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<PostOfficeShippingResponse> findPostOfficeByShippingSettings(
			@PathVariable(name = "setting_id") Long settingId) {
		PostOfficeShippingResponse response = postOfficeService.findByShippingSettings(settingId);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping(path = "/post-office/{setting_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<PostOfficeShippingResponse> createPostOffice(@PathVariable(name = "setting_id") Long settingId,
			@Valid @RequestBody PostOfficeShippingRequest request) {
		PostOfficeShippingResponse response = postOfficeService.create(settingId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping(path = "/post-office/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<PostOfficeShippingResponse> updatePostOffice(@PathVariable Long id,
			@Valid @RequestBody PostOfficeShippingRequest request) {
		PostOfficeShippingResponse response = postOfficeService.update(id, request);
		return ResponseEntity.ok(response);

	}

	@Override
	@DeleteMapping(path = "/post-office/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> deletePostOffice(@PathVariable Long id) {
		postOfficeService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//LOCAL PICKUP
	
	@Override
	@GetMapping(path = "/local-pickup/{setting_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<LocalPickupResponse>> findLocalPickupByShippingSettings(
			@PathVariable(name = "setting_id") Long settingId, Boolean isEnabled) {
		List<LocalPickupResponse> response = localPickupService.findByShippingSettings(settingId, isEnabled);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping(path = "/local-pickup/{setting_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<LocalPickupResponse> createLocalPickup(@PathVariable(name = "setting_id") Long settingId,
			@Valid @RequestBody LocalPickupRequest request) {
		LocalPickupResponse response = localPickupService.create(settingId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping(path = "/local-pickup/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<LocalPickupResponse> updateLocalPickup(@PathVariable Long id,
			@Valid @RequestBody LocalPickupRequest request) {
		LocalPickupResponse response = localPickupService.update(id, request);
		return ResponseEntity.ok(response);

	}
	
	@Override
	@DeleteMapping(path = "/local-pickup/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> deleteLocalPickup(@PathVariable Long id) {
		localPickupService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//DIGITAL DELIVERY
	
	@Override
	@GetMapping(path = "/digital-delivery/{setting_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<DigitalDeliveryResponse> findDigitalDeliveryByShippingSettings(
			@PathVariable(name = "setting_id") Long settingId) {
		DigitalDeliveryResponse response = digitalDeliveryService.findByShippingSettings(settingId);
		return ResponseEntity.ok(response);
	}

	@Override
	@PostMapping(path = "/digital-delivery/{setting_id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<DigitalDeliveryResponse> createDigitalDelivery(@PathVariable(name = "setting_id") Long settingId,
			@Valid @RequestBody DigitalDeliveryRequest request) {
		DigitalDeliveryResponse response = digitalDeliveryService.create(settingId, request);
		return ResponseEntity.ok(response);
	}

	@Override
	@PutMapping(path = "/digital-delivery/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<DigitalDeliveryResponse> updateDigitalDelivery(@PathVariable Long id,
			@Valid @RequestBody DigitalDeliveryRequest request) {
		DigitalDeliveryResponse response = digitalDeliveryService.update(id, request);
		return ResponseEntity.ok(response);

	}

	@Override
	@DeleteMapping(path = "/digital-delivery/{id}")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Void> deleteDigitalDelivery(@PathVariable Long id) {
		digitalDeliveryService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	

}
