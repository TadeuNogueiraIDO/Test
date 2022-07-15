package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuickPayFinalizationType {
	
	PIX(1L, "Pix IdoLink"),
	RECEIVEONTIME(2L, "Receber na hora"),
	SENDPROPOUSAL(3L, "Enviar proposta");

	private Long id;
	
	private String description;

}
