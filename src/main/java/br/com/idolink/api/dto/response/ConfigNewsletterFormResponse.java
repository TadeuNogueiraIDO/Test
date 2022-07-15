package br.com.idolink.api.dto.response;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.response.common.BaseToolResponse;
import br.com.idolink.api.model.enums.Typeicon;
import lombok.Data;

@Data
public class ConfigNewsletterFormResponse extends BaseToolResponse{

	private Long id;
	
    private String nameTool;
	
    private BlobFileResponse iconTool;
	
	private Boolean activated;
	
	private String title;
		
	private String subtitle;
	
	private BlobFileResponse icon;
	
	private Typeicon typeicon;
	
	private boolean name;
	private boolean email;
	private boolean phone;
	private boolean animated;


}

