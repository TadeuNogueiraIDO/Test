package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.LocalPickupRequest;
import br.com.idolink.api.dto.response.LocalPickupResponse;

public interface LocalPickupService {

	LocalPickupResponse findById(Long id);

	LocalPickupResponse create(Long shopId, LocalPickupRequest request);

	LocalPickupResponse update(Long id, LocalPickupRequest request);

	void delete(Long id);

	List<LocalPickupResponse> findByShippingSettings(Long shippingSettingsId, Boolean isEnabled);

	LocalPickupResponse updateEnabled(Long id, Boolean isEnabled);
}
