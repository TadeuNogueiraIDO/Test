package br.com.idolink.api.mapper.businesshour;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.BusinessHourDayRequest;
import br.com.idolink.api.dto.response.BusinessHourDayResponse;
import br.com.idolink.api.model.BusinessHourDay;
import br.com.idolink.api.model.enums.TimeFormat;

@Component
public class BusinessHourDayMapper {
	
	@Autowired
	private BusinessHourScheduleMapper businessHourScheduleMapper;

	public BusinessHourDayResponse response(BusinessHourDay model, TimeFormat timeFormat) {
		BusinessHourDayResponse response = new BusinessHourDayResponse();
	    response.setDay(model.getDay());
		response.setEnabled(model.getEnabled());
		response.setSchedules(nonNull(model.getSchedules()) ? businessHourScheduleMapper.response(model.getSchedules(), timeFormat) : new ArrayList<>());
		return response;
	}
	
	public BusinessHourDay create(BusinessHourDayRequest request, TimeFormat timeFormat) {
		BusinessHourDay model = new BusinessHourDay();
	    model.setDay(request.getDay());
		model.setEnabled(request.getEnabled());
		model.setSchedules(nonNull(request.getSchedules()) ? businessHourScheduleMapper.create(request.getSchedules(), timeFormat) : new ArrayList<>());
		return model;
	}
	
	public List<BusinessHourDay> create(List<BusinessHourDayRequest> request, TimeFormat timeFormat) {
		return request.stream().map(r -> create(r, timeFormat)).collect(Collectors.toList());
	}
	
	public List<BusinessHourDayResponse> response(List<BusinessHourDay> model, TimeFormat timeFormat) {
		return model.stream().map(m -> response(m, timeFormat)).collect(Collectors.toList());
	}
	
}
