package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletResponse {

	private Long id;
	
	private String walletNickname;
	
	private String bankName;
	
	private String typeWallet;
}
