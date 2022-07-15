package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotBlank;

import br.com.idolink.api.annotation.ValidPassword;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PasswordRecoveryRequest {
	
	@NotBlank
	@ValidPassword
	private String password;
	
	private String code;
}
