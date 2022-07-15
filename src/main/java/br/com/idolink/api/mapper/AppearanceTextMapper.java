package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.AppearanceTextRequest;
import br.com.idolink.api.dto.response.AppearanceTextResponse;
import br.com.idolink.api.model.AppearanceText;
import br.com.idolink.api.model.Ido;

@Component
public class AppearanceTextMapper {

	@Autowired
	private ModelMapper mapper;
	
	public AppearanceText save (AppearanceTextRequest request, Ido ido) {
		AppearanceText model = mapper.map(request, AppearanceText.class);
		model.setIdo(ido);
		return model;
	}
	
	public AppearanceTextResponse response(AppearanceText model) {
		return mapper.map(model, AppearanceTextResponse.class);		
	}
	
	public List<AppearanceTextResponse> response(List<AppearanceText> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());		
	}
	
	public AppearanceText model(AppearanceTextRequest request) {
		return mapper.map(request, AppearanceText.class);
	}
	
	public AppearanceTextResponse modelToResponse(AppearanceText model) {
		return mapper.map(model, AppearanceTextResponse.class);
	}
	
	public AppearanceText requestToModel(AppearanceTextRequest request) {
		return mapper.map(request, AppearanceText.class);
	}
}
