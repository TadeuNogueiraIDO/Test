package br.com.idolink.api.model.enums;

import java.util.ArrayList;
import java.util.List;

import br.com.idolink.api.dto.response.EnumResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnitOfMeasure {

	CENTIMETER(1L, "Centímetro"),
	INCH(2L, "Polegadas"),
	FEET(3L, "Pés");
	
	Long id;
	
	String description;

	public static List<EnumResponse> getUnitOfMeasure() {

		List<EnumResponse> list = new ArrayList<>();

		for (UnitOfMeasure v : UnitOfMeasure.values()) {
			list.add(
					EnumResponse.builder().enumValue(v.name()).id(v.getId()).description(v.getDescription()).build());
		}
		return list;
	}


}




