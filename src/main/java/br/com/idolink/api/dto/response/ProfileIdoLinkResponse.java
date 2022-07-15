package br.com.idolink.api.dto.response;

import br.com.idolink.api.model.enums.ProfileIdoCodName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileIdoLinkResponse {
	

	private Long id;
	
	private String name;
	
	private String url;
	
	private ProfileIdoCodName codName;

}
