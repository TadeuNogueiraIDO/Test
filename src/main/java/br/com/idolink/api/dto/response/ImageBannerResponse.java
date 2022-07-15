package br.com.idolink.api.dto.response;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.response.common.BaseToolResponse;
import br.com.idolink.api.model.enums.ImageBannerAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageBannerResponse extends BaseToolResponse{

	private Long id;
	
	private Boolean activated;
	
	private String nameTool;
	
	private BlobFileResponse iconTool;
	
	private String title;
	
	private ImageBannerAction action;
	
	private String field;
	
	private BlobFileResponse image;
	
	private ImageBannerContactResponse contact;
	
	private Long idoId;
	
	
	
	
}
