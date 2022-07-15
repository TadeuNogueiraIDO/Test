package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShopDashboardResponse {
	
	private DashboardOrderResponse order;
	
	private DashboardSalesResponse sales;

	private Boolean receiveReportEmailMonth;
}	
