package br.com.idolink.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessResponse {

	private Long id;

	private String name;
	
	private UserResponse user;
	
	private List<IdoResponse> idos;

}
