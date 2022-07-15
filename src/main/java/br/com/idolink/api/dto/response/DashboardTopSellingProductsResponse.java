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
public class DashboardTopSellingProductsResponse {
	
	private String name;
	
	private BigDecimal quantity;
	
	private BigDecimal percentage;
}
