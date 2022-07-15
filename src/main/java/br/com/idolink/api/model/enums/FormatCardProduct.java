package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FormatCardProduct {
	
	VERTICAL(1L, "Vertical"),
	HORIZONTAL(2L, "Horizontal");

	private Long id;
	
	private String description;

}
