package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DigitalDeliveryResponse {
	
	private Long id;
	
	private String password;

	private Boolean enabledPassword;				
		
}
