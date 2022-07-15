package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageLinkRequest {
	
	@NotNull
	private Long imageId;

	private String link;
	
}
