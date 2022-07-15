package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.FaqRequest;
import br.com.idolink.api.dto.response.FaqResponse;
import br.com.idolink.api.model.Faq;

@Service
public class FaqMapper {

	@Autowired
	private ModelMapper mapper;
		
	public FaqResponse modelToResponse(Faq model) {		
		return mapper.map(model, FaqResponse.class);
	
	}
	
	public List<FaqResponse> modelToResponse(List<Faq> model) {
		return model.stream().map(m -> modelToResponse(m)).collect(Collectors.toList());
	}

	public Faq requestToModel(FaqRequest request) {
		return mapper.map(request, Faq.class);
	}
}
