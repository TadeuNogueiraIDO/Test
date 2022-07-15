package br.com.idolink.api.dto.request;

import lombok.Data;

@Data
public class PredefinedModelRequest {

	private Long id;
	
	private String name;
	
	private Long image;
	
	private Boolean showcase;
	
	private Long classification;
}
