package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShopDashboardDate {
	WEEK("Essa semana"),
	MONTH("Esse mês"),
	THREE_MONTH("3 mêses"),
	SIX_MONTH("3 mêses"),
	YEAR("Esse ano");
	
	private String name;
}
