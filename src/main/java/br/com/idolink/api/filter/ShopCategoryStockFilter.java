package br.com.idolink.api.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopCategoryStockFilter {
	
	@Builder.Default
	private Boolean hideCategoriesWithoutProducts = false;
	
	@Builder.Default
	private Boolean hideOutStock = false;
	
	@Builder.Default
	private Boolean hideLowStockProducts = false;
}
