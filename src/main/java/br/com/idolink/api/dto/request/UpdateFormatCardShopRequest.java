package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.FormatCardProduct;
import br.com.idolink.api.model.enums.FormatShowcase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateFormatCardShopRequest {
	
	@NotNull
	private FormatShowcase formatShowcase;
	
	@NotNull
	private FormatCardProduct formatCardProduct;

}
