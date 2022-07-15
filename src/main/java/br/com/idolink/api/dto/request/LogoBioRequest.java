package br.com.idolink.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogoBioRequest {
	
	private Boolean logo;
	
	private Long fileId;
	
	private Boolean nameActivated;
	
	private String name;
	
	private Boolean bioActivated;
	
	private String bio;
		
}
