package br.com.idolink.api.dto.request;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.FormatCardProduct;
import br.com.idolink.api.model.enums.FormatShowcase;
import br.com.idolink.api.model.enums.ShopStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopRequest {
	
	
	@NotBlank
	private String name;

	private Boolean activateName;
	
	private Long storeBanner;
	
	@NotNull
	private ActivityShopRequest activity;
	
	@NotNull
	private FormatShowcase formatShowcase;
	
	@NotNull
	private FormatCardProduct formatCardProduct;
	
	private ShopStatus status;
	
	public ShopStatus getStatus() {
		if(Objects.isNull(this.status)) {
			return ShopStatus.INPROGRESS;
		}
		return this.status;
	}

}
