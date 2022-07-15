package br.com.idolink.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.NotificationSettingRequest;
import br.com.idolink.api.dto.response.NotificationSettingResponse;
import br.com.idolink.api.model.NotificationSetting;
import br.com.idolink.api.model.User;

@Component
public class NotificationSettingMapper {

	@Autowired
	private ModelMapper mapper;
	
	public NotificationSettingResponse response(NotificationSetting model) {
		return mapper.map(model,NotificationSettingResponse.class);		
	}
	
//	public List<NotificationSettingResponse> response(List<NotificationSetting> model) {
//		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());		
//	}
	public NotificationSetting save (NotificationSettingRequest request, User user) {
		NotificationSetting model = mapper.map(request, NotificationSetting.class);
		model.setUser(user);
		return model;
	}
}
