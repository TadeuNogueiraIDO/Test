package br.com.idolink.api.dto.response;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.response.common.BaseToolResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmbedHtmlResponse extends BaseToolResponse{

	private Long id;
	
    private String nameTool;
	
    private BlobFileResponse iconTool;
	
	private Boolean activated;
	
	private String title;
	
	private String code;
	
	private Long idoId;
	
	
				
}
