package br.com.idolink.api.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.WalletRequest;
import br.com.idolink.api.dto.response.WalletDetailsResponse;
import br.com.idolink.api.dto.response.WalletGeneralResponse;
import br.com.idolink.api.dto.response.WalletResponse;
import br.com.idolink.api.model.Bank;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.Wallet;

@Component
public class WalletMapper {

	@Autowired
	private ModelMapper mapper;

	
	public WalletResponse modelToResponse(Wallet model) {
		WalletResponse response = mapper.map(model, WalletResponse.class);
		response.setBankName(model.getBank().getName());
		response.setTypeWallet(model.getTypeWallet());
		return response;
	}
	
	public WalletDetailsResponse modelToDetailsResponse(Wallet model) {
		WalletDetailsResponse response = mapper.map(model, WalletDetailsResponse.class);
		response.setTypeWallet(model.getTypeWallet());
		return response;
	}
	
	public Wallet requestToModel(WalletRequest request, User user, Bank bank) {
		Wallet model = mapper.map(request, Wallet.class);
		model.setBank(bank);
		model.getWithdrawFrequency().setId(null);
		model.setUser(user);
		return model;
	}
	
	public WalletGeneralResponse modelToGeneral(List<Wallet> models, BigDecimal movimentations, BigDecimal gatewayIdo, BigDecimal invoices) {
		WalletGeneralResponse response = new WalletGeneralResponse();
		response.setMovimentations(movimentations);
		response.setWallets(models.stream().map(w -> modelToResponse(w)).collect(Collectors.toList()));
		response.setGatewayIdo(gatewayIdo);
		response.setInvoices(invoices);
		return response;
	}
}
