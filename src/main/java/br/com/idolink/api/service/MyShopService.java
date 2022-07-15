package br.com.idolink.api.service;

import br.com.idolink.api.dto.response.MyShopResponse;

public interface MyShopService {
	
	MyShopResponse dashboardMyShop (Long idoId, boolean validation);
	
	void hideCategory(Long id);

}
