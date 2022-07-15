package br.com.idolink.api.service.impl;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.PostOfficeShippingRequest;
import br.com.idolink.api.dto.response.PostOfficeShippingResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.PostOfficeShippingMapper;
import br.com.idolink.api.model.PostOfficeShipping;
import br.com.idolink.api.model.ShippingSettings;
import br.com.idolink.api.model.enums.PostOfficeServiceOption;
import br.com.idolink.api.repository.PostOfficeShippingRepository;
import br.com.idolink.api.repository.ShippingSettingsRepository;
import br.com.idolink.api.service.PostOfficeShippingService;

@Service
public class PostOfficeShippingServiceImpl implements PostOfficeShippingService {

	@Autowired
	private PostOfficeShippingMapper mapper;

	@Autowired
	private PostOfficeShippingRepository repository;
	
	@Autowired
	private ShippingSettingsRepository settingsRepository;

	@Override
	public PostOfficeShippingResponse findByShippingSettings(Long settingsId) {

		Optional<ShippingSettings> settings = settingsRepository.findById(settingsId);
		validate(settings, "ShippingSettings", "api.error.shipping.setting.not.found");
		
		Optional<PostOfficeShipping> model = repository.findByShippingSettings(settings.get());
		validate(model,"PostOfficeShipping", "api.error.post.office.not.found");
		
		return mapper.response(model.get());
		
	}

	@Override
	public PostOfficeShippingResponse findById(Long id) {
		Optional<PostOfficeShipping> model = repository.findById(id);
		validate(model,"PostOfficeShipping", "api.error.post.office.not.found");
		return mapper.response(model.get());
	}

	@Transactional
	@Override
	public PostOfficeShippingResponse create(Long settingsId, PostOfficeShippingRequest request) {

		Optional<ShippingSettings> settings = settingsRepository.findById(settingsId);
		validate(settings, "ShippingSettings", "api.error.shipping.setting.not.found");
		validateServiceOption(request);
		
		
		Optional<PostOfficeShipping> shippingOld = repository.findByShippingSettings(settings.get());
		
		if(shippingOld.isPresent()) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "PostOfficeShipping", "api.error.post.office.shop.conflict");
		}
		
		PostOfficeShipping model = mapper.model(request);
		model.setShippingSettings(settings.get());
		model = repository.save(model);
		
		return mapper.response(model);
	}

	@Transactional
	@Override
	public PostOfficeShippingResponse update(Long id, PostOfficeShippingRequest request) {

		@SuppressWarnings("serial")
		PostOfficeShipping model = repository.findById(id).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,
				"ShippingSettings", "api.error.shipping.setting.not.found") {
		});
		validateServiceOption(request);
				
		PostOfficeShipping modelNew = mapper.model(request);
		
		BeanUtils.copyProperties(modelNew, model, "id", "dateModel", "shippingSettings");
						
		return mapper.response(repository.save(model));
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Optional<PostOfficeShipping> model = repository.findById(id);
		validate(model, "PostOfficeShipping", "api.error.post.office.not.found");
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "PostOfficeShipping", "api.error.post.office.conflict");
		}
	}

	private void validateServiceOption(PostOfficeShippingRequest request) {
		
		if(request.getServiceOption().equals(PostOfficeServiceOption.CONVENTIONAL)) {
			request.setAdmCode(null);
			request.setAdmPassword(null);
		}else {
			
			if(Objects.isNull(request.getAdmCode()) || Objects.isNull(request.getAdmPassword())) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "PostOfficeShipping", "api.error.post.office.password.needed");
			}
						
		}
	}
	
	
	
	private void validate(Optional<?> model, String field, String message) {
		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
}
