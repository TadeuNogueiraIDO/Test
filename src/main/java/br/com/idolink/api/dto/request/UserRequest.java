package br.com.idolink.api.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.idolink.api.annotation.ValidPassword;
import lombok.Data;

@Data
public class UserRequest {

	@NotBlank
	private String name;
	
	@Email
	@NotBlank
	private String email;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate birthData;
	
	private Long idCountry;
	
	private String dialCode;
	
	private String number;

	@NotBlank
	@ValidPassword
	private String password;
	
	private Long fileAvatar;
}
