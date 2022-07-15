package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdoModelParamResponse {

	private Long id;
	
	private ModelParamResponse modelParam;
	
	private String value;
	
}
