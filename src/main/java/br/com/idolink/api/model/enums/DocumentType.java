package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DocumentType {

	CPF(1L, "cpf"),
	CNPJ(2L, "cnpj");
	
	private Long id;
	private String name;
}
