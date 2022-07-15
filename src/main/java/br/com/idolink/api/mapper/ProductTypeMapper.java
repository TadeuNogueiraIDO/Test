package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.response.ProductTypeResponse;
import br.com.idolink.api.model.ProductType;

@Component
public class ProductTypeMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private StorageApi storageApi;
	
	public ProductTypeResponse response(ProductType model) {
		ProductTypeResponse response = mapper.map(model, ProductTypeResponse.class);

		if (Objects.nonNull(model.getIcon())) {
			response.setIcon(storageApi.findByUuid(model.getIcon()));
		}

		return response;
	}
	
	public List<ProductTypeResponse> response(List<ProductType> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
}
