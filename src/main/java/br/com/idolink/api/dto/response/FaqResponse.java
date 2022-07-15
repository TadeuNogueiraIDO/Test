package br.com.idolink.api.dto.response;

import lombok.Data;

@Data
public class FaqResponse {
	
	private Long id;
	
	private String question;

	private String answer;

		
}
