package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ShopProduct;
import br.com.idolink.api.model.ShopProductVariation;

@Repository
public interface ShopProductVariationRepository extends JpaRepository<ShopProductVariation, Long>{
	
	List<ShopProductVariation> findByShopProduct(ShopProduct shopProduct);
	
	@Query("select id from ShopProductVariation s where s.shopProduct.id = :shopProductId")
	List<Long> findIdByShopProduct(Long shopProductId);
	
	
//	@Query("select spv from ShopProductVariation spv "
//			+ "inner join ShopProduct sp on sp.id = spv.shopProduct.id "
//			+ "inner join ShopCategory sc on sc.id = sp.shopCategory.id "
//			+ "inner join Shop s on s.id = sc.shop_id where spv.control_stock = :status and s.id=:shopId ")
//	List<ShopProductVariation> findVariationByShopByNameByStock(Long shopId,  Boolean status, Pageable pageable);

	
	
}
