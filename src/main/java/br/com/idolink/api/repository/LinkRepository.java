package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.Link;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
	
	List<Link> findByIdo(Ido ido);
	
	@Query("select count(i.id) from Link i where i.ido.id = ?1")
	Long  countByIdo(Long id);
	
}
