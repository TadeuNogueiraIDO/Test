package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.AppearanceCards;

@Repository
public interface AppearanceCardsRepository extends JpaRepository<AppearanceCards, Long>{

	@Query("from AppearanceCards i where i.ido.id = :id")
	Optional<AppearanceCards> findByIdo(Long id);
}
