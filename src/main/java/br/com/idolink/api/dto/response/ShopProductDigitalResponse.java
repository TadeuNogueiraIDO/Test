
package br.com.idolink.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class ShopProductDigitalResponse extends ShopProductResponse{
	private List<ShopProductDigitalVariationResponse> variations;
				
}
