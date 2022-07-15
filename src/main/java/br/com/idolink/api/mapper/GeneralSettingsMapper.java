package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.GeneralSettingsRequest;
import br.com.idolink.api.dto.response.GeneralSettingsResponse;
import br.com.idolink.api.dto.response.UserStatusResponse;
import br.com.idolink.api.model.GeneralSettings;
import br.com.idolink.api.model.User;

@Component
public class GeneralSettingsMapper {

	@Autowired
	private ModelMapper mapper;

	public GeneralSettingsResponse response(GeneralSettings model) {

		return mapper.map(model, GeneralSettingsResponse.class);

	}

	public List<GeneralSettingsResponse> response(List<GeneralSettings> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}

	public UserStatusResponse modelToResponse(User model) {
		return mapper.map(model, UserStatusResponse.class);
	}

	public GeneralSettingsResponse modelToResponse(GeneralSettings model) {
		return mapper.map(model, GeneralSettingsResponse.class);
	}

	public GeneralSettings model(GeneralSettingsRequest request) {
		return mapper.map(request, GeneralSettings.class);

	}

	public GeneralSettings requestToModel(GeneralSettingsRequest request) {
		return mapper.map(request, GeneralSettings.class);
	}
}