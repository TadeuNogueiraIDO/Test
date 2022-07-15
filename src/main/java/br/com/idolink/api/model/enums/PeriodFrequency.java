package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PeriodFrequency {

	daily(1L, "Diário"),
	weekly(2L, "Semanal"),
	monthly(3L, "Mensal");
	
	private Long id;
	
	private String name;
}
