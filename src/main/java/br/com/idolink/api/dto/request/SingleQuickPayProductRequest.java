package br.com.idolink.api.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleQuickPayProductRequest {
	
	private String name;
	
	private BigDecimal price;
	
	private Integer quantity;
	
	private String observation;
			
}
