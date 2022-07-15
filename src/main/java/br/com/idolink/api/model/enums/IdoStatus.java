package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IdoStatus {
	
	DRAFT("Rascunho"), 
	PUBLISHED("Publicado"),
	INACTIVE("Inativo"); 

	public String name;

}
