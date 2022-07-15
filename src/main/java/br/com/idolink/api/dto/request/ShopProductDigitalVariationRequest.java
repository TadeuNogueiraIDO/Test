package br.com.idolink.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ShopProductDigitalVariationRequest extends ShopProductVariationRequest {

	private String digitalUrl;
			
	private String digitalOrientation;
				
}
