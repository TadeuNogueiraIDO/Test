package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.IdoModelParamRequest;
import br.com.idolink.api.dto.response.IdoModelParamResponse;
import br.com.idolink.api.model.IdoModelParam;

@Service
public class IdoModelParamMapper {
	
	@Autowired
	private ModelMapper mapper;

	public IdoModelParamResponse response(IdoModelParam model) {
		return mapper.map(model, IdoModelParamResponse.class);		
	}

	public List<IdoModelParamResponse> response(List<IdoModelParam> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());		
	}
	
	public IdoModelParam model(IdoModelParamRequest request) {
		return mapper.map(request, IdoModelParam.class);
	}

	/**
	 * converts a IdoModelParamRequest List in IdoModelParam List
	 * @param model
	 * @return
	 */
	public List<IdoModelParam> modelList(List<IdoModelParamRequest> request) {
		return request.stream().map(m -> this.model(m)).collect(Collectors.toList());		
	}
			
}
