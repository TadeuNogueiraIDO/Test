package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredefinedModelParamResponse {

	private Long id;
	
	private ModelParamResponse modelParam;
	
	private String value;

}
