package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.CountryResponse;
import br.com.idolink.api.model.Country;
import br.com.idolink.api.service.MessagePropertieService;

@Service
public class CountryMapper{

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private MessagePropertieService messagePropertieService;

	public CountryResponse response(Country model) {
		CountryResponse response = mapper.map(model, CountryResponse.class);
		response.setCountry(messagePropertieService.getMessageAttribute(model.getCountry()));
		return response;
	}

	
	public List<CountryResponse> response(List<Country> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());		
	}
}
