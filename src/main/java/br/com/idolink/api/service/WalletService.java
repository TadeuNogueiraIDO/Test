package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.WalletRequest;
import br.com.idolink.api.dto.response.WalletDetailsResponse;
import br.com.idolink.api.dto.response.WalletGeneralResponse;
import br.com.idolink.api.dto.response.WalletResponse;

public interface WalletService {
	WalletDetailsResponse create(Long userId, WalletRequest request);
	
	WalletDetailsResponse findById(Long id);
	
	void delete(Long id);
	
	WalletDetailsResponse update(Long id, WalletRequest request);
	
	WalletGeneralResponse findGeneralWallet(Long userId);
	
	List<WalletResponse> findAll(Long userId);
}
