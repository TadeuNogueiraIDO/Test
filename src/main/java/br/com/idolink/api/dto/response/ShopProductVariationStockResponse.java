package br.com.idolink.api.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.request.ProductTypeRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopProductVariationStockResponse {

	private Long id;
	
	private String name;
	
	private String description;
	
	private BlobFileResponse MainImage;
	
	private ProductTypeRequest typeProduct;
	
	private Boolean hasVariation;
	
	private List<ShopProductVariationResponse> variations;
	
	//alterar atributo para status
	@JsonProperty("status")
	private Boolean enableDisable;
}
