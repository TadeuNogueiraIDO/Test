package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypePrice {
	
	FIXED(1L, "Fixed"),
	VARIABLE(2L, "Variable"),
	FREE(3L, "Free");

	private Long id;
	
	private String description;
}

