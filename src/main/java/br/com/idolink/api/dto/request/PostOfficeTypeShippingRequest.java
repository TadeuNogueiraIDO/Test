package br.com.idolink.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostOfficeTypeShippingRequest {

	private boolean pac;
	
	private boolean sedex;
	
	private boolean sedex10Env;
	
	private boolean sedex10Pac;
	
	private boolean sedex12;
	
	private boolean sedexToday;
	
	private boolean regletter;

}
