package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PostOfficeTypeShipping {
	
	PAC(0L, "PAC"),
	SEDEX(1L, "SEDEX"),
	SEDEX10_ENV(2L, "SEDEX 10 Envelope"),
	SEDEX10_PAC(3L, "SEDEX 10 Pacote"),
	SEDEX12_(4L, "SEDEX 12"),
	SEDEX_TODAY(5L, "SEDEX Hoje"),
	REG_LETTER(6L, "Carta Registrada");
	
	private Long id;
	
	private String description;
}
