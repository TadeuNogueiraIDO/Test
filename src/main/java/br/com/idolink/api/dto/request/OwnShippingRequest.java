package br.com.idolink.api.dto.request;

import java.math.BigDecimal;
import java.util.List;

import br.com.idolink.api.model.enums.TypePrice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OwnShippingRequest {

	private TypePrice typePrice;
	
	private BigDecimal price;
	
	private List<OwnShippingVariationRequest> OwnShippingVariation;
		
}
