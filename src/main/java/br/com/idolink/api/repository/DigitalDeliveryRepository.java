package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.DigitalDelivery;
import br.com.idolink.api.model.ShippingSettings;

@Repository
public interface DigitalDeliveryRepository extends JpaRepository<DigitalDelivery, Long>{

	Optional<DigitalDelivery> findByShippingSettings(ShippingSettings shippingSettings);	
}
