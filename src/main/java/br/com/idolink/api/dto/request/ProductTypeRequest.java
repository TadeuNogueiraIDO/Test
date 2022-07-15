package br.com.idolink.api.dto.request;

import br.com.idolink.api.model.enums.TypeProduct;
import lombok.Data;

@Data
public class ProductTypeRequest {

	private String title;
	
	private String icon;
	
	private TypeProduct type;
	
	private String description;
}
