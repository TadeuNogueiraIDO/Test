package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuickPaySedingStatus {
	
	PENDING(1L, "Envio Pendente"),
	SENT(2L, "Enviado"),
	DELIVERED(3L, "Entregue"),
	SENDCANCELED(4L, "Envio Cancelado");

	private Long id;
	
	private String description;

}
