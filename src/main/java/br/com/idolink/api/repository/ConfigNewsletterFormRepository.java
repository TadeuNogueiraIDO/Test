package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.idolink.api.model.ConfigNewsletterForm;
import br.com.idolink.api.model.Ido;

public interface ConfigNewsletterFormRepository extends JpaRepository<ConfigNewsletterForm, Long>{

	
	Optional<ConfigNewsletterForm> findByIdo(Ido ido);
	
	@Query("select count(i.id) from ConfigNewsletterForm i where i.ido.id = ?1")
	Long  countByIdo(Long id);

}

