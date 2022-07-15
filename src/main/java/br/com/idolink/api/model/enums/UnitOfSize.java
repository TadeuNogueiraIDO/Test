package br.com.idolink.api.model.enums;

import java.util.ArrayList;
import java.util.List;

import br.com.idolink.api.dto.response.EnumResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnitOfSize {
	
	PP(1L, "PP"),
	P(2L, "P"),
	M(3L, "M"),
	G(4L, "G"),
	GG(5L, "GG"),
	XG(6L, "XG"),
	XGG(7L, "XGG"),
	EG(8L, "EG"),
	EGG(9L, "EGG"),
	RN(10L, "RN"),
	XS(11L, "XS"),
	S(12L, "S"),
	L(13L, "L"),
	XL(14L, "XL"),
	XXL(15L, "XXL");
	
	Long id;
	
	String description;

	public static List<EnumResponse> getUnitOfSize() {

		List<EnumResponse> list = new ArrayList<>();

		for (UnitOfSize v : UnitOfSize.values()) {
			list.add(
					EnumResponse.builder().enumValue(v.name()).id(v.getId()).description(v.getDescription()).build());
		}
		return list;
	}


}




