package br.com.idolink.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.response.GatewayIdoResponse;
import br.com.idolink.api.dto.response.ido.GatewayIdoGeneralBalance;

@Component
public class GatewayIdoMapper {
	
	@SuppressWarnings("unused")
	private ModelMapper mapper;
	
	public GatewayIdoResponse modelToResponse (GatewayIdoGeneralBalance model){
		
		return GatewayIdoResponse.builder()
					.generalBalance(model.getAvailable().getAmount())
					.balanceDrawn(model.getTransferred().getAmount())
					.blockedBalance(model.getWaiting_funds().getAmount()).build();
	}
}
