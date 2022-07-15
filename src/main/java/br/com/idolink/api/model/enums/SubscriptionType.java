package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubscriptionType {

	MONTHLY(1L, "Mensal"),
	YEARLY(2L, "Anual"),	
	PROPORTIONAL(3L, "Proporcional");
	
	private Long id;
	private String name;
	
	public static SubscriptionType getById(Long id) {
	    for(SubscriptionType e : values()) {
	        if(e.id.equals(id)) return e;
	    }
	    throw new IllegalArgumentException(String.format("Não existe uma constante para o valor %d no ENUM %s",  id, SubscriptionType.class.getName()));
	}
}
