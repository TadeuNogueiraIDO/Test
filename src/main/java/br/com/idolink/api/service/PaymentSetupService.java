package br.com.idolink.api.service;

import java.util.List;
import java.util.Optional;

import br.com.idolink.api.dto.request.PaymentSetupRequest;
import br.com.idolink.api.dto.response.PaymentSetupResponse;
import br.com.idolink.api.model.PaymentSetup;
import br.com.idolink.api.model.Shop;

public interface PaymentSetupService {

	List<PaymentSetupResponse> list();
	
	PaymentSetupResponse findByShop(Long id);
	
	Optional<PaymentSetup> findByShop(Shop shop);
	
	PaymentSetupResponse findByShop(Long id, boolean validation);

	PaymentSetupResponse create(Long shopId, PaymentSetupRequest request);

	PaymentSetupResponse update(Long id, PaymentSetupRequest request);

}
