package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.AppearanceButton;

@Repository
public interface AppearanceButtonRepository extends JpaRepository<AppearanceButton, Long>{

	@Query("from AppearanceButton i where i.ido.id = :id")
	Optional<AppearanceButton> findByIdo(Long id);
	
		
}
