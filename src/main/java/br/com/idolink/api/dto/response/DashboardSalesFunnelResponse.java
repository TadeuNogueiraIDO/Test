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
public class DashboardSalesFunnelResponse {
	
	@Builder.Default
	private BigDecimal addCart = BigDecimal.ZERO;
	
	@Builder.Default
	private BigDecimal goToCheckout = BigDecimal.ZERO;
	
	@Builder.Default
	private BigDecimal createOrder = BigDecimal.ZERO;
	
	@Builder.Default
	private BigDecimal finalizePurchase = BigDecimal.ZERO;
}
