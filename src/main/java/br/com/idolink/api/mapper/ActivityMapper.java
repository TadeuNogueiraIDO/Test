package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.ActivityRequest;
import br.com.idolink.api.dto.response.ActivityFullResponse;
import br.com.idolink.api.dto.response.ActivityResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.model.Activity;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class ActivityMapper{
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private StorageApi storageApi;

	@Autowired
	private MessagePropertieService messagePropertieService;
	
	public ActivityResponse response(Activity model) {
		ActivityResponse response =  mapper.map(model, ActivityResponse.class);
		response.setSegment(messagePropertieService.getMessageAttribute(model.getSegment()));
		return response;
	}

	public List<ActivityResponse> response(List<Activity> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
	
	public ActivityFullResponse responseFull(Activity model) {
		ActivityFullResponse response =  mapper.map(model, ActivityFullResponse.class);
		response.setSegment(messagePropertieService.getMessageAttribute(model.getSegment()));
		
		try {
			if (Objects.nonNull(model.getImgUiid())) {
				response.setImg(storageApi.findByUuid(model.getImgUiid()));
			}
		} catch (Exception e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Imagem", "api.error.image.inconsistency");
		}
		return response;
	}

	public Activity model(ActivityRequest request) {
		return mapper.map(request, Activity.class);
	}

	public List<Activity> modelList(List<ActivityRequest> request) {
		return request.stream().map(m -> this.model(m)).collect(Collectors.toList());
	}
}
