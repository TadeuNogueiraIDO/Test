package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopProductStockResponse {
	private Long id;
	
	private String name;
	
	private String descrition;
	
	private Long mainImage;

	private Integer amount;
}
