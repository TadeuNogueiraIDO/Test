package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopQuickPayProductResponse {
	
	private Long id;
	
	private ShopProductVariationResponse productVariation;
	
	private Integer quantity;
	
	private String observation;
			
	private Long shopQuickPayId;
}
