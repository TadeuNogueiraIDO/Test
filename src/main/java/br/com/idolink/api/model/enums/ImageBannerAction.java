package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ImageBannerAction {

	EMAIL(1),
	MAXIMAZE_IMAGE(2),
	EXTERNAL_LINK(3),
	WHATSAPP(4),
	NONE(5);
	
	private Integer id;
}
