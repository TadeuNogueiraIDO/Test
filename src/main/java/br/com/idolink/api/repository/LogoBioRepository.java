package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.LogoBio;

@Repository
public interface LogoBioRepository extends JpaRepository<LogoBio, Long> {

	@Query("from LogoBio i where i.ido.id = :id")
	Optional<LogoBio> findByIdo(@Param("id") Long id);
	
	@Query("select count(i.id) from LogoBio i where i.ido.id = ?1")
	Long  countByIdo(Long id);
	
}
