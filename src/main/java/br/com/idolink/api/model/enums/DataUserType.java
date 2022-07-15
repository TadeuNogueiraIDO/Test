package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataUserType {
	
	NAME(1, "Nome"),
	EMAIL(2, "E-mail"),
	PHONE(3, "Telefone"),
	TOPIC(4, "Assunto"),
	MESSAGE(5, "Mensagem");
		
	private Integer id;
	
	private String name;
			
}
