package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ToolCodName {

	BUSINESSHOUR(0),
	CONTACT(1),
	CONTACTUS(2),
	FAQ(3),
	IMAGEBANNER(4),
	IMAGECAROUSEL(5),
	LINK(6),
	NEWSLETTER(7),
	PDF(8),
	VIDEOBANNER(9),
	BUTTONANIMATION(10),
	SHOP(11),
	HTML(12),
	LOGOBIO(13),
	MENURODAPE(14),
	CUSTOM_DOMAIN(15);

		
	private Integer id;
}

 