package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.LogoBioRequest;
import br.com.idolink.api.dto.response.LogoBioResponse;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.LogoBio;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class LogoBioMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private StorageApi storageApi;
	
	@Autowired
	private MessagePropertieService messagePropertieService;

	public LogoBioResponse response(LogoBio model) {

		LogoBioResponse response =  mapper.map(model, LogoBioResponse.class);
		
		Tool tool = toolRepository.findByCodName(ToolCodName.LOGOBIO);         
		response.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		response.setIconTool(storageApi.findByUuid(tool.getIcon()));

		
			if (Objects.nonNull(model.getFileId())) {
				response.setImage(storageApi.findFileById(model.getFileId()));
			}
		
		return response;
			
	}
	
	
	

	public List<LogoBioResponse> response(List<LogoBio> model) {
		return model.stream().map(m -> response(m)).collect(Collectors.toList());
	}
	
	public LogoBio create(LogoBioRequest request, Ido ido) {
		LogoBio model = mapper.map(request, LogoBio.class);
		model.setIdo(ido);
		return model;
	}
	
}
