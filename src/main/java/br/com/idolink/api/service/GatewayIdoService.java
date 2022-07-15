package br.com.idolink.api.service;

import br.com.idolink.api.dto.response.GatewayIdoResponse;

public interface GatewayIdoService {

	GatewayIdoResponse findGatewayId(Long userId, Long walletId);
	
	void toWithdraw(Long userId, Long walletId);
	
}
