package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ShippingSettings;
import br.com.idolink.api.model.Shop;

@Repository
public interface ShippingSettingsRepository extends JpaRepository<ShippingSettings, Long> {

	Optional<ShippingSettings> findByShop(Shop shop);
	
}
