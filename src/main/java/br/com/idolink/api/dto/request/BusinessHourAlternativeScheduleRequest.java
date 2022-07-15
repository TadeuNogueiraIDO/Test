package br.com.idolink.api.dto.request;

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
public class BusinessHourAlternativeScheduleRequest {
	
	private Date day;
	
	private Boolean isAlert;

	private String messageAlert;
	
	private Boolean closed;
	
	private List<BusinessHourScheduleRequest> schedules;

}
