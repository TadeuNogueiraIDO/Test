 package br.com.idolink.api.dto.response;

import java.util.List;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopSimpleProductResponse {

	private Long id;
	
	private String name;
	
	private String description;
	
	private BlobFileResponse MainImage;
	
	private List<BlobFileResponse> additionalImages;
	
	private ProductTypeResponse typeProduct;
		
	private Boolean isVitrineShow;
	
	private Boolean hasVariation;
	
	private Boolean enableDisable;
	
				
}
