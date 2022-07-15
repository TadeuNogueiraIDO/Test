package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.LocalPickupRequest;
import br.com.idolink.api.dto.response.LocalPickupResponse;
import br.com.idolink.api.model.LocalPickup;

@Component
public class LocalPickupMapper {

	@Autowired
	private ModelMapper mapper;

	public LocalPickupResponse response(LocalPickup model) {
		LocalPickupResponse response = mapper.map(model, LocalPickupResponse.class);
		return response;
	}
	
	public List<LocalPickupResponse> response(List<LocalPickup> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
			
	public LocalPickup model(LocalPickupRequest request) {
		LocalPickup model =  mapper.map(request, LocalPickup.class);
		return model;
	}
			
}
