package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyShopResponse {
	
	private Long totalProducts;
	
	private Long categories;
	
	private Long lowStock;
	
	private Long withoutStock;

}
