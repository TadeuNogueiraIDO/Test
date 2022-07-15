package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ImageCarousel;

@Repository
public interface ImageCarouselRepository extends JpaRepository<ImageCarousel, Long>{

	@Query("FROM ImageCarousel i where i.ido.id = :id")
	List<ImageCarousel> findByIdo(@Param("id") Long id);
	
	@Query("select distinct ic from ImageCarousel ic left join ic.ido i left join fetch ic.itens where i.id = :idoId")
	List<ImageCarousel> findAllByIdoId(Long idoId);
	
	@Query("select count(i.id) from ImageCarousel i where i.ido.id = ?1")
	Long  countByIdo(Long id);
	
	@Query(value = "SELECT * FROM public.image_carousel where ido_id  = :ido and dt_deleted is null ", nativeQuery = true)
	ImageCarousel findAllImageCarrousselByido(Long ido);

}
