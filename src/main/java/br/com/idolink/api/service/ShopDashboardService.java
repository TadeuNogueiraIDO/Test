package br.com.idolink.api.service;

import br.com.idolink.api.dto.response.ShopDashboardResponse;
import br.com.idolink.api.filter.ShopDashboardFilter;


public interface ShopDashboardService {

	ShopDashboardResponse find(Long userId, ShopDashboardFilter filter);
	
	ShopDashboardResponse canSendReportEmailMonth(Long id, Boolean p);


}
