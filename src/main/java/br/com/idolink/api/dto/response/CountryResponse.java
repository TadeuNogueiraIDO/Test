package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryResponse {

	private Long id;

	private String country;
	
	private String ddi;
	
	private String coin;
	
	private String coinName;
	
	private String masked;
}
