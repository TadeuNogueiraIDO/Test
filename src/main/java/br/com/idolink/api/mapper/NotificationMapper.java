package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.NotificationRequest;
import br.com.idolink.api.dto.response.NotificationResponse;
import br.com.idolink.api.model.Notification;

@Component
public class NotificationMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private StorageApi storageApi;
	
	public NotificationResponse response(Notification model) {
	
		NotificationResponse response = mapper.map(model, NotificationResponse.class);

		
		if(Objects.nonNull(model.getIcon())) {
			
			response.setIcon(storageApi.findByUuid(model.getIcon()));
			
		}
		
		
		return response;
	}

	public List<NotificationResponse> response(List<Notification> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}

	public Notification requestToModel(NotificationRequest request) {
		return mapper.map(request, Notification.class);
	}

	public NotificationResponse modelToResponse(Notification model) {
		return mapper.map(model, NotificationResponse.class);
	}

}
