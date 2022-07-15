package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ImageAspect {

	PORTRAIT(0L, "Retrato"),
	LANDSCAPE(1L, "Paisagem"),
	SQUARE(2L, "Horizontal");

	private Long id;
	
	private String description;

}
