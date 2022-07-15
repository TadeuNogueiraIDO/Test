package br.com.idolink.api.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleQuickPayProductResponse {
	
	private Long id;
	
	private String name;
	
	private BigDecimal price;
	
	private Integer quantity;
	
	private String observation;
			
	private Long singleQuickPayId;
}
