package br.com.idolink.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopQuickPayProductRequest {
	
	private Long productVariationId;
	
	private Integer quantity;
	
	private String observation;
			
}
