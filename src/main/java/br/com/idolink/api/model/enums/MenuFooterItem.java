package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuFooterItem {

	TOOL(1L, "Ferramenta"),
	CATEGORY_SHOP(2L, "Categoria da loja"),
	EXTERNAL_LINK(3L, "Link externo");

	private Long id;

	private String description;
}
