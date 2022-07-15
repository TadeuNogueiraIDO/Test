package br.com.idolink.api.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.ConfigContactUsRequest;
import br.com.idolink.api.dto.response.ConfigContactUsResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ConfigContactUsMapper;
import br.com.idolink.api.model.ConfigContactUs;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ConfigContactUsRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.service.ConfigContactUsService;

@Service
public class ConfigContactUsServiceImpl extends GenericToolsServiceImpl implements ConfigContactUsService {

	@Autowired
	private ConfigContactUsRepository repository;

	@Autowired
	private IdoRepository idoRepository;
	
	@Autowired
	private ConfigContactUsMapper mapper;
	
	@Override
	public ConfigContactUsResponse publicFindByIdo(Long idoId) {
		 return this.publicFindByIdo(idoId, true);
	} 
		
	@Override
	public ConfigContactUsResponse publicFindByIdo(Long idoId, boolean validation) {
		
		Optional<Ido> ido =  idoRepository.findById(idoId);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		Optional<ConfigContactUs> model = repository.findByIdo(ido.get());
		
		if(validation) {
		   validate(model, "ConfigContactUs", "api.error.contact.us.not.found");
		}
						
		return model.isPresent()? mapper.modelToResponse(model.get()) : null;
	}

		
	@Override
	public ConfigContactUsResponse findByIdo(Long idoId) {
		 return this.findByIdo(idoId, true);
	} 
		
	@Override
	public ConfigContactUsResponse findByIdo(Long idoId, boolean validation) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		Optional<ConfigContactUs> model = repository.findByIdo(ido.get());
		
		if(validation) {
		   validate(model, "ConfigContactUs", "api.error.contact.us.not.found");
		}
						
		return model.isPresent()? mapper.modelToResponse(model.get()) : null;
	}

	@Override
	public ConfigContactUsResponse findById(Long id) {
		Optional<ConfigContactUs> model = repository.findById(id);
		validate(model, "ConfigContactUs", "api.error.contact.us.not.found");
		return mapper.modelToResponse(model.get());
	}
	
	
	@Override
	@Transactional
	public ConfigContactUsResponse save(Long idoId, ConfigContactUsRequest request) {
		
		ConfigContactUs model = mapper.requestToModel(request);
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		model.setIdo(ido.get());	
		Optional<ConfigContactUs> old = repository.findByIdo(ido.get());
		
		if(old.isPresent()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "ConfigContactUs", "api.error.contact.us.ido.conflict");
		}
					
		model.setIdo(ido.get());
		model = repository.save(model);
		
		
		super.createTools(ToolCodName.CONTACTUS, idoId, model.getId());
						
		return mapper.modelToResponse(model);
	}

	@Override
	@Transactional
	public ConfigContactUsResponse update(Long id, ConfigContactUsRequest request) {

		ConfigContactUs model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "ConfigContactUs", "api.error.contact.us.not.found"));
	
		ConfigContactUs configContactUs = mapper.requestToModel(request);
		BeanUtils.copyProperties(configContactUs, model, "id","dateModel", "ido");
		model = repository.save(model);
		return mapper.modelToResponse(model);
	}

	@Override
	@Transactional
	public void delete(Long id) {

		ConfigContactUs model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"ConfigContactUs", "api.error.contact.us.not.found"));
		try {
					
			repository.delete(model);
			repository.flush();
			
			Long idoId = model.getIdo().getId();
			super.deleteTools(ToolCodName.CONTACTUS, idoId, id, null);
			
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "ConfigContactUs", "api.error.contact.us.conflict");
		}

	}
	
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
			
}

