package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuOrderDate {
	
	TODAY(1L, "Hoje"),
	WEEK(2L, "Essa semana"),
	MONTH(3L, "Esse mês"),
	THREE_MONTH(4L, "3 mêses");
	
	
	private Long id;
	private String description;
	
	
}
