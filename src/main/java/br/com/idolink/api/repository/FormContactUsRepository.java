package br.com.idolink.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.idolink.api.model.ConfigContactUs;
import br.com.idolink.api.model.FormContactUs;

public interface FormContactUsRepository extends JpaRepository<FormContactUs, Long>{

	@Query("from ConfigContactUs i where i.ido.id = :id")
	Optional<ConfigContactUs> findByIdo(Long id);
	
	List<FormContactUs> findByConfigContactUs(ConfigContactUs config);
}
