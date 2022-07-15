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
public class DashboardSalesResponse {
	
	private BigDecimal invoicing;
	
	private DashboardSalesFunnelResponse salesFunel;
	
	private List<DashboardFormPaymentsResponse> formPayments;
	
	private List<DashboardTopSellingProductsResponse> topSellingProducts;
}
