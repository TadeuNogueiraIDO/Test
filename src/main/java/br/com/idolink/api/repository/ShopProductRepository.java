package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ShopProduct;

@Repository
public interface ShopProductRepository extends PagingAndSortingRepository<ShopProduct, Long>, ShopProductFilterRepository{

	@Query("from ShopProduct i where i.shopCategory.id = :shopCategoryId")
	List<ShopProduct> findByShopCategory(Long shopCategoryId);
	
	@Query("from ShopProduct i where i.shopCategory.shop.id = :shopId")
	List<ShopProduct> findByShop(Long shopId, Pageable pageable);
	
	@Query("from ShopProduct i where i.shopCategory.shop.id = :shopId and UPPER(i.name) like %:name%")
	List<ShopProduct> findByShopByName(Long shopId, String name, Pageable pageable);
	
	@Query(value = "from ShopProduct sp left join fetch sp.variations i where "
			+ "sp.shopCategory.shop.id = :shopId and i.hasStockControl = :status")
	List<ShopProduct> findByShopByNameByStock(Long shopId, Boolean status,Pageable pageable);
	

	
}

