package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FaqRequest {
	
	@NotNull
	private String question;

	@NotNull
	private String answer;
		
}
