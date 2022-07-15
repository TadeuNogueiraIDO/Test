package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.AnalyticsMarketingRequest;
import br.com.idolink.api.dto.response.AnalyticsMarketingResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.AnalyticsMarketingMapper;
import br.com.idolink.api.model.AnalyticsMarketing;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.repository.AnalyticsMarketingRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.service.AnalyticsMarketingService;

@Service
public class AnalyticsMarketingServiceImpl implements AnalyticsMarketingService {

	@Autowired
	private AnalyticsMarketingRepository repository;
	
	@Autowired
	private IdoRepository idoRepository;
		
	@Autowired
	private AnalyticsMarketingMapper mapper;
	
	@Override
	public List<AnalyticsMarketingResponse> findAll(){
		List<AnalyticsMarketing> models = repository.findAll();
		return mapper.response(models);
	}
	
	
	@Override
	public AnalyticsMarketingResponse findById(Long id) {
		Optional<AnalyticsMarketing> model = repository.findById(id);
		validate(model, "Analytics Marketing", "api.error.analytics.marketing.not.found");
		return mapper.response(model.get());
	}

	@Override
	@Transactional
	public AnalyticsMarketingResponse create(Long idoId, AnalyticsMarketingRequest request) {
						
		AnalyticsMarketing model = mapper.model(request);
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		repository.save(model);
		return mapper.response(model);
	}

	@Override
	@Transactional
	public AnalyticsMarketingResponse update(Long id, AnalyticsMarketingRequest request) {

		@SuppressWarnings("serial")
		AnalyticsMarketing model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "AnalyticsMarketing", "api.error.analytics.marketing.not.found") {
				});
		BeanUtils.copyProperties(request, model, "id","dateModel", "ido");
						
		repository.save(model);
		return mapper.response(model);

	}

	@Transactional
	public void delete(Long id) {

		Optional<AnalyticsMarketing> model = repository.findById(id);
		validate(model,"Analytics Marketing", "api.error.analytics.marketing.not.found");
		
		try {
			
			repository.deleteById(id);
			repository.flush();
						
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST,"AnalyticsMarketing", "api.error.analytics.marketing.conflict");
		}
				
	}
			
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
		
}
