package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeWallet {

	MERCADOPAGO(1L,"Mercado_Pago"),
	PAGSEGURO(2L,"Pag_seguro"),
	PAYPAL(3L,"PayPal"),
	GATEWAYIDO(4L,"Gateway_Ido");

	private Long id;
	private String name;
	
	
}
