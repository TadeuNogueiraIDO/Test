
package br.com.idolink.api.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItensResponse {
	
	private Long id;
	
	private String name;
	
	private Integer quantity;
	
	private String urlImage;
	
	private String observation;
	
	private BigDecimal price;
}
