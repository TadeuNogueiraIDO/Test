package br.com.idolink.api.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.ShippingSettingsRequest;
import br.com.idolink.api.dto.response.ShippingSettingsResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ShippingSettingsMapper;
import br.com.idolink.api.model.ShippingSettings;
import br.com.idolink.api.model.Shop;
import br.com.idolink.api.repository.ShippingSettingsRepository;
import br.com.idolink.api.repository.ShopRepository;
import br.com.idolink.api.service.ShippingSettingsService;

@Service
public class ShippingSettingsServiceImpl implements ShippingSettingsService {

	@Autowired
	private ShippingSettingsMapper mapper;

	@Autowired
	private ShippingSettingsRepository repository;

	@Autowired
	private ShopRepository shopRepository;

	@Override
	public ShippingSettingsResponse findByShop(Long shopId) {

		Optional<Shop> shop = shopRepository.findById(shopId);
		validate(shop, "shop", "api.error.shop.not.found");
		
		Optional<ShippingSettings> model = repository.findByShop(shop.get());
		
		validate(model, "ShippingSettings", "api.error.shipping.setting.not.found");
		return mapper.response(model.get());
		
	}

	@Override
	public ShippingSettingsResponse findById(Long id) {
		Optional<ShippingSettings> model = repository.findById(id);
		validate(model, "ShippingSettings", "api.error.shipping.setting.not.found");
		return mapper.response(model.get());
	}

	@Transactional
	@Override
	public ShippingSettingsResponse create(Long shopId, ShippingSettingsRequest request) {

		Optional<Shop> shop = shopRepository.findById(shopId);
		validate(shop,"Shop", "api.error.shop.not.found");
		
		Optional<ShippingSettings> shippingOld = repository.findByShop(shop.get());
		
		if(shippingOld.isPresent()) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "ShippingSettings", "api.error.shipping.setting.shop.conflict");
		}
				
		ShippingSettings model = mapper.model(request);
		
		model.setDigitalDelivery(null);
		model.setLocaPickups(null);
		model.setOwnShipping(null);
		model.setPostOfficeShipping(null);
		model.setShop(shop.get());
		if(model.getFreeShipping().equals(false)) {
			model.setMinimumValue(null);
			model.setDecripiton(null);
			model.setDisplayWarning(null);
			model.setCustomText(null);
		}
		if(model.getFreeShipping().equals(true) && model.getDisplayWarning().equals(false)) {
			model.setCustomText(null);
		}
		
		model = repository.save(model);
		return mapper.response(model);
	}

	@Transactional
	@Override
	public ShippingSettingsResponse update(Long id, ShippingSettingsRequest request) {
		
		@SuppressWarnings("serial")
		ShippingSettings model = repository.findById(id).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,
				"ShippingSettings", "api.error.shipping.setting.not.found") {
		});
		
		ShippingSettings modelNew = mapper.model(request);
		BeanUtils.copyProperties(modelNew, model, "id", "dateModel", "shop" , "ownShipping", "postOfficeShipping", "locaPickups", "digitalDelivery");
		return mapper.response(repository.save(model));
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Optional<ShippingSettings> model = repository.findById(id);
		validate(model, "ShippingSettings", "api.error.shipping.setting.not.found");
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "ShippingSettings", "api.error.shipping.setting.conflict");
		}
	}

	private void validate(Optional<?> model, String field, String message) {
		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
}
