package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShopProductCategoryFilterResponse {
	
	private Long id;

	private String name;
	
	private Long categoryCover;
	
	private Boolean hideCategory;
	
	private Long shop;
}
