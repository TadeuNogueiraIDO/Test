 package br.com.idolink.api.dto.response;

import java.util.List;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class  ShopProductPhysicalResponse extends ShopProductResponse {

	private List<ShopProductPhysicalVariationResponse> variations;
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private BlobFileResponse MainImage;
	
	private List<BlobFileResponse> additionalImages;
		
	private Boolean isVitrineShow;
	
	private ShopProductCategoryFilterResponse category;

	
	
	
}
