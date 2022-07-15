package br.com.idolink.api.model.enums;

import org.springframework.http.HttpStatus;

import br.com.idolink.api.execption.BaseFullException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Classe utilizada para verificar o o modelo pre definido escolhido e configurar no IDO
 * 
 *
 */
@Getter
@AllArgsConstructor
public enum PredefinedModelsTemplate {
	
	AURORA(9),
	STELLAR(10),
	NEWMARINE(11),
	MURAL(12),
	NEWWHITE(13),
	SCRIBBLE(14),
	NEWREFLECTION(15),
	SUMMER(16),
	NEWCITRIC(17),
	MYMUSIC(18),
	RAINBOW(19),
	URBAN(20),
	PORTFOLIO(21),
	LINENEEDLE(22),
	LOLLIPOP(23),
	HEALTH(34),
	INFLUENCER(35);
		
	private Integer id;
	
	public static PredefinedModelsTemplate getById(Integer id) {
	    for(PredefinedModelsTemplate e : values()) {
	        if(e.id.equals(id)) 
	        	return e;
	    }
	    throw new BaseFullException(HttpStatus.NOT_FOUND, "predefinedModel" , "api.error.predefined.model.not.found");
	} 
}
