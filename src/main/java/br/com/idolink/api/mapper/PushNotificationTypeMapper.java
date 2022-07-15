package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.PushNotificationTypeRequest;
import br.com.idolink.api.dto.response.PushNotificationTypeResponse;
import br.com.idolink.api.model.PushNotificationType;

@Component
public class PushNotificationTypeMapper {
	
	@Autowired
	private StorageApi storageApi;

	@Autowired
	private ModelMapper mapper;
	
	public PushNotificationType requestToModel(PushNotificationTypeRequest request) {
		return mapper.map(request, PushNotificationType.class);
		
	}
	public PushNotificationTypeResponse modelToResponse(PushNotificationType model) {
		
		PushNotificationTypeResponse response = mapper.map(model, PushNotificationTypeResponse.class);
		if(Objects.nonNull(model.getIcon())) {
			response.setIcon(storageApi.findFileById(model.getIcon()));
		}
		return response;
		
	}
	public List<PushNotificationTypeResponse> modelToResponseList(List<PushNotificationType> model){
		return model.stream().map(m -> this.modelToResponse(m)).collect(Collectors.toList());
	}
	

}
