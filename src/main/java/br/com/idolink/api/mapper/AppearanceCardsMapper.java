package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.AppearanceCardsRequest;
import br.com.idolink.api.dto.response.AppearanceCardsResponse;
import br.com.idolink.api.model.AppearanceCards;
import br.com.idolink.api.model.Ido;

@Component
public class AppearanceCardsMapper {

	@Autowired
	private ModelMapper mapper;
	
	public AppearanceCards save (AppearanceCardsRequest request, Ido ido) {
		AppearanceCards model = mapper.map(request, AppearanceCards.class);
		model.setIdo(ido);
		return model;
	}
	
	public AppearanceCardsResponse response(AppearanceCards model) {
		return mapper.map(model, AppearanceCardsResponse.class);		
	}
	
	public List<AppearanceCardsResponse> response(List<AppearanceCards> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());		
	}
	
	public AppearanceCards model(AppearanceCardsRequest request) {
		return mapper.map(request, AppearanceCards.class);
	}
	
	public AppearanceCardsResponse modelToResponse(AppearanceCards model) {
		return mapper.map(model, AppearanceCardsResponse.class);
	}
	
	public AppearanceCards requestToModel(AppearanceCardsRequest request) {
		return mapper.map(request, AppearanceCards.class);
	}
}
