package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FormatShowcase {
	
	CAROUSEL(1L, "Carousel"),
	GRID(2L, "Grid");

	private Long id;
	
	private String description;

}
