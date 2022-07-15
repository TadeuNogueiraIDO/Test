package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.response.CurrencyResponse;
import br.com.idolink.api.model.Currency;

@Component
public class CurrencyMapper {

	@Autowired
	private ModelMapper mapper;

	public CurrencyResponse response(Currency model) {
		return mapper.map(model, CurrencyResponse.class);
	}

	public List<CurrencyResponse> response(List<Currency> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
}
