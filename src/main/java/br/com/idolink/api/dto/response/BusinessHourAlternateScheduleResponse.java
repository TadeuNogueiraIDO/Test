package br.com.idolink.api.dto.response;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BusinessHourAlternateScheduleResponse {

	private Long id;
	
	private Date day;
	
	private Boolean isAlert;
	
	private String messageAlert;
	
	private Boolean closed;
	
	private List<BusinessHourScheduleResponse> schedules;
}

