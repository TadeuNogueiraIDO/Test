package br.com.idolink.api.dto.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopQuickPayCheckoutRequest {
	
	private BigDecimal discountValue;
	private BigDecimal additionalValue;
	
	private List<ShopQuickPayProductRequest> products;
		
}
