package br.com.idolink.api.repository;

import java.util.List;

import br.com.idolink.api.model.LocalPickup;

public interface LocalPickupFilterRepository {
	
	List<LocalPickup> findByShippingSettings(Long shippingSettings, Boolean isEnabled);
}
