package br.com.idolink.api.dto.response;

import br.com.idolink.api.model.enums.PostOfficeServiceOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostOfficeShippingResponse {

	private Long id;
	
	private String zipcodeOrigin;
		
	private PostOfficeTypeShippingResponse typeShipping; 
		    
	private PostOfficeServiceOption serviceOption;
		
	private String admCode;
		
	private String admPassword;
	
	private PostOfficeOptionalServiceResponse optionalServices;
		
}
