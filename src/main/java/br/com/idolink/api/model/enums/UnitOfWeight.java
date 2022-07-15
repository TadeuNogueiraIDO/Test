package br.com.idolink.api.model.enums;

import java.util.ArrayList;
import java.util.List;

import br.com.idolink.api.dto.response.EnumResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnitOfWeight {

	KILOGRAM(1L, "Kilogramas"),
	GRAM(2L, "Gramas"),
	POUNDS(2L, "Libras"),
	OZ(3L, "Oz");
	
	Long id;
	
	String description;
	
	public static List<EnumResponse> getUnitOfWeight() {

		List<EnumResponse> list = new ArrayList<>();

		for (UnitOfWeight v : UnitOfWeight.values()) {
			list.add(
					EnumResponse.builder().enumValue(v.name()).id(v.getId()).description(v.getDescription()).build());
		}
		return list;
	}
	
}




