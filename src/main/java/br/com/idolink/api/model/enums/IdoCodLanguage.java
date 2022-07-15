package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IdoCodLanguage {

	SPANISH(1L, "es"),
	ENGLISH(2L, "en"),
	PORTUGUESE(3L, "pt");
	
	Long id;
	
	String cod;
	
	public static String getCod(Long id) {

		for(IdoCodLanguage e : values()) {
	        if(e.id.equals(id)) 
	        	return e.getCod();
	    }
	    return null;
	}
	
}




