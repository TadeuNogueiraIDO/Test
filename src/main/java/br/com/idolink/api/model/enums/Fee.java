package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public	enum Fee {
	PROCESSINGFEE(1L, "Taxa de processamento");
	
	private Long id;
	
	private String name;
}
