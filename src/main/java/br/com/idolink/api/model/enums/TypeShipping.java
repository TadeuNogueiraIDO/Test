package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeShipping {
	
	OWNSHIPPING(1L, "Own Shipping"),
	POSTOFFICE(2L, "Post Office"),
	LOCALPICKUP(3L, "Local Pickup"),
	DIGITALDELIVERY(4L, "Digital Delivery"),
	POSTOFFICE_PAC(5L, "Post Office-PAC"),
	POSTOFFICE_SEDEX(6L, "Post Office-Sedex");

	private Long id;
	
	private String description;
}
