package br.com.idolink.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.IdoAnalyticsMarketingRequest;
import br.com.idolink.api.dto.request.IdoAnalyticsMarketingRequestList;
import br.com.idolink.api.dto.response.IdoAnalyticsMarketingResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.IdoAnalyticsMarketingMapper;
import br.com.idolink.api.model.AnalyticsMarketing;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoAnalyticsMarketing;
import br.com.idolink.api.repository.AnalyticsMarketingRepository;
import br.com.idolink.api.repository.IdoAnalyticsMarketingRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.service.IdoAnalyticsMarketingService;

@Service
public class IdoAnalyticsMarketingServiceImpl implements IdoAnalyticsMarketingService {

	@Autowired
	private IdoAnalyticsMarketingRepository repository;
	
	@Autowired
	private AnalyticsMarketingRepository analyticsMarketingRepository;
	
	
	@Autowired
	private IdoRepository idoRepository;
		
	@Autowired
	private IdoAnalyticsMarketingMapper mapper;
	
	@Override
	public List<IdoAnalyticsMarketingResponse> listByIdo(Long idoId) {

		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		List<IdoAnalyticsMarketing> model = repository.findByIdo(ido.get());
		return mapper.response(model);
	}

	@Override
	public IdoAnalyticsMarketingResponse findById(Long id) {
		Optional<IdoAnalyticsMarketing> model = repository.findById(id);
		validate(model, "Analytics Marketing", "api.error.analytics.marketing.not.found");

		return mapper.response(model.get());
	}

	@Override
	@Transactional
	public List<IdoAnalyticsMarketingResponse> createOrUpdate(Long idoId, IdoAnalyticsMarketingRequestList request) {
			
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
				
		List<IdoAnalyticsMarketingRequest> listRequest = request.getAnalyticsMarketingRequests();
		List<IdoAnalyticsMarketingResponse> listResponse = new ArrayList<>();
				
		for (IdoAnalyticsMarketingRequest idoAnalyticsMarketingRequest : listRequest) {
			
			Optional<AnalyticsMarketing> analyticsMarketing =  analyticsMarketingRepository.findById(idoAnalyticsMarketingRequest.getAnalyticsMarketingId());
			validate(analyticsMarketing, "Analytics Marketing", "api.error.analytics.marketing.not.found");
							
			Optional<IdoAnalyticsMarketing> idoAnalyticsMarketingOp =  repository.findByIdoAndAnalyticsMarketing(ido.get(), analyticsMarketing.get());
			
			IdoAnalyticsMarketing model = new IdoAnalyticsMarketing();
			
			
			if(idoAnalyticsMarketingOp.isPresent()) {
				model = idoAnalyticsMarketingOp.get();
				BeanUtils.copyProperties(idoAnalyticsMarketingRequest, model, "id","dateModel", "ido", "analyticsMarketing");
				
				
			}else {
				
				model =  mapper.model(idoAnalyticsMarketingRequest);
				model.setIdo(ido.get());
				model.setAnalyticsMarketing(analyticsMarketing.get());
			}
			
			model.setIsActive(idoAnalyticsMarketingRequest.isActive());
			repository.save(model);
			listResponse.add(mapper.response(model));
			
		}
				
		return listResponse;
	}
	
	@Transactional
	public void delete(Long id) {

		Optional<IdoAnalyticsMarketing> model = repository.findById(id);
		validate(model,"Analytics Marketing", "api.error.analytics.marketing.not.found");
		
		try {
			
			repository.deleteById(id);
			repository.flush();
						
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST,"IdoAnalyticsMarketing", "api.error.analytics.marketing.conflict");
		}
				
	}
			
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
			
}
