package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.AppearanceText;

@Repository
public interface AppearanceTextRepository extends JpaRepository<AppearanceText, Long>{

	@Query("from AppearanceText i where i.ido.id = :id")
	Optional<AppearanceText> findByIdo(Long id);
}
