package br.com.idolink.api.dto.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletGeneralResponse {
	
	private List<WalletResponse> wallets;
	
	private BigDecimal movimentations;
	
	private BigDecimal gatewayIdo;
	
	private BigDecimal invoices;
}
