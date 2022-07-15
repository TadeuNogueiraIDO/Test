package br.com.idolink.api.service;

import br.com.idolink.api.dto.request.OwnShippingRequest;
import br.com.idolink.api.dto.response.OwnShippingResponse;

public interface OwnShippingService {

	OwnShippingResponse findById(Long id);

	OwnShippingResponse create(Long shopId, OwnShippingRequest request);

	OwnShippingResponse update(Long id, OwnShippingRequest request);

	void delete(Long id);

	OwnShippingResponse findByShippingSettings(Long shippingSettingsId);
}
