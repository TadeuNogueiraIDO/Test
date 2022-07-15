package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.ButtonAnimationRequest;
import br.com.idolink.api.dto.response.ButtonAnimationResponse;
import br.com.idolink.api.model.ButtonAnimation;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.MessagePropertieService;

@Service
public class ButtonAnimationMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private StorageApi storageApi;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private MessagePropertieService messagePropertieService;

	public ButtonAnimationResponse response(ButtonAnimation model) {
		
		ButtonAnimationResponse response =	mapper.map(model, ButtonAnimationResponse.class);	
		Tool tool = toolRepository.findByCodName(ToolCodName.BUTTONANIMATION);         
		response.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		response.setIconTool(storageApi.findByUuid(tool.getIcon()));
		
		return 	response;
	}

	public List<ButtonAnimationResponse> response(List<ButtonAnimation> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());		
	}
	
	public ButtonAnimation model(ButtonAnimationRequest request) {
		return mapper.map(request, ButtonAnimation.class);
	}

	public ButtonAnimationRequest request(ButtonAnimation model) {
		return mapper.map(model, ButtonAnimationRequest.class);
	}
	
	public List<ButtonAnimationRequest> requestList(List<ButtonAnimation> request) {
		return request.stream().map(m -> this.request(m)).collect(Collectors.toList());		
	}
	
	/**
	 * converts a ButtonAnimationRequest List in ButtonAnimation List
	 * @param model
	 * @return
	 */
	public List<ButtonAnimation> modelList(List<ButtonAnimationRequest> request) {
		return request.stream().map(m -> this.model(m)).collect(Collectors.toList());		
	}
			
}
