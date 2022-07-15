package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class ShopProductDigitalVariationResponse extends ShopProductVariationResponse{

	private String digitalUrl;
	
	private String digitalOrientation;

}
