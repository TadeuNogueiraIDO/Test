package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderTypeStatus {
	OPEN(1L, "Aberto"),
	FINISHED(2L, "Finalizado");
	
	private Long id;
	private String description;
	
}
