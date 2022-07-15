package br.com.idolink.api.dto.response;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ButtonAnimationResponse {
	
	private Long id;

	private String nameTool;
	
    private BlobFileResponse iconTool;  

	private String name;

}
