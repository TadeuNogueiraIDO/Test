package br.com.idolink.api.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ShopProductDigitalRequest  extends ShopProductRequest {
	
	private List<ShopProductDigitalVariationRequest> variations;
		
}
