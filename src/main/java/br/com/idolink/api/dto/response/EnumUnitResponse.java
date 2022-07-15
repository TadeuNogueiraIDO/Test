package br.com.idolink.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnumUnitResponse {
	
	private List<EnumResponse> unitOfColor;
	private List<EnumResponse> unitOfMeasure;
	private List<EnumResponse> unitOfSize;
	private List<EnumResponse> unitOfWeight;
		
}
