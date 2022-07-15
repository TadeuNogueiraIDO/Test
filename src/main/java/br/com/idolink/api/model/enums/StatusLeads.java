package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusLeads {

	OPEN("Aberto"), 
	NOTREAD("Não Lido"),
	ARCHIVED("Arquivado");


private String name;

}
