package br.com.idolink.api.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.api.PagarmeAPI;
import br.com.idolink.api.dto.request.ido.GatewayIdoToWithdraw;
import br.com.idolink.api.dto.response.GatewayIdoResponse;
import br.com.idolink.api.dto.response.ido.GatewayIdoGeneralBalance;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.GatewayIdoMapper;
import br.com.idolink.api.model.Wallet;
import br.com.idolink.api.repository.WalletRepository;
import br.com.idolink.api.service.GatewayIdoService;

@Service
public class GatewayIdoServiceImpl implements GatewayIdoService{

	@Autowired
	private WalletRepository repository;
	
	@Autowired
	private GatewayIdoMapper mapper;
	
	@Autowired
	private PagarmeAPI pagarmeApi;
	
	@Override
	public GatewayIdoResponse findGatewayId(Long userId, Long walletId) {
		Wallet wallet = repository.findById(walletId).orElseThrow(() -> 
			new BaseFullException(HttpStatus.NOT_FOUND, "GatewayIdo", "api.error.gatewayido.not.found"));

		GatewayIdoGeneralBalance pagarMe = pagarmeApi.findGeneralBalance(wallet.getToken());
		
		return mapper.modelToResponse(pagarMe);
	}

	@Override
	public void toWithdraw(Long userId, Long walletId) {
		Wallet wallet = repository.findById(walletId).orElseThrow(() -> 
			new BaseFullException(HttpStatus.NOT_FOUND, "GatewayIdo", "api.error.gatewayido.not.found"));
		
		GatewayIdoGeneralBalance pagarMe = pagarmeApi.findGeneralBalance(wallet.getToken());
		
		if(pagarMe.getAvailable().getAmount() ==  BigDecimal.ZERO) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "GatewayIdo", "api.error.gatewayido.insufficient.funds ");
		}
		
		GatewayIdoToWithdraw request = GatewayIdoToWithdraw.builder()
				.amount(pagarMe.getAvailable().toString())
				.recipient_id(wallet.getToken())
				.build();
		pagarmeApi.toWithdraw(request);
	}

}
