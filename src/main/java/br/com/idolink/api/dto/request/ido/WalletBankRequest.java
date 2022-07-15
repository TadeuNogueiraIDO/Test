package br.com.idolink.api.dto.request.ido;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class WalletBankRequest {
	
	@NotBlank
	//Código do banco da conta
	private String bank_code;
	
	@NotNull
	//Agencia do banco da conta
	private String agencia;
	
	//
	@Size(max = 1)
	private String agencia_dv;
	
	@NotBlank
	//numero da conta
	private String conta;
	
	@NotBlank
	//Tipo da conta
	private String type;
	
	@NotBlank
	//digito verificador da conta
	private String conta_dv;
	
	@NotBlank
	//CPF ou CNPJ atrelado a conta
	private String document_number;
	
	@NotBlank
	// nome ou razão social do dono da conta
	private String legal_name;
	

}
