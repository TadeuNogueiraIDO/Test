package br.com.idolink.api.dto.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShippingSettingsRequest {

	private Boolean ownShipping;
	
	private Boolean postOffice;
	
	private Boolean localPickup;
	
	private String devolution;
	
	private Boolean freeShipping;

	private BigDecimal minimumValue;

	private String decripiton;
	
	private Boolean displayWarning;

	private String customText;

	private Boolean digitalDelivery;
}
