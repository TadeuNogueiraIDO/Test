package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.ConfigFaqRequest;
import br.com.idolink.api.dto.request.FaqRequest;
import br.com.idolink.api.dto.response.ConfigFaqResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ConfigFaqMapper;
import br.com.idolink.api.mapper.FaqMapper;
import br.com.idolink.api.model.ConfigFaq;
import br.com.idolink.api.model.Faq;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ConfigFaqRepository;
import br.com.idolink.api.repository.FaqRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.service.ConfigFaqService;

@Service
public class ConfigFaqServiceImpl extends GenericToolsServiceImpl implements ConfigFaqService {

	@Autowired
	private FaqRepository faqRepository;
	
	@Autowired
	private ConfigFaqRepository repository;
	
	@Autowired
	private IdoRepository idoRepository;
	
	@Autowired
	private ConfigFaqMapper mapper;
	
	@Autowired
	private FaqMapper faqMapper;
		
	@Override
	public ConfigFaqResponse findById(Long id) {
		ConfigFaq model = validate(id);
		return mapper.modelToResponse(model);
	}
	
	public ConfigFaqResponse findByIdo(Long idoid) {
		return this.findByIdo(idoid, true);
	} 
	
	public ConfigFaqResponse publicFindByIdo(Long idoid) {
		return this.publicFindByIdo(idoid, true);
	} 
	
	
	@Override
	public ConfigFaqResponse publicFindByIdo(Long idoid, boolean validation) {
		
		Optional<Ido> ido =  idoRepository.findById(idoid);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		Optional<ConfigFaq> model = repository.findByIdo(ido.get());
		
		if(validation) {
			if(model.isEmpty()) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "FAQ", "api.error.ido.not.found");
			}
		}
		return model.isPresent()? mapper.modelToResponse(model.get()) : null;
	}

	
	
	@Override
	public ConfigFaqResponse findByIdo(Long idoid, boolean validation) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoid);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		Optional<ConfigFaq> model = repository.findByIdo(ido.get());	
		
		if(validation) {
			if(model.isEmpty()) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "FAQ", "api.error.ido.not.found");
			}
		}
		return model.isPresent()? mapper.modelToResponse(model.get()) : null;
	}

	@Override
	@Transactional
	public ConfigFaqResponse create(Long idoid, ConfigFaqRequest request) {
		
		for(FaqRequest validate : request.getFaqs()) {
			
			validateQuantCaracteres(validate.getAnswer(), 800);
			validateQuantCaracteres(validate.getQuestion(), 100);
			
		}
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoid);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		Optional<ConfigFaq> config = repository.findByIdo(ido.get()); 
		
		if(config.isPresent()) {
			throw new BaseFullException(HttpStatus.CONFLICT, "FAQ", "api.error.config.faq.ido.conflict");
		}
		
		ConfigFaq model = mapper.requestToModel(request, ido.get());
				
		model = repository.save(model);
		
		createFaqs(request.getFaqs(), model);
		
		super.createTools(ToolCodName.FAQ, idoid, model.getId());
								
		return mapper.modelToResponse(model);
	}

	
	@Override
	@Transactional
	public ConfigFaqResponse update(ConfigFaqRequest request, Long id) {
		
		ConfigFaq model = validate(id);
		BeanUtils.copyProperties(request, model, "dateModel", "ido");
		
		deleteFaqs(model);
		createFaqs(request.getFaqs(), model);
				
		model = repository.save(model);
		return mapper.modelToResponse(model);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {

		ConfigFaq model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"FAQ", "api.error.ido.not.found"));
		try {
			
			deleteFaqs(model);			
			repository.delete(model);
			repository.flush();
			
			Long idIdo = model.getIdo().getId();
			super.deleteTools(ToolCodName.FAQ, idIdo, id, null);
			
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "FAQ", "api.error.config.faq.conflict");
		}

	}
	
	
	private ConfigFaq validate(Long id) {
		Optional<ConfigFaq> model = repository.findById(id);
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "FAQ", "api.error.ido.not.found");
		}
		return model.get();
	}
	
	
	@Transactional
	private void createFaqs(List<FaqRequest> faqsRequest, ConfigFaq model) {
		
		if(Objects.nonNull(faqsRequest) && !faqsRequest.isEmpty()) {	
			
			for (FaqRequest faqRequest : faqsRequest) {
				Faq modelFaq = faqMapper.requestToModel(faqRequest);
				modelFaq.setConfigFaq(model);
				faqRepository.save(modelFaq);
			}
			} else
				
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "FAQ", "api.error.config.faq.create.conflict");
	}	
		
	
	
	@Transactional
	private void deleteFaqs(ConfigFaq model) {
		
		List<Faq> faqs = faqRepository.findByConfigFaq(model);
				
		if(Objects.nonNull(faqs) && !faqs.isEmpty()) {
			for (Faq faq : faqs) {
				faqRepository.delete(faq);
				repository.flush();
			}
		}
	}
	
	private void validateQuantCaracteres(String base, int max) {
		
		if(base.length() > max && max<=100) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Faq",
					"api.error.faq.type.conflict");
		}
		
		if(base.length() > max && max>100) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Faq",
					"api.error.faq.question.type.conflict");
		}
		
	}

		
}
