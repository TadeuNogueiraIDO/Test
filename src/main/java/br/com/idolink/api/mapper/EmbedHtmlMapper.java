package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.EmbedHtmlRequest;
import br.com.idolink.api.dto.response.EmbedHtmlResponse;
import br.com.idolink.api.model.EmbedHtml;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class EmbedHtmlMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private StorageApi api;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	
	public EmbedHtmlResponse response(EmbedHtml model) {
	
		EmbedHtmlResponse response = mapper.map(model, EmbedHtmlResponse.class);
		
		Tool tool = toolRepository.findByCodName(ToolCodName.HTML);         
		response.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		response.setIconTool(api.findByUuid(tool.getIcon()));

		return response;
	}

	public List<EmbedHtmlResponse> response(List<EmbedHtml> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}

	public EmbedHtml model(EmbedHtmlRequest request) {
		return mapper.map(request, EmbedHtml.class);
	}

	public List<EmbedHtml> modelList(List<EmbedHtmlRequest> request) {
		return request.stream().map(m -> this.model(m)).collect(Collectors.toList());
	}
}
