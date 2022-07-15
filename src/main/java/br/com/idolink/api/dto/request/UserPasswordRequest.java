package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotBlank;

import br.com.idolink.api.annotation.ValidPassword;
import lombok.Data;

@Data
public class UserPasswordRequest {	

	@NotBlank
    private String currentPassword;
	
	@NotBlank
	@ValidPassword
	private String newPassword;
	
	@NotBlank
	private String confirmNewPassword;
}
