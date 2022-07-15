package br.com.idolink.api.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.ConfigNewsletterFormRequest;
import br.com.idolink.api.dto.response.ConfigNewsletterFormResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ConfigNewsletterFormMapper;
import br.com.idolink.api.model.ConfigNewsletterForm;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ConfigNewsletterFormRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.service.ConfigNewsletterFormService;

@Service
public class ConfigNewsletterFormServiceImpl extends GenericToolsServiceImpl implements ConfigNewsletterFormService {

	@Autowired
	private ConfigNewsletterFormRepository repository;

	@Autowired
	private IdoRepository idoRepository;
	
	@Autowired
	private ConfigNewsletterFormMapper mapper;
			
	@Override
	public ConfigNewsletterFormResponse publicFindByIdo(Long idoId) {
		return this.publicFindByIdo(idoId, true);
	} 
		
	@Override
	public ConfigNewsletterFormResponse publicFindByIdo(Long idoId, boolean validation) {
		
		Optional<Ido> ido =  idoRepository.findById(idoId);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		Optional<ConfigNewsletterForm> model = repository.findByIdo(ido.get());
		
		if(validation) {
			validate(model, "Configuração", "api.error.config.newsletter.not.found");
		}
		
		return model.isPresent()? mapper.modelToResponse(model.get()) : null;
				
	}
	
	@Override
	public ConfigNewsletterFormResponse findByIdo(Long idoId) {
		return this.findByIdo(idoId, true);
	} 
		
	@Override
	public ConfigNewsletterFormResponse findByIdo(Long idoId, boolean validation) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		Optional<ConfigNewsletterForm> model = repository.findByIdo(ido.get());
		
		if(validation) {
			validate(model, "Configuração", "api.error.config.newsletter.not.found");
		}
		
		return model.isPresent()? mapper.modelToResponse(model.get()) : null;
				
	}

	@Override
	public ConfigNewsletterFormResponse findById(Long id) {
		Optional<ConfigNewsletterForm> model = repository.findById(id);
		validate(model, "Configuração", "api.error.config.newsletter.not.found");
		return mapper.modelToResponse(model.get());
	}
	
	
	@Override
	@Transactional
	public ConfigNewsletterFormResponse save(Long idoId, ConfigNewsletterFormRequest request) {
		
		ConfigNewsletterForm model = mapper.requestToModel(request);
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		model.setIdo(ido.get());	
		Optional<ConfigNewsletterForm> old = repository.findByIdo(ido.get());
		
		if(old.isPresent()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Configuração", "api.error.config.newsletter.ido.conflict");
		}
					
		model.setIdo(ido.get());
		model = repository.save(model);
		
		super.createTools(ToolCodName.NEWSLETTER, idoId, model.getId());
						
		return mapper.modelToResponse(model);
	}

	@Override
	@Transactional
	public ConfigNewsletterFormResponse update(Long id, ConfigNewsletterFormRequest request) {

		ConfigNewsletterForm model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Configuração", "api.error.config.newsletter.not.found"));
	
		ConfigNewsletterForm configContactUs = mapper.requestToModel(request);
		BeanUtils.copyProperties(configContactUs, model, "id","dateModel", "ido");
		model = repository.save(model);
		return mapper.modelToResponse(model);
	}

	@Override
	@Transactional
	public void delete(Long id) {

		ConfigNewsletterForm model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"Configuração", "api.error.config.newsletter.not.found"));
		try {

			repository.delete(model);
			repository.flush();
			
			Long idIdo = model.getIdo().getId();
			super.deleteTools(ToolCodName.NEWSLETTER, idIdo, id, null);
			
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Configuração", "api.error.config.newsletter.conflict");
		}

	}
	
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
			
}

