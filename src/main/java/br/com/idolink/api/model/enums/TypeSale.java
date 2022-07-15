package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeSale {
	ONLINE("On-line"),
	QUICKPAY("Rápida Loja"),
	QUICKSIMPLE("Rápida Simples");
	
	private String description;
}
