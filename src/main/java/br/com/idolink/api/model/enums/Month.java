package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Month {

    JANUARY(1, "Janeiro"),
    FEBRUARY(2, "Fevereiro"),
    MARCH(3, "Março"),
    APRIL(4, "Abril"),
    MAY(5, "Maio"),
    JUNE(6, "Junho"),
    JULY(7, "Julho"),
    AUGUST(8, "Agosto"),
    SEPTEMBER(9, "Setembro"),
    OCTOBER(10, "Outubro"),
    NOVEMBER(11, "Novembro"),
    DECEMBER(12, "Dezembro");

	private Integer id;
	private String name;
	
	public static Month getById(Integer id) {
	    for(Month e : values()) {
	        if(e.id.equals(id)) return e;
	    }
	    throw new IllegalArgumentException(String.format("Não existe uma constante para o valor %d no ENUM %s",  id, Month.class.getName()));
	}
}
