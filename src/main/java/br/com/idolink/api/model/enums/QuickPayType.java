package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuickPayType {
	
	SINGLE(1L, "Avulsa"),
	PRODUCT(2L, "Por Produto");

	private Long id;
	
	private String description;

}
