package br.com.idolink.api.dto.response;

import lombok.Data;

@Data
public class AttachedPdfLeadsResponse {

	private Long id;
	
	private String email;
		
	private String phone;
	
	private String name;
}
