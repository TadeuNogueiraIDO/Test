package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.TimezoneRequest;
import br.com.idolink.api.dto.response.TimezoneResponse;
import br.com.idolink.api.model.Timezone;

@Component
public class TimezoneMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public TimezoneResponse response (Timezone model) {
		return mapper.map(model, TimezoneResponse.class);		
	}
	
	public List<TimezoneResponse> response(List<Timezone> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());		
	}
	
	public Timezone model(TimezoneRequest request) {         
		return mapper.map(request, Timezone.class); 
	}     
	
	public List<Timezone> modelList(List<TimezoneRequest> request) {         
		return request.stream().map(m -> this.model(m)).collect(Collectors.toList());     
	}
}
