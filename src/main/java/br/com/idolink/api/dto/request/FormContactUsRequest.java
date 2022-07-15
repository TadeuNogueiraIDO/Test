package br.com.idolink.api.dto.request;

import javax.validation.constraints.Email;

import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FormContactUsRequest {

	private String name;
	
	@Email
	@Nullable
	private String email;

	private String telephone;

	private String title;

	private String textBox;
	

}
