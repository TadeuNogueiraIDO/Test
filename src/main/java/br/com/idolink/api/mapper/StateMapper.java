package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.response.StateResponse;
import br.com.idolink.api.model.State;

@Component
public class StateMapper {
	

	@Autowired
	private ModelMapper mapper;

	public StateResponse response(State model) {
		return mapper.map(model, StateResponse.class);		
	}

	
	public List<StateResponse> response(List<State> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());		
	}

}
