package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TimeFormat {
	
	FORMAT_AM_PM(1L),
	FORMAT_24_H(2L);

	private Long id;

}
