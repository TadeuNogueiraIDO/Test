package br.com.idolink.api.dto.response;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.model.enums.ImageBannerAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageCarouselItemResponse {
	
	private Long id;

	private Boolean activated;
	
	private BlobFileResponse imageId;

	private ImageBannerAction action;

	private String actionField;
	
	private String dialCode;
	
	private Boolean addDetail;

	private ImageCarouselItemDetailResponse detail;

}
