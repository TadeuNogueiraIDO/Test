package br.com.idolink.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.response.BankResponse;
import br.com.idolink.api.model.Bank;

@Component
public class BankMapper {

	@Autowired
	private ModelMapper mapper;
	
	public BankResponse modelToResponse(Bank model) {
		return mapper.map(model, BankResponse.class);
	}
	
}
