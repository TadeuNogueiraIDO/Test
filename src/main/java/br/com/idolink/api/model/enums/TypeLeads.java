package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeLeads {

	
	CONTACT("Contact"),
	NEWSLETTER("NewsLetter"),
	PDF("PDF");
	

	private String name;

}
