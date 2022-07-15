package br.com.idolink.api.mapper.businesshour;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.BusinessHourAlternativeScheduleRequest;
import br.com.idolink.api.dto.response.BusinessHourAlternateScheduleResponse;
import br.com.idolink.api.model.BusinessHourAlternateSchedule;
import br.com.idolink.api.model.enums.TimeFormat;

@Component
public class BusinessHourAlternateScheduleMapper {
	
	@Autowired
	private BusinessHourScheduleMapper businessHourScheduleMapper;
	
	public BusinessHourAlternateScheduleResponse response(BusinessHourAlternateSchedule model, TimeFormat timeFormat) {
		BusinessHourAlternateScheduleResponse response = new BusinessHourAlternateScheduleResponse();
		response.setClosed(model.getClosed());
		response.setDay(model.getDay());
		response.setIsAlert(model.getIsAlert());
		response.setMessageAlert(model.getMessageAlert());
		response.setSchedules(nonNull(model.getSchedules()) ? businessHourScheduleMapper.response(model.getSchedules(), timeFormat) : new ArrayList<>());
		return response;
	}
	
	public List<BusinessHourAlternateScheduleResponse> response(List<BusinessHourAlternateSchedule> model, TimeFormat timeFormat) {
		return model.stream().map(m -> response(m, timeFormat)).collect(Collectors.toList());
	}

	public List<BusinessHourAlternateSchedule> create(List<BusinessHourAlternativeScheduleRequest> requests,
			TimeFormat timeFormat) {
		return requests.stream().map(r -> create(r, timeFormat)).collect(Collectors.toList());
	}
	public BusinessHourAlternateSchedule create(BusinessHourAlternativeScheduleRequest request,
			TimeFormat timeFormat) {
		BusinessHourAlternateSchedule model = new BusinessHourAlternateSchedule();
		model.setDay(request.getDay());
		model.setIsAlert(request.getIsAlert());
		model.setMessageAlert(request.getMessageAlert());
		model.setClosed(request.getClosed());
		model.setSchedules(nonNull(request.getSchedules()) ? businessHourScheduleMapper.create(request.getSchedules(), timeFormat) : null);
		return model;
	}
}
