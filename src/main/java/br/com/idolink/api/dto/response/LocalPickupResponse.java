package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocalPickupResponse {
	
	private Long id;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String number;
		
	private String complement;
	
	private String district;
	
	private String addressName;
	
	private String city;
	
	private StateResponse stateBrasil;
	
	private String state;
	
	private CountryResponse country;
	
	private String zipCode;
	
	private Boolean isEnabled;				
		
}
