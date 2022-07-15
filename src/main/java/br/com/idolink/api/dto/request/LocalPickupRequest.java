package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocalPickupRequest {
	
	@NotNull
	private String addressLine1;
		
	private String addressLine2;
	
	private String number;

	private String complement;

	private String district;

	private String addressName;
	
	@NotNull
	private String city;
	
    private StateRequest stateBrasil;
	
	private String state;
	
	@NotNull
	private CountryRequest country;
	
	@NotNull
	private String zipCode;
	
	private Boolean isEnabled;				
		
}
