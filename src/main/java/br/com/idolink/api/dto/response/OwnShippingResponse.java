package br.com.idolink.api.dto.response;

import java.util.List;

import br.com.idolink.api.model.enums.TypePrice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OwnShippingResponse {

	private Long id;
	
	private TypePrice typePrice;
	
	private Float price;
	
	private List<OwnShippingVariationResponse> OwnShippingVariation;
}
