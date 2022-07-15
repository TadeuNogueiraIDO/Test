package br.com.idolink.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopCategoryStockResponse {
	
	private Long id;

	private String name;
	
	private List<ShopProductVariationStockResponse> products;

	private Long productsSize;
	
	private Boolean enable;
}
