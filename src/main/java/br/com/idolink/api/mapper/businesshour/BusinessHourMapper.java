package br.com.idolink.api.mapper.businesshour;

import static java.util.Objects.nonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.BusinessHourRequest;
import br.com.idolink.api.dto.response.BusinessHourResponse;
import br.com.idolink.api.model.BusinessHour;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class BusinessHourMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private StorageApi storageApi;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	@Autowired
	private BusinessHourDayMapper businessHourDayMapper;
	
	@Autowired
	private BusinessHourAlternateScheduleMapper businessHourAlternateScheduleMapper;

	public BusinessHourResponse response(BusinessHour model) {
		BusinessHourResponse response = new BusinessHourResponse();
		response.setTitle(model.getTitle());
		response.setActivated(model.getActivated());
		response.setId(model.getId());
		response.setTitle(model.getTitle());
		response.setType(model.getType());
		response.setDays(nonNull(model.getDays()) ? businessHourDayMapper.response(model.getDays(), model.getTimeFormat()) : new ArrayList<>());
		response.setAlternativeSchedules(nonNull(model.getAlternativeSchedules()) ? businessHourAlternateScheduleMapper.response(model.getAlternativeSchedules(), model.getTimeFormat()) : new ArrayList<>());
		response.setTimeFormat(model.getTimeFormat());
		response.setTypeicon(model.getTypeicon());
		
		Tool tool = toolRepository.findByCodName(ToolCodName.BUSINESSHOUR);         
		response.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		response.setIconTool(storageApi.findByUuid(tool.getIcon()));

		if(Objects.nonNull(model.getIcon())) {
			response.setIcon(storageApi.findFileById(model.getIcon())); 
		}
		
		return response;
	}
	
	public List<BusinessHourResponse> response(List<BusinessHour> model) {
		return model.stream().map(m -> response(m)).collect(Collectors.toList());
	}
	
	public BusinessHour create(BusinessHourRequest request, Ido ido) {
		BusinessHour model = new BusinessHour();
		model.setTitle(request.getTitle());
		model.setTitle(request.getTitle());
		model.setType(request.getType());
		model.setDays(nonNull(request.getDays()) ? businessHourDayMapper.create(request.getDays(), request.getTimeFormat()) : new ArrayList<>());
		model.setAlternativeSchedules(nonNull(request.getAlternativeSchedules()) ? businessHourAlternateScheduleMapper.create(request.getAlternativeSchedules(), request.getTimeFormat()) : new ArrayList<>());
		model.setTimeFormat(request.getTimeFormat());
		model.setTypeicon(request.getTypeicon());
		model.setIdo(ido);
		return model;
	}
	
	public BusinessHour model(BusinessHourRequest request) {
		BusinessHour model =  mapper.map(request, BusinessHour.class);
		return model;
	}
}
