package br.com.idolink.api.dto.request;

import br.com.idolink.api.model.enums.ProductFilter;
import br.com.idolink.api.model.enums.UnitOfMeasure;
import br.com.idolink.api.model.enums.UnitOfWeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ShopProductPhysicalVariationRequest extends ShopProductVariationRequest{

	private UnitOfMeasure unitOfMeasure;
	
	private Double length;
	
	private Double width;
	
	private Double height;
		
	private UnitOfWeight UnitOfWeight;
	
	private Double weight;
			
	private ProductFilter filterType;
	
	private String filterValue;
	
    private Boolean hasDimensionControl;
	
	private Boolean hasFilterControl;
}
