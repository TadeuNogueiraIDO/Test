package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.ToolRequest;
import br.com.idolink.api.dto.response.ToolActivatedResponse;
import br.com.idolink.api.dto.response.ToolResponse;
import br.com.idolink.api.model.AttachedPdf;
import br.com.idolink.api.model.BusinessHour;
import br.com.idolink.api.model.ConfigContactUs;
import br.com.idolink.api.model.ConfigFaq;
import br.com.idolink.api.model.ConfigNewsletterForm;
import br.com.idolink.api.model.EmbedHtml;
import br.com.idolink.api.model.IdoContact;
import br.com.idolink.api.model.ImageBanner;
import br.com.idolink.api.model.ImageCarousel;
import br.com.idolink.api.model.Link;
import br.com.idolink.api.model.LogoBio;
import br.com.idolink.api.model.MenuFooter;
import br.com.idolink.api.model.Shop;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.VideoBanner;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class ToolMapper{
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ToolCurrencyMapper toolCurrencyMapper;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	@Autowired
	private StorageApi storageApi;
	
	public Tool requestToModel(ToolRequest request) {
		return mapper.map(request, Tool.class);
	}
	
	public ToolResponse modelToResponse(Tool model) {
		ToolResponse response = mapper.map(model, ToolResponse.class);
		if(Objects.nonNull(model.getIcon())) {
			response.setIcon(storageApi.findByUuid(model.getIcon()));
		}
		response.setPrices(toolCurrencyMapper.modelToResponse(model.getToolCurrency()));
		response.setName(messagePropertieService.getMessageAttribute(model.getName()));	
		response.setDescription(messagePropertieService.getMessageAttribute(model.getDescription()));		
		return response;
	}
	
	public List<ToolResponse> modelToResponse(List<Tool> model) {
		return model.stream().map(m -> modelToResponse(m)).collect(Collectors.toList());
	}
	
	
	public ToolActivatedResponse responseToEmbedHtml(EmbedHtml model) {
		return mapper.map(model, ToolActivatedResponse.class);
	}
	
	public ToolActivatedResponse responseToBusinessHour(BusinessHour model) {
		return mapper.map(model, ToolActivatedResponse.class);
	}
	public ToolActivatedResponse responseToContact(IdoContact model) {
		return mapper.map(model, ToolActivatedResponse.class);
	}
	public ToolActivatedResponse responseToConfigContactUs(ConfigContactUs model) {
		return mapper.map(model, ToolActivatedResponse.class);
	}	
	public ToolActivatedResponse responseToConfigFaq(ConfigFaq model) {
		return mapper.map(model, ToolActivatedResponse.class);
	}
	public ToolActivatedResponse responseToImageBanner(ImageBanner model) {
		return mapper.map(model, ToolActivatedResponse.class);
	}
		public ToolActivatedResponse responseToImageCarousel(ImageCarousel model) {
		return mapper.map(model, ToolActivatedResponse.class);
	}
	public ToolActivatedResponse responseToLink(Link model) {
		return mapper.map(model, ToolActivatedResponse.class);
	}
	public ToolActivatedResponse responseToAttachedPdf(AttachedPdf model) {
		return mapper.map(model, ToolActivatedResponse.class);
	}
	public ToolActivatedResponse responseToVideoBanner(VideoBanner model) {
		return mapper.map(model, ToolActivatedResponse.class);
	}
	public ToolActivatedResponse responseToConfigNewsletterForm(ConfigNewsletterForm model) {
		return mapper.map(model, ToolActivatedResponse.class);
	}
	public ToolActivatedResponse responseToShop(Shop model) {
		return mapper.map(model, ToolActivatedResponse.class);
	}
	public ToolActivatedResponse responseToLogoBio(LogoBio model) {
		return mapper.map(model, ToolActivatedResponse.class);
	}
	public ToolActivatedResponse responseToMenuFooter(MenuFooter model) {
		return mapper.map(model, ToolActivatedResponse.class);
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	public List<String> formatCoin(List<ToolCurrency> currencies) {
//		List<String> prices = new ArrayList<>();
//		
//		for(ToolCurrency currency : currencies) {
//			prices.add(String.format(currency.getCurrency().getSimbol().toString()));
//		}
//		
//		return prices;
//	}
}
