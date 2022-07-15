package br.com.idolink.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequest {

	
	private CountryRequest country;
	
	private String district;
	
	private String number;
	
	private String complement;
	
	private StateRequest stateBrasil;
	
	private String zipCode;
	
	private String city;
	
	private String addressName;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String typeOfHouse;
	
	private String state;

	
	
}
