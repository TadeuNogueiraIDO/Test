package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContactType {

	EMAIL(1L, "EMAIL"),
	FONE(2L, "FONE"),
	SMS(3L, "SMS"),
	WHATSAPP(4L, "WHATSAPP"),
	ENDERECO(5L, "ENDERECO"),
	TELEGRAM(6L, "TELEGRAM"),
	INSTAGRAM(7L,"INSTAGRAM"),
	FACEBOOK(8L,"FACEBOOK"),
	LINKEDIN(9L,"LINKEDIN"),
	YOUTUBE(10L,"YOUTUBE"),
	TIKTOK(11L,"TIKTOK"),
	SITE(12L,"SITE");
	
	Long id;
	
	String description;
}
