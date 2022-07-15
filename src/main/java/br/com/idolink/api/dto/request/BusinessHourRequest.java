package br.com.idolink.api.dto.request;

import java.util.List;

import br.com.idolink.api.model.enums.BusinessHourType;
import br.com.idolink.api.model.enums.TimeFormat;
import br.com.idolink.api.model.enums.Typeicon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BusinessHourRequest {
	
	private String title;
	
	private BusinessHourType type;
	
	private TimeFormat timeFormat;
	
	private Long icon;
	
	private List<BusinessHourDayRequest> days;
	
	private List<BusinessHourAlternativeScheduleRequest> alternativeSchedules;
	
	private Typeicon typeicon;
	
}
