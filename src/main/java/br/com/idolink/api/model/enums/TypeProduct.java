package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeProduct {
	
	PHYSICAL(1L, "Physical"),
	DIGITAL(2L, "Digital");

	private Long id;
	
	private String description;
}
