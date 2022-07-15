package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlanPackagePaymentStatus {
	PAID(1L, "Pago"),
	WAITINGPAYMENT(1L, "Aguardando pagamento"),
	EXPIRED(3L, "Expirado"),
	CANCELED(4L, "Cancelado");
	
	private Long id;
	
	private String name;
}
