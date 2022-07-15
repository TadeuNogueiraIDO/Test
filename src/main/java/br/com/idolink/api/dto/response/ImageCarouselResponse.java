package br.com.idolink.api.dto.response;

import java.util.List;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.response.common.BaseToolResponse;
import br.com.idolink.api.model.enums.ImageAspect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageCarouselResponse extends BaseToolResponse{

	private Long id;
	
	private String nameTool;
	
	private BlobFileResponse iconTool;
	
	private Boolean activated;
	
	private String title;

	private Boolean showTitle;

	private ImageAspect imageformat;
	
	private List<ImageCarouselItemResponse> itens;
	
	
}
