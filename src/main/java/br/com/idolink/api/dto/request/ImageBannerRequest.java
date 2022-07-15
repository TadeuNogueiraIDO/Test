package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.ImageBannerAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageBannerRequest {

	@NotBlank
	private String title;
	
	@NotNull
	private ImageBannerAction action;
	
	private ImageBannerContactRequest contact;
	
	private String field;
	
	@NotNull
	private Long fileId;
	
}
