package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeActivity {
	
	PRODUCT(1L, "product"),
	SERVICE(2L, "service");

	private Long id;
	
	private String description;
}
