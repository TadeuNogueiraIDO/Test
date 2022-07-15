package br.com.idolink.api.dto.response;

import br.com.idolink.api.model.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WalletDetailsResponse {

	private Long id;
	
	private String walletNickname;
	
	private DocumentType documentType;
	
	private String name;
	
	private String document;
		
	private WalletWithdrawFrequencyResponse withdrawFrequency;
	
	private String telephone;

	private BankResponse bank;
	
	private String typeWallet;
}
