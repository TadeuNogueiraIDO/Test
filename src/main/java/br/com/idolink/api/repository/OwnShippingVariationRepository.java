package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.OwnShipping;
import br.com.idolink.api.model.OwnShippingVariation;

@Repository
public interface OwnShippingVariationRepository extends JpaRepository<OwnShippingVariation, Long> {

	List<OwnShipping> findByOwnShipping(OwnShipping ownShipping);
	
}
