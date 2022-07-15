package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OwnShippingVariationResponse {

	private String label;
			
	private Float price;
	
}
