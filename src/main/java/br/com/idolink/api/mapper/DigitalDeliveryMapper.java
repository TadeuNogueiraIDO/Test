package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.DigitalDeliveryRequest;
import br.com.idolink.api.dto.response.DigitalDeliveryResponse;
import br.com.idolink.api.model.DigitalDelivery;

@Component
public class DigitalDeliveryMapper {

	@Autowired
	private ModelMapper mapper;

	public DigitalDeliveryResponse response(DigitalDelivery model) {
		DigitalDeliveryResponse response = mapper.map(model, DigitalDeliveryResponse.class);
		return response;
	}
	
	public List<DigitalDeliveryResponse> response(List<DigitalDelivery> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
			
	public DigitalDelivery model(DigitalDeliveryRequest request) {
		DigitalDelivery model =  mapper.map(request, DigitalDelivery.class);
		return model;
	}
			
}
