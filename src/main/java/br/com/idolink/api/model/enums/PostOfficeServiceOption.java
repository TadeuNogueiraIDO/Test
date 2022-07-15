package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PostOfficeServiceOption {
	
	CONVENTIONAL (0L, "Convencional"),
	CORPORATE(1L, "Corporativo");
	
	private Long id;
	
	private String description;
}
