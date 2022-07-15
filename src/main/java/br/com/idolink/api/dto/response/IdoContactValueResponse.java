package br.com.idolink.api.dto.response;

import lombok.Data;

@Data
public class IdoContactValueResponse {

	private Long id;
	
	private String value;
	
	private Boolean enable;
	
	private ContactResponse contact;
}
