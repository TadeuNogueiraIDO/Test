package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.response.ToolCurrencyResponse;
import br.com.idolink.api.model.ToolCurrency;

@Component
public class ToolCurrencyMapper {

	@Autowired
	private ModelMapper mapper;
	
	public ToolCurrencyResponse modelToResponse(ToolCurrency model) {
		ToolCurrencyResponse response = mapper.map(model, ToolCurrencyResponse.class);
		return response;
	}
	
	public List<ToolCurrencyResponse> modelToResponse(List<ToolCurrency> model) {
		return model.stream().map(m -> modelToResponse(m)).collect(Collectors.toList());
	}
}
