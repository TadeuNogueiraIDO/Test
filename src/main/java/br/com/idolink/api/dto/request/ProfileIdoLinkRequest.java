package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotBlank;

import br.com.idolink.api.model.enums.ProfileIdoCodName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileIdoLinkRequest {

	@NotBlank
	private String name;
	
	private String url;
	
	private ProfileIdoCodName codName;
	
}
