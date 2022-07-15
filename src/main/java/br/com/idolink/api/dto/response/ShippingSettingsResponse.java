package br.com.idolink.api.dto.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShippingSettingsResponse {

	private Long id;
	
	private boolean ownShipping;
	
	private boolean postOffice;
	
	private boolean localPickup;
	
	private boolean digitalDelivery;
	
	private String devolution;
	
	private Boolean freeShipping;

	private BigDecimal minimumValue;

	private String decripiton;
	
	private Boolean displayWarning;

	private String customText;
	
	private OwnShippingResponse ownShippingResponse;
	
	private PostOfficeShippingResponse postOfficeShippingResponse;
	
	private List<LocalPickupResponse> localPickupsResponses;
	
	private DigitalDeliveryResponse digitalDeliveryResponse;
					
}
