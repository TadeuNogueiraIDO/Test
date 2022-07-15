package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShopStatus {
	
	INPROGRESS(1L, "Shop in progress"),
	COMPLETED(2L, "Shop completed");
	
	private Long id;
	
	private String name;
}
