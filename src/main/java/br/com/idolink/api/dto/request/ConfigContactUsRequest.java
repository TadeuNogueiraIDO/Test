package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfigContactUsRequest {

	@NotBlank
	private String title;
		
	private String subtitle;
		
	private boolean name;
	private boolean email;
	private boolean phone;
	private boolean topic;
	private boolean message;
	
}
	

