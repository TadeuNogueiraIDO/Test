package br.com.idolink.api.dto.response;

import br.com.idolink.api.dto.request.CountryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

	private Long id;
	private CountryRequest country;
	private String district;
	private String number;
	private String complement;
	private StateResponse stateBrasil;
	
	private String zipCode;
	private String city;
	private String addressName;
	private String addressLine1;
	
	private String addressLine2;
	private String typeOfHouse;
	private String state;
}
