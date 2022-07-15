package br.com.idolink.api.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopProductVariationRequest {
	
	private Long id;
	
	private String name;
	
	private BigDecimal price;
	
	private BigDecimal promoPrice;
	
	private Boolean hasStockControl;
	
	private Integer amount;
	
	private Boolean disableOutOfStock;
	
	private String sku;
		
}
