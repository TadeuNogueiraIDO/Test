package br.com.idolink.api.mapper;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.ImageCarouselItemDetailRequest;
import br.com.idolink.api.dto.request.ImageCarouselItemRequest;
import br.com.idolink.api.dto.request.ImageCarouselRequest;
import br.com.idolink.api.dto.response.ImageCarouselItemDetailResponse;
import br.com.idolink.api.dto.response.ImageCarouselItemResponse;
import br.com.idolink.api.dto.response.ImageCarouselResponse;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.ImageCarousel;
import br.com.idolink.api.model.ImageCarouselItem;
import br.com.idolink.api.model.ImageCarouselItemDetail;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class ImageCarouselMapper {
	
	@Autowired
	private StorageApi storageApi;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	
	public ImageCarouselResponse modelToResponse(ImageCarousel model) {
		
		ImageCarouselResponse response = mapper.map(model, ImageCarouselResponse.class);
		
		Tool tool = toolRepository.findByCodName(ToolCodName.IMAGECAROUSEL);         
		response.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		response.setIconTool(storageApi.findByUuid(tool.getIcon()));

		return response;
	}
	
	public List<ImageCarouselResponse> modelToResponse(List<ImageCarousel> model) {
		return model.stream().map(m -> modelToResponse(m)).collect(Collectors.toList());
	}
	
	public List<ImageCarouselItemResponse> itemModelToResponse(List<ImageCarouselItem> model) {
				
		return model.stream().map(m -> itemModelToResponse(m)).collect(Collectors.toList());
	}
	
	public ImageCarouselItemResponse itemModelToResponse(ImageCarouselItem model) {
		return ImageCarouselItemResponse.builder()
				.id(model.getId())			
				.imageId(nonNull(model.getImageId()) ? storageApi.findFileById(model.getImageId()) : null)
				.action(model.getAction())
				.actionField(model.getActionField())
				.dialCode(model.getDialCode())
				.addDetail(model.getAddDetail())				
				.detail((model.getDetail() != null) ? detailModelToResponse(model.getDetail()) : null)
				.build();
	}
	
	public ImageCarouselItemDetailResponse detailModelToResponse(ImageCarouselItemDetail model) {
		return ImageCarouselItemDetailResponse.builder()
				.id(model.getId())
				.title(model.getTitle())
				.price(model.getPrice())
				.description(model.getDescription())
				.build();
	}
	
	
	
	public ImageCarousel requestToModel(ImageCarouselRequest request, Ido ido) {
		return ImageCarousel.builder()
				.title(request.getTitle())
				.showTitle(request.getShowTitle())
				.imageformat(request.getImageformat())
				.itens(itemRequestToModel(request.getItens()))
				.ido(ido)
				.build();

	}
	
	public List<ImageCarouselItem> itemRequestToModel(List<ImageCarouselItemRequest> request) {
		return request.stream().map(m -> itemRequestToModel(m)).collect(Collectors.toList());
	}
	
	public ImageCarouselItem itemRequestToModel(ImageCarouselItemRequest request) {
		return ImageCarouselItem.builder()
				.imageId(request.getImageId())
				.action(request.getAction())
				.actionField(request.getActionField())
				.dialCode(request.getDialCode())
				.addDetail(request.getAddDetail())
				.detail(detailRequestToModel(request.getDetail()))
				.build();
	}

	public ImageCarouselItemDetail detailRequestToModel(ImageCarouselItemDetailRequest request) {
		if(Objects.isNull(request)) {		
			return null;
		}
		return ImageCarouselItemDetail.builder()				
				.title(request.getTitle())
				.price(request.getPrice())
				.description(request.getDescription())
				.build();
	
		}
	
	public ImageCarouselItem create(ImageCarouselItemRequest request) {
		ImageCarouselItem model = mapper.map(request, ImageCarouselItem.class);		
		return model;
	}
	

}
