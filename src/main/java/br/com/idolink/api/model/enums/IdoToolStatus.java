package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IdoToolStatus {

	AVAILABLE(0, "Disponível"),
	INSTALLED(1, "Instalado"),
	BOUGHT(2, "Comprado"),
	INACTIVATED(3, "Inativado"),
	FREE(4, "Gratis"),
	START_FREE(5, "Comece Grátis"),
	PREMIUM(6, "Premium");
	
	private Integer id;
	
	private String name;
			
}
