package br.com.idolink.api.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.ItemMenuFooterRequest;
import br.com.idolink.api.dto.request.MenuFooterRequest;
import br.com.idolink.api.dto.response.ItemMenuFooterResponse;
import br.com.idolink.api.dto.response.MenuFooterResponse;
import br.com.idolink.api.dto.response.UpdateMenuFooterResponse;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.ItemMenuFooter;
import br.com.idolink.api.model.MenuFooter;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class MenuFooterMapper {
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private StorageApi storageApi;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	public MenuFooterResponse response(MenuFooter model) {
		MenuFooterResponse response = mapper.map(model, MenuFooterResponse.class);
		
		Tool tool = toolRepository.findByCodName(ToolCodName.MENURODAPE);         
		response.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		response.setIconTool(storageApi.findByUuid(tool.getIcon()));

		if(Objects.nonNull(model.getItens())){
			response.setItens(itemModelToResponse(model.getItens()));			
		}
		if(Objects.nonNull(model.getLogo()) && model.getLogo() != 0) {
			response.setLogo(storageApi.findFileById(model.getLogo()));
		}
		
		return response;
	}
	
	public List<MenuFooterResponse> response(List<MenuFooter> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
	
	public ItemMenuFooterResponse itemModelToResponse(ItemMenuFooter model) {
		return mapper.map(model, ItemMenuFooterResponse.class);
	}
	
	public List<ItemMenuFooterResponse> itemModelToResponse(List<ItemMenuFooter> model) {
		return model.stream().map(m -> itemModelToResponse(m)).collect(Collectors.toList());
	}
	
	public MenuFooter requestModel(MenuFooterRequest request, Ido ido) {		
		return MenuFooter.builder()
				.logo(request.getLogo())
				.unpinMenu(request.getUnpinMenu())
				.activateFooter(request.getActivateFooter())
				.itens(new ArrayList<ItemMenuFooter>())
				.ido(ido)
				.build();
	}
	
	public MenuFooter requestToModel(MenuFooterRequest request) {		
		return MenuFooter.builder()
				.logo(request.getLogo())
				.unpinMenu(request.getUnpinMenu())
				.activateFooter(request.getActivateFooter())
				.itens(new ArrayList<ItemMenuFooter>())
				.build();
	}
	
	public List<ItemMenuFooter> itemRequestToModel(List<ItemMenuFooterRequest> request) {
		return request.stream().map(m -> itemRequestToModel(m)).collect(Collectors.toList());
	}
	
	public ItemMenuFooter itemRequestToModel(ItemMenuFooterRequest request) {
		return ItemMenuFooter.builder()
				.typeItem(request.getTypeItem())
				.label(request.getLabel())
				.externalLink(request.getExternalLink())
				.build();
	}
	
	public UpdateMenuFooterResponse modelToResponse(MenuFooter model) {
		return mapper.map(model, UpdateMenuFooterResponse.class);
	}
	public MenuFooterRequest modelToRequest(MenuFooter model) {
		return mapper.map(model, MenuFooterRequest.class);
	}
	

}
