package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DisplayFormPdf {
	
	BUTTON("Botão"),
	BANNER("Banner");
	
	private String name;
		
}
