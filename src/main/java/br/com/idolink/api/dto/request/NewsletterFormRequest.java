package br.com.idolink.api.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NewsletterFormRequest {

	private String name;

	@Email
	@NotBlank
	private String email;

	private String telephone;

}
