package br.com.idolink.api.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.ShopQuickPayProductRequest;
import br.com.idolink.api.dto.response.ShopQuickPayProductResponse;
import br.com.idolink.api.model.ShopQuickPayProduct;

@Component
public class ShopQuickPayProductMapper {

	@Autowired
	private ModelMapper mapper;
			
	public ShopQuickPayProductResponse modelToResponse(ShopQuickPayProduct model) {
		ShopQuickPayProductResponse response = mapper.map(model, ShopQuickPayProductResponse.class);
		return response;
	}
	
	public ShopQuickPayProduct requestToModel(ShopQuickPayProductRequest request) {
		ShopQuickPayProduct model = mapper.map(request, ShopQuickPayProduct.class);
		return model;
	}
	
	public List<ShopQuickPayProduct> requestToModel(List<ShopQuickPayProductRequest> requests) {
		return requests.stream().map(m -> this.requestToModel(m)).collect(Collectors.toList());
	}
	
	
	public List<ShopQuickPayProductResponse> modelToResponseList(List<ShopQuickPayProduct> models) {
		
		if(Objects.nonNull(models)) {
			return models.stream().map(m -> this.modelToResponse(m)).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
			
}
