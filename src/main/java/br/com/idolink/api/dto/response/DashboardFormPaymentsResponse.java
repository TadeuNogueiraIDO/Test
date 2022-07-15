package br.com.idolink.api.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DashboardFormPaymentsResponse {
	
	private String name;
	
	private BigDecimal value;
	
	private BigDecimal percentage;
}
