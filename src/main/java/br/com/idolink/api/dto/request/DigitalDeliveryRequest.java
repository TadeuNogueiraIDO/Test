package br.com.idolink.api.dto.request;

import br.com.idolink.api.annotation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DigitalDeliveryRequest {
	
	@ValidPassword
	private String password;

	private Boolean enabledPassword;				
		
}
