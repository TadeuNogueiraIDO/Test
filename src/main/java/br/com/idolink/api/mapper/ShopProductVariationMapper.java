package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.ShopProductDigitalVariationRequest;
import br.com.idolink.api.dto.request.ShopProductPhysicalVariationRequest;
import br.com.idolink.api.dto.response.ShopProductDigitalVariationResponse;
import br.com.idolink.api.dto.response.ShopProductPhysicalVariationResponse;
import br.com.idolink.api.model.ShopProductVariation;

@Component
public class ShopProductVariationMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public ShopProductPhysicalVariationResponse responsePhysical(ShopProductVariation model) {
		
		ShopProductPhysicalVariationResponse response = mapper.map(model, ShopProductPhysicalVariationResponse.class);
		
		response.setHasFilterControl( Objects.isNull(model.getFilterType()) || Objects.isNull(model.getFilterValue()) ? false : true);
		
		if(Objects.isNull(model.getWeight()) && Objects.isNull(model.getWidth()) && Objects.isNull(model.getHeight()) && Objects.isNull(model.getLength())) {
			response.setHasDimensionControl(false);
		}else {
			response.setHasDimensionControl(true);
		}
		
		return response;
	}
	
	public ShopProductDigitalVariationResponse responseDigital(ShopProductVariation model) {
		return mapper.map(model, ShopProductDigitalVariationResponse.class);
	}

	public List<ShopProductPhysicalVariationResponse> responsePhysical(List<ShopProductVariation> model) {
		return model.stream().map(m -> this.responsePhysical(m)).collect(Collectors.toList());
	}

	public List<ShopProductDigitalVariationResponse> responseDigital(List<ShopProductVariation> model) {
		return model.stream().map(m -> this.responseDigital(m)).collect(Collectors.toList());
	}
	
	
	public ShopProductVariation modelPhysical(ShopProductPhysicalVariationRequest request) {
		
		ShopProductVariation model = mapper.map(request, ShopProductVariation.class);
		
		if(!request.getHasFilterControl()) {
			model.setFilterType(null);
			model.setFilterValue(null);
		}
		
		if(!request.getHasDimensionControl()) {
			model.setHeight(null);
			model.setLength(null);
			model.setWeight(null);
			model.setWidth(null);
		}
		
		return model;
	}

	public ShopProductVariation modelDigital(ShopProductDigitalVariationRequest request) {
		return mapper.map(request, ShopProductVariation.class);
	}
	
	public List<ShopProductVariation> modelListPhysical(List<ShopProductPhysicalVariationRequest> request) {
		return request.stream().map(m -> this.modelPhysical(m)).collect(Collectors.toList());
	}

	public List<ShopProductVariation> modelListDigital(List<ShopProductDigitalVariationRequest> request) {
		return request.stream().map(m -> this.modelDigital(m)).collect(Collectors.toList());
	}
		
}
