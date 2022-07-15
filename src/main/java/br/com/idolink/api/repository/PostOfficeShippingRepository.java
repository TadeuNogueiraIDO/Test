package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.PostOfficeShipping;
import br.com.idolink.api.model.ShippingSettings;

@Repository
public interface PostOfficeShippingRepository extends JpaRepository<PostOfficeShipping, Long> {

	Optional<PostOfficeShipping> findByShippingSettings(ShippingSettings shippingSettings);
	
}
