package br.com.idolink.api.dto.response;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.response.common.BaseToolResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigContactUsResponse extends BaseToolResponse{

	private Long id;
	
    private String nameTool;
	
    private BlobFileResponse iconTool;
	
	private Boolean activated;
	
	private String title;
		
	private String subtitle;
	
	private boolean name;
	private boolean email;
	private boolean phone;
	private boolean topic;
	private boolean message;


}

