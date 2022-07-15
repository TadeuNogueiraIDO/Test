package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.MenuFooter;

@Repository
public interface MenuFooterRepository extends JpaRepository<MenuFooter, Long>{
	
	@Query("from MenuFooter i where i.ido.id = :id")
	Optional<MenuFooter> findByIdo(@Param("id") Long id);
	
	Optional<MenuFooter> findByIdo(Ido ido);

	boolean existsById(Long id);
	
	@Query("select count(i.id) from MenuFooter i where i.ido.id = ?1")
	Long  countByIdo(Long id);
}
