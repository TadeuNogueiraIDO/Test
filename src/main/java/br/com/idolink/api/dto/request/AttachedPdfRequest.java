package br.com.idolink.api.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.DisplayFormPdf;
import br.com.idolink.api.model.enums.Typeicon;
import lombok.Data;

@Data
public class AttachedPdfRequest {

	@NotBlank
	private String title;
		
	private String subtitle;
	
	@NotNull
	@Min(value=1, message = "O campo Pdf não pode ser vazio")
	private Long pdf;
	
	private Long icon;
	
	private Typeicon typeicon;
	
	private boolean name;
	private boolean email;
	private boolean phone;
	
	private Boolean buttonAnimation;
	
	private String message;
	
	private DisplayFormPdf displayForm; 
	
	private Long banner;
	
	private Boolean showTitle;
	
}
	
