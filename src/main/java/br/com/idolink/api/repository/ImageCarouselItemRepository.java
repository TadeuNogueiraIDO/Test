package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ImageCarouselItem;

@Repository
public interface ImageCarouselItemRepository extends JpaRepository<ImageCarouselItem, Long>{

	@Query("from ImageCarouselItem i where i.imageCarousel.id = :id")
	List<ImageCarouselItem> findByImageCarousel(Long id);

}
