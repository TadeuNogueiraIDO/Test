package br.com.idolink.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminContactRequest {

	private String title;
	
	private String description;
	
	private Long mainIcon;
	
	private Long secondaryIcon;
	
	private String url;

}
