package br.com.idolink.api.dto.response;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.response.common.BaseToolResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoBannerResponse extends BaseToolResponse{
	
	private Long id;
	
	private String nameTool;
	
    private BlobFileResponse iconTool;
	
	private Boolean activated;
	
	private String title;
	
	private String link;
	
	private Long idoId;
	
	private String thumbnail;
	


}
