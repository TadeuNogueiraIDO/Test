package br.com.idolink.api.service.impl;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.OwnShippingRequest;
import br.com.idolink.api.dto.response.OwnShippingResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.OwnShippingMapper;
import br.com.idolink.api.model.OwnShipping;
import br.com.idolink.api.model.OwnShippingVariation;
import br.com.idolink.api.model.ShippingSettings;
import br.com.idolink.api.model.enums.TypePrice;
import br.com.idolink.api.repository.OwnShippingRepository;
import br.com.idolink.api.repository.OwnShippingVariationRepository;
import br.com.idolink.api.repository.ShippingSettingsRepository;
import br.com.idolink.api.service.OwnShippingService;

@Service
public class OwnShippingServiceImpl implements OwnShippingService {

	@Autowired
	private OwnShippingMapper mapper;

	@Autowired
	private OwnShippingRepository repository;
	
	@Autowired
	private OwnShippingVariationRepository variationRepository;

	@Autowired
	private ShippingSettingsRepository settingsRepository;

	@Override
	public OwnShippingResponse findByShippingSettings(Long settingsId) {

		Optional<ShippingSettings> settings = settingsRepository.findById(settingsId);
		validate(settings, "ShippingSettings", "api.error.shipping.setting.not.found");
		
		Optional<OwnShipping> model = repository.findByShippingSettings(settings.get());
		validate(model, "OwnShipping", "api.error.own.shipping.not.found");
		
		return mapper.response(model.get());
		
	}

	@Override
	public OwnShippingResponse findById(Long id) {
		Optional<OwnShipping> model = repository.findById(id);
		validate(model, "OwnShipping", "api.error.own.shipping.not.found");
		return mapper.response(model.get());
	}

	@Transactional
	@Override
	public OwnShippingResponse create(Long settingsId, OwnShippingRequest request) {

		Optional<ShippingSettings> settings = settingsRepository.findById(settingsId);
		validate(settings, "ShippingSettings", "api.error.shipping.setting.not.found");
		
		Optional<OwnShipping> shippingOld = repository.findByShippingSettings(settings.get());
		
		if(shippingOld.isPresent()) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "OwnShipping", "api.error.own.shipping.shop.conflict");
		}
		
		validateRequest(request);
				
		OwnShipping model = mapper.model(request);
		model.setShippingSettings(settings.get());
		model = repository.save(model);
		
		if(Objects.nonNull(model.getOwnShippingVariation())){
			for (OwnShippingVariation variation : model.getOwnShippingVariation()) {
				variation.setOwnShipping(model);
				variationRepository.save(variation);
			}
		}
		
		
				
		return mapper.response(model);
	}

	@Transactional
	@Override
	public OwnShippingResponse update(Long id, OwnShippingRequest request) {

		@SuppressWarnings("serial")
		OwnShipping model = repository.findById(id).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,
				"ShippingSettings", "api.error.shipping.setting.not.found") {
		});
		
		validateRequest(request);
		
		OwnShipping modelNew = mapper.model(request);
		
		//limpa as variacoes antigas
		for (OwnShippingVariation variation : model.getOwnShippingVariation()) {
			variation.setOwnShipping(model);
			variationRepository.delete(variation);
		}
				
		BeanUtils.copyProperties(modelNew, model, "id", "dateModel", "shippingSettings");
		
		//atualiza as novas variacoes de preco
		if(Objects.nonNull(model.getOwnShippingVariation())){
			for (OwnShippingVariation variation : model.getOwnShippingVariation()) {
				variation.setOwnShipping(model);
				variationRepository.save(variation);
			}
		}
		
		return mapper.response(repository.save(model));
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Optional<OwnShipping> model = repository.findById(id);
		validate(model, "OwnShipping", "api.error.shipping.setting.not.found");
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "OwnShipping", "api.error.own.shipping.conflict");
		}
	}

	private void validateRequest(OwnShippingRequest request) {
		
		if(request.getTypePrice() == TypePrice.FIXED) {
			
			if(Objects.isNull(request.getPrice()) || request.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "OwnShipping", "api.error.own.shipping.value.not.found");
			}
			
			if(Objects.nonNull(request.getOwnShippingVariation()) && !request.getOwnShippingVariation().isEmpty()) {
				request.setOwnShippingVariation(null);
			}
									
		}
		
		if(request.getTypePrice() == TypePrice.FREE) {
				
			if(Objects.nonNull(request.getPrice()) && request.getPrice().compareTo(BigDecimal.ZERO) != 0) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "OwnShipping", "api.error.own.shipping.value.bad.request");
			}	
			
			if(Objects.nonNull(request.getOwnShippingVariation()) && !request.getOwnShippingVariation().isEmpty()) {
				request.setOwnShippingVariation(null);
			}
				
		}		
			
		if(request.getTypePrice() == TypePrice.VARIABLE) {
			
			if(Objects.nonNull(request.getPrice()) && request.getPrice().compareTo(BigDecimal.ZERO) != 0) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "OwnShipping", "api.error.own.shipping.value.fixed.bad.request");
			}
			
			
			if(Objects.isNull(request.getOwnShippingVariation()) || request.getOwnShippingVariation().isEmpty()) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "OwnShipping", "api.error.own.shipping.value.variable.not.found");
			}
						
		}
	}
		
	private void validate(Optional<?> model, String field, String message) {
		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
}
