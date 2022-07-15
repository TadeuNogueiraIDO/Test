package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.AppearanceButtonRequest;
import br.com.idolink.api.dto.response.AppearanceButtonResponse;
import br.com.idolink.api.model.AppearanceButton;
import br.com.idolink.api.model.Ido;

@Component
public class AppearanceButtonMapper {

	@Autowired
	private ModelMapper mapper;
	
	public AppearanceButton save (AppearanceButtonRequest request, Ido ido) {
		AppearanceButton model = mapper.map(request, AppearanceButton.class);
		model.setIdo(ido);
		return model;
	}
	
	public AppearanceButtonResponse response(AppearanceButton model) {
		return mapper.map(model, AppearanceButtonResponse.class);		
	}
	
	public List<AppearanceButtonResponse> response(List<AppearanceButton> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());		
	}
	
	public AppearanceButton model(AppearanceButtonRequest request) {
		return mapper.map(request, AppearanceButton.class);
	}
	
	public AppearanceButtonResponse modelToResponse(AppearanceButton model) {
		return mapper.map(model, AppearanceButtonResponse.class);
	}
	
	public AppearanceButton requestToModel(AppearanceButtonRequest request) {
		return mapper.map(request, AppearanceButton.class);
	}
}
