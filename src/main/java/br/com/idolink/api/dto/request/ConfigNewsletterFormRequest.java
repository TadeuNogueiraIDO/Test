package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotBlank;

import br.com.idolink.api.model.enums.Typeicon;
import lombok.Data;

@Data
public class ConfigNewsletterFormRequest {

	@NotBlank
	private String title;
		
	private String subtitle;
		
	private boolean name;
	private boolean email;
	private boolean phone;
	private Long icon;
	private Typeicon typeicon;
	private boolean animated;
	
}
	

