package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Classe utilizada para verificar o o modelo pre definido escolhido e configurar no IDO
 * 
 *
 */
@Getter
@AllArgsConstructor
public enum TypeTemplate {
	
	LINK(1),
	PAGE(2),
	SHOP(3);
		
	private Integer id;
		 
}
