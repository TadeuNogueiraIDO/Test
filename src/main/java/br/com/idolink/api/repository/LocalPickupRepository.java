package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.LocalPickup;

@Repository
public interface LocalPickupRepository extends JpaRepository<LocalPickup, Long>, LocalPickupFilterRepository {

		
}
