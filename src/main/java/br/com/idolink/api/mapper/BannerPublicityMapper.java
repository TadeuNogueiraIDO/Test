package br.com.idolink.api.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.request.BannerPublicityRequest;
import br.com.idolink.api.dto.response.BannerPublicityResponse;
import br.com.idolink.api.dto.response.ImageLinkResponse;
import br.com.idolink.api.model.BannerPublicity;

@Component
public class BannerPublicityMapper {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private StorageApi storageApi;

	public BannerPublicity model(BannerPublicityRequest request) {
		BannerPublicity model = mapper.map(request, BannerPublicity.class);
		convertImagesModel(request, model);
		return model;
	}

	public BannerPublicityResponse response(BannerPublicity model) {
		BannerPublicityResponse response = mapper.map(model, BannerPublicityResponse.class);
	    convertStringInFile(response, model);
		return response;
	}
		
	public List<BannerPublicityResponse> response(List<BannerPublicity> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}

	/**
	 * converte a string de lista arquivos do banco para uma lista de arquivos blob 
	 * @param request
	 * @return
	 */
	private void convertStringInFile(BannerPublicityResponse response, BannerPublicity model) {
		
		List<ImageLinkResponse> imagesLink = new ArrayList<>();
		
		String[] splitImage = model.getImages().split(" ; ");
		String[] splitLink = model.getLink().split(" ; ");
				
		for(int i=0;i<splitImage.length; i++) {
		
			BlobFileResponse file = storageApi.findFileById(Long.valueOf(splitImage[i]));
			
			String link = null;
			
			if(!splitLink[i].isBlank()) {
				link = splitLink[i];
			}
			
			imagesLink.add(ImageLinkResponse.builder().imageId(file).link(link).build());
						
		}
		response.setImages(imagesLink);
	}
	
	
	/**
	 * converte a lista de long de arquivos em uma String para salvar no banco
	 * @param longFiles
	 */
	public void convertImagesModel(BannerPublicityRequest request, BannerPublicity model) {
		
		StringBuffer dataImages = new StringBuffer();
		StringBuffer dataLink = new StringBuffer();
		
		for (int i = 0; i < request.getImages().size(); i++) {
			
			request.getImages().get(i);
			
			dataImages.append(request.getImages().get(i).getImageId()).append(" ; ");
			
			String link = " ";
			
			if(request.getImages().get(i).getLink() != null) {
				link = request.getImages().get(i).getLink();
			}
						
			dataLink.append(link).append(" ; ");
		}
		
		model.setImages(dataImages.toString());
		model.setLink(dataLink.toString());
	}

}
