package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.VideoBanner;

@Repository
public interface VideoBannerRepository extends JpaRepository<VideoBanner, Long>{

	@Query("from VideoBanner v where v.ido.id = :id")
	List<VideoBanner> findByIdo(Long id);
	
	@Query("select count(i.id) from VideoBanner i where i.ido.id = ?1")
	Long  countByIdo(Long id);
	
}
