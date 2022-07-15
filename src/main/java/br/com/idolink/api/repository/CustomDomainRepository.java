package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.CustomDomain;

@Repository
public interface CustomDomainRepository extends JpaRepository<CustomDomain, Long> {

	@Query("from CustomDomain d where d.ido.id = :id")
	Optional<CustomDomain> findByIdo(Long id);
	
}
