package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostOfficeTypeShippingResponse {

	private boolean pac;
	
	private boolean sedex;
	
	private boolean sedex10Env;
	
	private boolean sedex10Pac;
	
	private boolean sedex12;
	
	private boolean sedexToday;
	
	private boolean regletter;

}
