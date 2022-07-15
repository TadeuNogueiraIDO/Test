package br.com.idolink.api.dto.response;

import java.math.BigDecimal;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuOrderItem {
	
	private Long id;
	
	private String name;
	
	private Integer quantity;
	
	private BlobFileResponse urlImage;
	
	private String observation;
	
	private BigDecimal price;
}
