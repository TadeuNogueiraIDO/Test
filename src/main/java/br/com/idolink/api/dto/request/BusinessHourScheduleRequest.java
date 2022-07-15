package br.com.idolink.api.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BusinessHourScheduleRequest {
	
	@ApiModelProperty(example = "HH:mm or HH:mma")
	private String openTime;
	
	@ApiModelProperty(example = "HH:mm or HH:mma")
	private String closeTime;
}
