package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.OwnShippingRequest;
import br.com.idolink.api.dto.response.OwnShippingResponse;
import br.com.idolink.api.model.OwnShipping;

@Component
public class OwnShippingMapper {

	@Autowired
	private ModelMapper mapper;

	public OwnShippingResponse response(OwnShipping model) {
		OwnShippingResponse response = mapper.map(model, OwnShippingResponse.class);
		return response;
	}
	
	public List<OwnShippingResponse> response(List<OwnShipping> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
			
	public OwnShipping model(OwnShippingRequest request) {
		OwnShipping model =  mapper.map(request, OwnShipping.class);
		return model;
	}
			
}
