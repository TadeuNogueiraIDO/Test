package br.com.idolink.api.dto.response;

import br.com.idolink.api.model.enums.MenuFooterItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemMenuFooterResponse {

	private Long id;

	private MenuFooterItem typeItem;

	private String label;

	private String externalLink;
	
	private Boolean hide;

	private ToolsIdoReponse tool;

	private ShopCategoryForMenuFooterReponse shopCategory;
}
