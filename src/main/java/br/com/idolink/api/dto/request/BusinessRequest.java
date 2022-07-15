package br.com.idolink.api.dto.request;

import lombok.Data;

@Data
public class BusinessRequest {

	private String name;
	
	private UserRequest user;
}
