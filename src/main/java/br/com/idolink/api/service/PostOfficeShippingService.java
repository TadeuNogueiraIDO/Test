package br.com.idolink.api.service;

import br.com.idolink.api.dto.request.PostOfficeShippingRequest;
import br.com.idolink.api.dto.response.PostOfficeShippingResponse;

public interface PostOfficeShippingService {

	PostOfficeShippingResponse findById(Long id);

	PostOfficeShippingResponse create(Long shopId, PostOfficeShippingRequest request);

	PostOfficeShippingResponse update(Long id, PostOfficeShippingRequest request);

	void delete(Long id);

	PostOfficeShippingResponse findByShippingSettings(Long shippingSettingsId);
}
