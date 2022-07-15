package br.com.idolink.api.dto.request;

import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class AttachedPdfLeadsRequest {

	@Email
	private String email;
		
	private String phone;
	
	private String name;
	
}
	
