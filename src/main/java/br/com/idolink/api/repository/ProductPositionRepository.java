package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ProductPosition;

@Repository
public interface ProductPositionRepository extends JpaRepository<ProductPosition, Long> {

	List<ProductPosition> findByShopCategoryIdOrderByPositionAsc(Long shopCategoryId);
	
	@Query("FROM ProductPosition i WHERE i.shopCategory.id = :categoryId AND i.shopProduct.id = :shopProductId")
	ProductPosition findByProductPositionId(Long categoryId, Long shopProductId);
	
	@Query("FROM ProductPosition i WHERE i.shopCategory.id = :categoryId AND i.position = :position")
	ProductPosition findByPosition(Long categoryId, Long position);
	
	@Query("SELECT MAX(i) AS lastPosition FROM ProductPosition i WHERE i.shopCategory.id = :categoryId")
	Long findLastPosition(Long categoryId);
	
}