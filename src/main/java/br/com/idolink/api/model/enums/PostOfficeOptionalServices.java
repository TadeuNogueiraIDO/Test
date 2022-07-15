package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PostOfficeOptionalServices {
	
	RECEIPT_NOTICE(0L, "Aviso de Recebimento"),
	OWN_HAND(1L, "Mãos Próprias"),
	DECLARED_VALUE(2L, "Valor Declarado");
		
	private Long id;
	
	private String description;
}
