package br.com.idolink.api.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.IdoToolRequest;
import br.com.idolink.api.dto.response.IdoToolResponse;
import br.com.idolink.api.dto.response.ToolStatusListResponse;
import br.com.idolink.api.dto.response.ToolStatusResponse;
import br.com.idolink.api.dto.response.ToolsIdoReponse;
import br.com.idolink.api.model.IdoTool;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class IdoToolMapper {
	
	@Autowired
	private ModelMapper mapper;
		
	@Autowired
	private StorageApi storageApi;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	public IdoTool requestToModel(IdoToolRequest request) {
		return mapper.map(request, IdoTool.class);
	}
	
	public IdoToolResponse modelToResponse(IdoTool model) {
		IdoToolResponse response = mapper.map(model, IdoToolResponse.class);
		
		
		if(Objects.nonNull(model.getDateModel())) {
			response.setDt_created(model.getDateModel().dt_created);
		}
				
		if(Objects.nonNull(model.getTool().getIcon())) {
			response.getTool().setIcon(storageApi.findByUuid(model.getTool().getIcon()));
		}
		
		return translateIdoMessages(response);
	}
	
	public List<IdoToolResponse> modelToResponse(List<IdoTool> model) {
		return model.stream().map(m -> modelToResponse(m)).collect(Collectors.toList());
	}
	
	public ToolsIdoReponse modelResponse(IdoTool model) {
		ToolsIdoReponse response =  mapper.map(model, ToolsIdoReponse.class);
		
		if(Objects.nonNull(response.getTool()) &&  Objects.nonNull(response.getTool().getName())) {
			response.getTool().setName(messagePropertieService.getMessageAttribute(response.getTool().getName()));
		}
		return response;
	}
	
	public List<ToolsIdoReponse> modelResponse(List<IdoTool> model) {
		return model.stream().map(m -> modelResponse(m)).collect(Collectors.toList());
	}
	
	/**
	 * Converte Lista de IdoToolResponse para um ToolStatusListResponse
	 * ToolStatusListResponse - Representa o status de todas as ferramentas para um unico Ido
	 * @param model
	 * @return
	 */
	public ToolStatusListResponse responseToToolStatusListResponse(List<IdoToolResponse> response){
		
		ToolStatusListResponse toolListResponse = new ToolStatusListResponse();
		
		List<ToolStatusResponse> toolList = new ArrayList<>();
		
		for (IdoToolResponse idoToolResponse : response) {
			
			ToolStatusResponse toolStatusResponse = ToolStatusResponse.builder()
					.status(idoToolResponse.getStatus())
					.tool(idoToolResponse.getTool()).build();																						 
						
			toolList.add(toolStatusResponse);
							
		}
		
		toolListResponse.setTool(toolList);
		return toolListResponse;
		
	}
	
	private IdoToolResponse translateIdoMessages(IdoToolResponse response) {
		
		if(Objects.nonNull(response.getTool())) {
			response.getTool().setName(messagePropertieService.getMessageAttribute(response.getTool().getName()));
			response.getTool().setDescription(messagePropertieService.getMessageAttribute(response.getTool().getDescription()));
		}
		
		return response;
	}
		
}
