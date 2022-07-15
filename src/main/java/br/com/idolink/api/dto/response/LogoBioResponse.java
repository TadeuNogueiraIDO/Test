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
public class LogoBioResponse extends BaseToolResponse{
	
	private Long id;
	
	private String nameTool;
	
    private BlobFileResponse iconTool;
	
	private Boolean activated;
	
	private Long idoId;
	
	private Boolean nameActivated;
	
	private String name;
	
	private Boolean bioActivated;
	
	private String bio;
	
	private Boolean logo;
	
	private BlobFileResponse image;

	
	
}
