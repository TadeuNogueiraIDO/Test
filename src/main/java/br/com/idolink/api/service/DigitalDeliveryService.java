package br.com.idolink.api.service;

import br.com.idolink.api.dto.request.DigitalDeliveryRequest;
import br.com.idolink.api.dto.response.DigitalDeliveryResponse;

public interface DigitalDeliveryService {

	DigitalDeliveryResponse findById(Long id);

	DigitalDeliveryResponse create(Long shopId, DigitalDeliveryRequest request);

	DigitalDeliveryResponse update(Long id, DigitalDeliveryRequest request);

	void delete(Long id);

	DigitalDeliveryResponse findByShippingSettings(Long shippingSettingsId);
}
