package br.com.idolink.api.mapper.businesshour;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.BusinessHourScheduleRequest;
import br.com.idolink.api.dto.response.BusinessHourScheduleResponse;
import br.com.idolink.api.model.BusinessHourSchedule;
import br.com.idolink.api.model.enums.TimeFormat;

@Component
public class BusinessHourScheduleMapper {

	public BusinessHourScheduleResponse response(BusinessHourSchedule model, TimeFormat timeFormat) {
		DateTimeFormatter amPmFormater = DateTimeFormatter.ofPattern("h:mma");
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("HH:mm");
		BusinessHourScheduleResponse response = new BusinessHourScheduleResponse();
		if(timeFormat.equals(TimeFormat.FORMAT_24_H)) {
			response.setOpenTime(model.getOpenTime().format(formater));			
			response.setCloseTime(model.getCloseTime().format(formater));
		}
		else if(timeFormat.equals(TimeFormat.FORMAT_AM_PM)){
			response.setOpenTime(model.getOpenTime().format(amPmFormater));			
			response.setCloseTime(model.getCloseTime().format(amPmFormater));
		}
		return response;
	}
	
	public List<BusinessHourScheduleResponse> response(List<BusinessHourSchedule> model, TimeFormat timeFormat) {
		return model.stream().map(m -> response(m, timeFormat)).collect(Collectors.toList());
	}
	
	public List<BusinessHourSchedule> create(List<BusinessHourScheduleRequest> request, TimeFormat timeFormat) {
		return request.stream().map(r -> create(r, timeFormat)).collect(Collectors.toList());
	}
	
	public BusinessHourSchedule create(BusinessHourScheduleRequest request, TimeFormat timeFormat) {
		DateTimeFormatter amPmFormater = DateTimeFormatter.ofPattern("h:mma");
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("HH:mm");
		BusinessHourSchedule model = new BusinessHourSchedule();
		if(timeFormat.equals(TimeFormat.FORMAT_24_H)) {
			model.setOpenTime(LocalTime.parse(request.getOpenTime(), formater));			
			model.setCloseTime(LocalTime.parse(request.getCloseTime(), formater));
		}
		else if(timeFormat.equals(TimeFormat.FORMAT_AM_PM)){
			model.setOpenTime(LocalTime.parse(request.getOpenTime(), amPmFormater));			
			model.setCloseTime(LocalTime.parse(request.getCloseTime(), amPmFormater));
		}
		return model;
	}
	
}
