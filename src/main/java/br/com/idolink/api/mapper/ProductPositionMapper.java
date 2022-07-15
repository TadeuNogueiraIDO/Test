package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.ProductPositionRequest;
import br.com.idolink.api.dto.response.ProductPositionResponse;
import br.com.idolink.api.model.ProductPosition;
import br.com.idolink.api.model.ShopCategory;
import br.com.idolink.api.model.ShopProduct;

@Component
public class ProductPositionMapper {

	
	@Autowired
	private ModelMapper mapper;
	
	public  ProductPosition requestToModel( ProductPositionRequest request,ShopCategory shopCategory, ShopProduct category) {
		
		ProductPosition model = new ProductPosition(); 
		
		model.setPosition(request.getPosition());
		model.setShopCategory(shopCategory);
		model.setShopProduct(category);
				
		return model;
	}
		
	public ProductPositionResponse modelToResponse(ProductPosition model) {
		ProductPositionResponse response =  mapper.map(model,ProductPositionResponse.class);
		return response;
	}
	
	public List<ProductPositionResponse> modelToResponse(List<ProductPosition> model) {
		return model.stream().map(m -> modelToResponse(m)).collect(Collectors.toList());
	}
		
}