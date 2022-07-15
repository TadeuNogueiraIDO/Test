package br.com.idolink.api.dto.response;

import java.util.List;

import br.com.idolink.api.model.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BusinessHourDayResponse {

	private WeekDay day;
	
	private Boolean enabled;
	
	private List<BusinessHourScheduleResponse> schedules;
}
