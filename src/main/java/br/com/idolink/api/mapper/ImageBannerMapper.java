package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.ImageBannerRequest;
import br.com.idolink.api.dto.response.ImageBannerResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.ImageBanner;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class ImageBannerMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private StorageApi storageApi;

	@Autowired
	private MessagePropertieService messagePropertieService;
	
	public ImageBannerResponse response(ImageBanner model) {

		ImageBannerResponse response =  mapper.map(model, ImageBannerResponse.class);
		
		Tool tool = toolRepository.findByCodName(ToolCodName.IMAGEBANNER);         
		response.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		response.setIconTool(storageApi.findByUuid(tool.getIcon()));

		try {
			if (Objects.nonNull(model.getFileId())) {
				response.setImage(storageApi.findFileById(model.getFileId()));
			}
		} catch (Exception e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Imagem", "api.error.image.inconsistency");
		}
		
		return response;
			
	}
	
	public List<ImageBannerResponse> response(List<ImageBanner> model) {
		return model.stream().map(m -> response(m)).collect(Collectors.toList());
	}
	
	public ImageBanner create(ImageBannerRequest request, Ido ido) {
		ImageBanner model = mapper.map(request, ImageBanner.class);
		model.setIdo(ido);
		return model;
	}
}
