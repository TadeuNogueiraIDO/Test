package br.com.idolink.api.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

	private Long id;
	
	private String uuid;

	private String name;

	private String email;
	
	private LocalDate birthData;
	
	private String dialCode;
	
	private String number;
	
	private UserStatusResponse status;
	
	private CountryResponse country;
	
	private Boolean validateEmail;
	
	@JsonUnwrapped
	private GeneralSettingsResponse generalSettings;
	
	private BlobFileResponse fileAvatar;

}
