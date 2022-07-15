package br.com.idolink.api.model.enums;

import java.util.ArrayList;
import java.util.List;

import br.com.idolink.api.dto.response.EnumResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnitOfColor {
	
	BLACK(1L, "000000"),
	GREY(2L, "A9A9A9"),
	WHITE(3L, "FFFFFF"),
	YELLOW(4L, "FBC640"),
	ORANGE(5L, "FF5E2C"),
	RED(6L, "D03131"),
	PINK(7L, "F35FAE"),
	LILAC(8L, "6017C9"),
	BLUE(9L, "2858DD"),
	GREEN(10L, "12C138");
	
	Long id;
	
	String description;

	public static List<EnumResponse> getUnitOfColor() {

		List<EnumResponse> list = new ArrayList<>();

		for (UnitOfColor v : UnitOfColor.values()) {
			list.add(
					EnumResponse.builder().enumValue(v.getDescription()).id(v.getId()).description(v.name()).build());
		}
		return list;
	}


}




