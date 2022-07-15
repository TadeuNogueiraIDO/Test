package br.com.idolink.api.dto.response;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.response.common.BaseToolResponse;
import br.com.idolink.api.model.enums.Typeicon;
import lombok.Data;

@Data
public class ConfigFaqResponse extends BaseToolResponse{
	
	private Long id;
	
	private String nameTool;
	
    private BlobFileResponse iconTool;
	
	private Boolean activated;
	
	@NotNull
	private String title;

	private String subtitle;
	
	@NotNull
	private BlobFileResponse icon;
	
	@NotNull
	private Typeicon typeIcon;
	
	private List<FaqResponse> faqs;
	


}
