package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvoicePaymentStatus {
	WAITINGPAYMENT(1L, "Aguardando pagamento"),
	PAYMENTMADE(2L, "Pagamento Efetuado"),
	OVERDUE(3L, "Vencido"),
	PROCESSING(4L, "Em processamento");

	private Long id;
	
	private String description;
}
