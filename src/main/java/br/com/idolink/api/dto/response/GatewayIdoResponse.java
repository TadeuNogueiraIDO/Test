package br.com.idolink.api.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GatewayIdoResponse {

	private BigDecimal generalBalance;
	
	private BigDecimal balanceDrawn;
	
	private BigDecimal blockedBalance;
}
