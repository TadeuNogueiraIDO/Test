package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.PushNotificationTypeRequest;
import br.com.idolink.api.dto.response.PushNotificationTypeResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.PushNotificationTypeMapper;
import br.com.idolink.api.model.PushNotificationType;
import br.com.idolink.api.repository.PushNotificationTypeRepository;
import br.com.idolink.api.service.PushNotificationTypeService;

@Service
public class PushNotificationTypeServiceImpl implements PushNotificationTypeService{

	@Autowired
	private PushNotificationTypeRepository repository;
	
	@Autowired
	private PushNotificationTypeMapper mapper;
	
	@Override
	@Transactional
	public PushNotificationTypeResponse create (PushNotificationTypeRequest request) {
		
	    PushNotificationType model = mapper.requestToModel(request);
	    
	    repository.save(model);
	    
	    PushNotificationTypeResponse response = mapper.modelToResponse(model);
		
	    response.setId(model.getId());
	    
		return response;
	}
	
	@Override
	public List<PushNotificationTypeResponse> findAll() {
		
	List<PushNotificationType> model = repository.findAll();
		
	List<PushNotificationTypeResponse> response = mapper.modelToResponseList(model);
	
	return response;
		
	}
	
	@Override
	@Transactional
	public void delete(Long id ) {
		

	Optional<PushNotificationType> model = repository.findById(id);
	
	if(model.isEmpty()) {
		
		throw new BaseFullException(HttpStatus.NOT_FOUND, "NotificationType", "api.error.type.notification.not.found");
	}

	
		repository.deleteById(id);

	
	
	}

	@Override
	public PushNotificationTypeResponse findById(Long id) {
		
		Optional<PushNotificationType> model = repository.findById(id);
		
		if(model.isEmpty()) {
			
			throw new BaseFullException(HttpStatus.NOT_FOUND, "NotificationType", "api.error.type.notification.not.found");
		}
		
		PushNotificationTypeResponse response = mapper.modelToResponse(model.get());
		
		return response;
		
	}

	@Transactional
	@Override
	public PushNotificationTypeResponse update(PushNotificationTypeRequest request, Long id) {
		
		Optional<PushNotificationType> model = repository.findById(id);
		
		if(model.isEmpty()) {
			
			throw new BaseFullException(HttpStatus.NOT_FOUND, "NotificationType", "api.error.type.notification.not.found");
		}
		
		PushNotificationType modelSave = model.get();
		
		BeanUtils.copyProperties(request, modelSave , "id");
		
		repository.save(modelSave);

		PushNotificationTypeResponse response = mapper.modelToResponse(modelSave);
		
		return response;
	
	}
	
}
