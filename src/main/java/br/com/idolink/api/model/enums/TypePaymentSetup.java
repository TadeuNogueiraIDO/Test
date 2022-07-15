package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypePaymentSetup {
	
	GATEWAYIDO(1L, "Gateway Ido"),
	PAYMENTDELIVERY(2L, "Payment Delivery"),
	PAYPAL(3L, "Paypal"),
	MERCADOPAGO(4L, "Mercado Pago"),
	PAGSEGURO(5L, "Pag Seguro"),
	ADDTAX(6L, "Add fee or tax"),
	ADDTIP(7L, "Add Tip in checkout");
	
	private Long id;
	
	private String description;
}
