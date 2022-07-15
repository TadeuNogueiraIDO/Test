package br.com.idolink.api.service;

import br.com.idolink.api.dto.request.DeliveryPaymentSetupRequest;
import br.com.idolink.api.dto.response.DeliveryPaymentSetupResponse;

public interface DeliveryPaymentSetupService {

	DeliveryPaymentSetupResponse findByShop(Long id);

	DeliveryPaymentSetupResponse create(Long shopId, DeliveryPaymentSetupRequest request);

	DeliveryPaymentSetupResponse update(Long shopId, DeliveryPaymentSetupRequest request);
	

}
