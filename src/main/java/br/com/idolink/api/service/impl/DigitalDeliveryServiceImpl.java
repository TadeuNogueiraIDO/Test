package br.com.idolink.api.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.DigitalDeliveryRequest;
import br.com.idolink.api.dto.response.DigitalDeliveryResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.DigitalDeliveryMapper;
import br.com.idolink.api.model.DigitalDelivery;
import br.com.idolink.api.model.ShippingSettings;
import br.com.idolink.api.repository.DigitalDeliveryRepository;
import br.com.idolink.api.repository.ShippingSettingsRepository;
import br.com.idolink.api.service.DigitalDeliveryService;

@Service
public class DigitalDeliveryServiceImpl implements DigitalDeliveryService {

	@Autowired
	private DigitalDeliveryMapper mapper;

	@Autowired
	private DigitalDeliveryRepository repository;
	
	@Autowired
	private ShippingSettingsRepository settingsRepository;

	@Override
	public DigitalDeliveryResponse findByShippingSettings(Long settingsId) {

		Optional<ShippingSettings> settings = settingsRepository.findById(settingsId);
		validate(settings, "ShippingSettings", "api.error.shipping.setting.not.found");
		
		Optional<DigitalDelivery> model = repository.findByShippingSettings(settings.get());
		validate(model, "DigitalDelivery", "api.error.digital.delivey.not.found");
		
		return mapper.response(model.get());
		
	}

	@Override
	public DigitalDeliveryResponse findById(Long id) {
		Optional<DigitalDelivery> model = repository.findById(id);
		validate(model, "DigitalDelivery", "api.error.digital.delivey.not.found");
		return mapper.response(model.get());
	}

	@Transactional
	@Override
	public DigitalDeliveryResponse create(Long settingsId, DigitalDeliveryRequest request) {

		Optional<ShippingSettings> settings = settingsRepository.findById(settingsId);
		validate(settings, "ShippingSettings", "api.error.shipping.setting.not.found");
		
		Optional<DigitalDelivery> shippingOld = repository.findByShippingSettings(settings.get());
		
		if(shippingOld.isPresent()) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "DigitalDelivery", "api.error.digital.delivey.ido.conflict");
		}
								
		DigitalDelivery model = mapper.model(request);
		model.setShippingSettings(settings.get());
		model = repository.save(model);
			
				
		return mapper.response(model);
	}

	@Transactional
	@Override
	public DigitalDeliveryResponse update(Long id, DigitalDeliveryRequest request) {

		@SuppressWarnings("serial")
		DigitalDelivery model = repository.findById(id).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,
				"DigitalDelivery", "api.error.digital.delivey.not.found") {
		});
						
		DigitalDelivery modelNew = mapper.model(request);
								
		BeanUtils.copyProperties(modelNew, model, "id", "dateModel", "shippingSettings");
						
		return mapper.response(repository.save(model));
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Optional<DigitalDelivery> model = repository.findById(id);
		validate(model, "DigitalDelivery", "api.error.digital.delivey.not.found");
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "DigitalDelivery", "api.error.digital.delivey.conflict");
		}
	}

	
		
	private void validate(Optional<?> model, String field, String message) {
		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
}
