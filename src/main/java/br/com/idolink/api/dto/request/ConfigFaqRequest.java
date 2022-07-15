package br.com.idolink.api.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.Typeicon;
import lombok.Data;

@Data
public class ConfigFaqRequest {
	
	@NotNull
	private String title;

	private String subtitle;
	
	private Long iconId;
	
	private Typeicon typeIcon;
	
	private List<FaqRequest> faqs;

}
