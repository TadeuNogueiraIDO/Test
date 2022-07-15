package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvoiceType {
	SINGLEQUICKPAY,
	SHOPQUICKPAY,
	TOOL;
}
