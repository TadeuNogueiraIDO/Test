package br.com.idolink.api.mapper;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.ToolConfigPackageRequest;
import br.com.idolink.api.dto.response.ToolPlanResponse;
import br.com.idolink.api.model.ToolConfigPackage;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class ToolPackageMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	public ToolConfigPackage requestToModel(ToolConfigPackageRequest request) {
		return mapper.map(request, ToolConfigPackage.class);
	}
	
	public ToolPlanResponse modelToResponse(ToolConfigPackage model) {
		ToolPlanResponse response = mapper.map(model, ToolPlanResponse.class);
		response.setName(messagePropertieService.getMessageAttribute(model.getTool().getName()));
		setMsgTranslator(response, model);
		return response;
	}
	
	/**
	 * traducao msg padrao
	 * @param response
	 */
	private void setMsgTranslator(ToolPlanResponse response, ToolConfigPackage model) {
		
		if(response.getAddition() == 0) {
			StringBuilder msg = new StringBuilder(" - ");
			
			if(Objects.nonNull(model.getTool())) {
				
				ToolCodName codName = model.getTool().getCodName();
				
				if(codName.equals(ToolCodName.IMAGECAROUSEL) || 
						codName.equals(ToolCodName.IMAGEBANNER) ||
						codName.equals(ToolCodName.HTML) ||
						codName.equals(ToolCodName.PDF)) {
					
					msg.append(messagePropertieService.getMessageAttribute("api.model.tool.package.unlimited.adition"));
				}
				if(codName.equals(ToolCodName.PDF)) {
					msg.append(messagePropertieService.getMessageAttribute("api.model.tool.package.tamanho.mb"));
				}
				if(codName.equals(ToolCodName.SHOP)) {
					msg.append(messagePropertieService.getMessageAttribute("api.model.tool.package.shop.unlimited"));
				}
				if(codName.equals(ToolCodName.CUSTOM_DOMAIN)) {
					msg.append(messagePropertieService.getMessageAttribute("api.model.tool.package.customdomain"));
				}
			}
			response.setMsgTranslator(response.getName() + msg.toString());
		}
	}
		
}
