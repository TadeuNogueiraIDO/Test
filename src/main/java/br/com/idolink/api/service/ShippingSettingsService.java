package br.com.idolink.api.service;

import br.com.idolink.api.dto.request.ShippingSettingsRequest;
import br.com.idolink.api.dto.response.ShippingSettingsResponse;

public interface ShippingSettingsService {

	ShippingSettingsResponse findById(Long id);

	ShippingSettingsResponse create(Long shopId, ShippingSettingsRequest request);

	ShippingSettingsResponse update(Long id, ShippingSettingsRequest request);

	void delete(Long id);

	ShippingSettingsResponse findByShop(Long shopId);
}
