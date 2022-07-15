package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.VideoBannerRequest;
import br.com.idolink.api.dto.response.VideoBannerResponse;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.VideoBanner;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class VideoBannerMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	@Autowired
	private StorageApi storageApi;
	
	public VideoBanner create (VideoBannerRequest request, Ido ido) {
		VideoBanner model = mapper.map(request, VideoBanner.class);
		model.setIdo(ido);
		return model;
	}
	
	public VideoBannerResponse response (VideoBanner model) {
		VideoBannerResponse response =  mapper.map(model, VideoBannerResponse.class);
		
		Tool tool = toolRepository.findByCodName(ToolCodName.VIDEOBANNER);         
		response.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		response.setIconTool(storageApi.findByUuid(tool.getIcon()));
				
		response.setIdoId(model.getIdo().getId());
		return response;
	}
	
	public List<VideoBannerResponse> response (List<VideoBanner> model) {
		return model.stream().map(m -> response(m)).collect(Collectors.toList());
	}

}
