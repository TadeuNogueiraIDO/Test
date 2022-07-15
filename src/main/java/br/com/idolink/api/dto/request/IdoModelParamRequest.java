package br.com.idolink.api.dto.request;

import br.com.idolink.api.dto.request.ido.IdoRequest;
import lombok.Data;

@Data
public class IdoModelParamRequest {

	private Long id;
	
	private IdoRequest ido;
	
	private ModelParamRequest modelParam;
	
	private String value;
	
}
