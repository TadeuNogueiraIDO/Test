package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.IdoToolPositionRequest;
import br.com.idolink.api.dto.response.IdoToolPositionResponse;
import br.com.idolink.api.model.IdoToolPosition;

@Component
public class IdoToolPositionMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	
	public IdoToolPosition requestToModel(IdoToolPositionRequest request) {
		return mapper.map(request, IdoToolPosition.class);
	}
	
	public IdoToolPositionResponse modelToResponse(IdoToolPosition model) {
		IdoToolPositionResponse response =  mapper.map(model, IdoToolPositionResponse.class);
		return response;
	}
	
	public List<IdoToolPositionResponse> modelToResponse(List<IdoToolPosition> model) {
		return model.stream().map(m -> modelToResponse(m)).collect(Collectors.toList());
	}
			
}
