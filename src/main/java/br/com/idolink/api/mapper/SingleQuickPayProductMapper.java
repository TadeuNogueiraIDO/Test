package br.com.idolink.api.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.SingleQuickPayProductRequest;
import br.com.idolink.api.dto.response.SingleQuickPayProductResponse;
import br.com.idolink.api.model.SingleQuickPayProduct;

@Component
public class SingleQuickPayProductMapper {

	@Autowired
	private ModelMapper mapper;
			
	public SingleQuickPayProductResponse modelToResponse(SingleQuickPayProduct model) {
		
		SingleQuickPayProductResponse response = mapper.map(model, SingleQuickPayProductResponse.class);
		return response;
	}
	
	public SingleQuickPayProduct requestToModel(SingleQuickPayProductRequest request) {
		SingleQuickPayProduct model = mapper.map(request, SingleQuickPayProduct.class);
		return model;
	}
	
	public List<SingleQuickPayProduct> requestToModel(List<SingleQuickPayProductRequest> requests) {
		return requests.stream().map(m -> this.requestToModel(m)).collect(Collectors.toList());
	}
	
	
	public List<SingleQuickPayProductResponse> modelToResponseList(List<SingleQuickPayProduct> models) {
		
		if(Objects.nonNull(models)) {
			return models.stream().map(m -> this.modelToResponse(m)).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
			
}
