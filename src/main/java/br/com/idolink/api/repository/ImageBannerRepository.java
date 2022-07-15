package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.idolink.api.model.ImageBanner;

public interface ImageBannerRepository extends JpaRepository<ImageBanner, Long>{
	
		@Query("from ImageBanner i where i.ido.id = :id")
		List<ImageBanner> findByIdo(Long id);
		
		@Query("select count(i.id) from ImageBanner i where i.ido.id = ?1")
		Long  countByIdo(Long id);
}
