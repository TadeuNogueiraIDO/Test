package br.com.idolink.api.dto.request;

import br.com.idolink.api.model.enums.PostOfficeServiceOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostOfficeShippingRequest {

	
	private String zipcodeOrigin;
		
	private PostOfficeTypeShippingRequest typeShipping; 
		    
	private PostOfficeServiceOption serviceOption;
		
	private String admCode;
		
	private String admPassword;
	
	private PostOfficeOptionalServiceRequest optionalServices;
		
}
