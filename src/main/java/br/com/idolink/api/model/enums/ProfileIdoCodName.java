package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProfileIdoCodName {

	ABOUT_IDOLINK(0),
	BLOG(1),
	FAQ(2),
	TERMS_OF_USE(3),
    REPORT_PROBLEM(4),
	CONTACT(5);
    
    private Integer id;
}
