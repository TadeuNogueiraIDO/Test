package br.com.idolink.api.dto.request;

import lombok.Data;

@Data
public class PredefinedModelParamRequest {

	private Long id;
	
	private PredefinedModelRequest predefinedModel;
	
	private ModelParamRequest modelParam;
	
	private String value;
}
