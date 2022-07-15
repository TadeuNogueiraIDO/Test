package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BankAccountType {

	JOINT_CURRENT_ACCOUNT(1L, "Conta corrente conjunta"),
	CHECKING_ACCOUNT(2L, "Conta corrente"),
	JOINT_SAVINGS_ACCOUNT(3L, "Conta poupança conjunta"),
	SAVINGS_ACCOUNT(4L, "Conta poupança");
	
	private Long id;
	
	private String name;
}
