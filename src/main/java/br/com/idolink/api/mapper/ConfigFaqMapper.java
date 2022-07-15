package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.ConfigFaqRequest;
import br.com.idolink.api.dto.response.ConfigFaqResponse;
import br.com.idolink.api.model.ConfigFaq;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.MessagePropertieService;

@Service
public class ConfigFaqMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	@Autowired
	private StorageApi storageApi;

	public ConfigFaqResponse modelToResponse(ConfigFaq model) {
		ConfigFaqResponse response = mapper.map(model, ConfigFaqResponse.class);
		
		Tool tool = toolRepository.findByCodName(ToolCodName.FAQ);         
		response.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		response.setIconTool(storageApi.findByUuid(tool.getIcon()));
		
		if(Objects.nonNull(model.getIconId()) && model.getIconId() != 0) {
			response.setIcon(storageApi.findFileById(model.getIconId())); 
		}
		return response;
	}
	
	public List<ConfigFaqResponse> modelToResponse(List<ConfigFaq> model) {
		return model.stream().map(m -> modelToResponse(m)).collect(Collectors.toList());
	}

	public ConfigFaq requestToModel(ConfigFaqRequest request, Ido ido) {
		ConfigFaq model = mapper.map(request, ConfigFaq.class);
		model.setIdo(ido);
		return model;
	}
}
