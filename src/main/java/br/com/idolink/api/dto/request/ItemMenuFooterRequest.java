package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotBlank;

import br.com.idolink.api.model.enums.MenuFooterItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemMenuFooterRequest {

	private MenuFooterItem typeItem;

	@NotBlank
	private String label;
	
	private String externalLink;
	
	private  Long toolId;

	private  Long shopCategoryId;

}
