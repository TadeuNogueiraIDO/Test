package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.OwnShipping;
import br.com.idolink.api.model.ShippingSettings;

@Repository
public interface OwnShippingRepository extends JpaRepository<OwnShipping, Long> {

	Optional<OwnShipping> findByShippingSettings(ShippingSettings shippingSettings);
	
}
