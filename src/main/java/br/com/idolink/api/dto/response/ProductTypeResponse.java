package br.com.idolink.api.dto.response;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.model.enums.TypeProduct;
import lombok.Data;

@Data
public class ProductTypeResponse {
	
	private Long id;
	
	private String title;
	
	private BlobFileResponse icon;
	
	private TypeProduct type;
	
	private String description;

}
