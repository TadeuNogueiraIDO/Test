package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.LinkRequest;
import br.com.idolink.api.dto.response.LinkResponse;
import br.com.idolink.api.model.Link;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.MessagePropertieService;


@Service
public class LinkMapper {
	
	@Autowired
	private StorageApi api;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private MessagePropertieService messagePropertieService;

	public LinkResponse response(Link model) {
		LinkResponse response = mapper.map(model, LinkResponse.class);
		
		Tool tool = toolRepository.findByCodName(ToolCodName.LINK);         
		response.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		response.setIconTool(api.findByUuid(tool.getIcon()));

		if(Objects.nonNull(model.getIcon()) && model.getIcon() != 0) {
			response.setIcon(api.findFileById(model.getIcon()));
		}
		return response;
	}

	public List<LinkResponse> response(List<Link> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());		
	}
	
	public Link model(LinkRequest request) {
		return mapper.map(request, Link.class);
	}

	/**
	 * converts a LinkRequest List in Link List
	 * @param model
	 * @return
	 */
	public List<Link> modelList(List<LinkRequest> request) {
		return request.stream().map(m -> this.model(m)).collect(Collectors.toList());		
	}
			
}
