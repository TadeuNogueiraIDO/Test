package br.com.idolink.api.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopProductVariationResponse {

	private Long id;
	
	private String name;
	
	private BigDecimal price;
	
	private BigDecimal promoPrice;
	
	private Integer amount;
	
	private Boolean disableOutOfStock;
	
	private String sku;
	
	private Boolean hasStockControl;
	
	
}
