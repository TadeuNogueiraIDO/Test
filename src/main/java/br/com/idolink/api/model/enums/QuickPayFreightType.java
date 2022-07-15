package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuickPayFreightType {
	
	WAITINGPAYMENT(1L, "Aguardando pagamento"),
	PAID(2L, "Pago"),
	PAYMENTCANCELED(3L, "Pagamento Cancelado");

	private Long id;
	
	private String description;

}
