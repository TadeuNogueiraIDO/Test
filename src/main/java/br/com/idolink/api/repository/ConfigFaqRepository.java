package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ConfigFaq;
import br.com.idolink.api.model.Ido;

@Repository
public interface ConfigFaqRepository extends JpaRepository<ConfigFaq, Long>{
	
	Optional<ConfigFaq> findByIdo(Ido ido);
	
	@Query("select count(i.id) from ConfigFaq i where i.ido.id = ?1")
	Long  countByIdo(Long id);
}
