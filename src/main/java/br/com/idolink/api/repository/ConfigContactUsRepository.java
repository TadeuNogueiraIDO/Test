package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.idolink.api.model.ConfigContactUs;
import br.com.idolink.api.model.Ido;

public interface ConfigContactUsRepository extends JpaRepository<ConfigContactUs, Long>{

	
	Optional<ConfigContactUs> findByIdo(Ido ido);
	
	@Query("select count(i.id) from ConfigContactUs i where i.ido.id = ?1")
	Long  countByIdo(Long id);

}

