package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Typeicon {
	

	SOCIAL(0, "Social"),
	IDO(1, "Galeria Ido"),
	UPLOAD(2, "Novo Upload"),
	NONE(3, "Nenhum");
		
	private Integer id;
	
	private String name;
			
}
