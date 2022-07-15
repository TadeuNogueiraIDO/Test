package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormContactUsResponse {

	private Long id;
	
	private String name;

	private String email;

	private String telephone;

	private String title;

	private String textBox;
	
	
	
}
