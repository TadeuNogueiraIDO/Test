package br.com.idolink.api.dto.response;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.response.common.BaseToolResponse;
import br.com.idolink.api.model.enums.DisplayFormPdf;
import br.com.idolink.api.model.enums.Typeicon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttachedPdfResponse extends BaseToolResponse{

	private Long id;
	
	private String nameTool;
	
    private BlobFileResponse iconTool;    
    
	private Boolean activated;
	
	private String title;
		
	private String subtitle;
	
	private BlobFileResponse pdf;
	
	private BlobFileResponse icon;
	
	private boolean name;
	private boolean email;
	private boolean phone;
	
	private Boolean buttonAnimation;
	
	private String message;
	
	private DisplayFormPdf displayForm;
	
	private BlobFileResponse banner;
	
	private Typeicon typeicon;
	
	private Boolean showTitle;
	

	
}
