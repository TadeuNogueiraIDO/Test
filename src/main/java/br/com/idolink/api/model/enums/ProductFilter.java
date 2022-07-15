package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductFilter {

	SIZE(1L, "Tamanho"),
	NUMERIC_VALUE(2L, "Valor numérico"),
	COLOR(3L, "Cor"),
	CUSTOM(3L, "Personalizado");
	
	Long id;
	
	String description;


}




