 package br.com.idolink.api.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class BannerPublicityRequest{

	private String title;
	
	private String subtitle;
	
	@NotNull
	private List<ImageLinkRequest> images;
		
}
