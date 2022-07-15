package br.com.idolink.api.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.idolink.api.model.enums.Typeicon;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LinkRequest {

	@NotBlank
	private String title;
	
	private String subtitle;

	private Long icon;
	
	@NotBlank
    private String url;
	
	private Typeicon typeicon;

	private List<ButtonAnimationRequest> buttonsAnimation;
}
