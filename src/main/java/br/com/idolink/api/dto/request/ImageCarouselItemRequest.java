package br.com.idolink.api.dto.request;

import br.com.idolink.api.model.enums.ImageBannerAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageCarouselItemRequest {

	private Long imageId;
	
	private ImageBannerAction action;
	
	private String actionField;
	
	private String dialCode;
	
	private Boolean addDetail;
	
	private ImageCarouselItemDetailRequest detail;
	
}
