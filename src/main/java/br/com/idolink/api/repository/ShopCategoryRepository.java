package br.com.idolink.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Shop;
import br.com.idolink.api.model.ShopCategory;

@Repository
public interface ShopCategoryRepository extends JpaRepository<ShopCategory, Long>, ShopCategoryFilterRepository{

	List<ShopCategory> findByShop(Shop shop);
	
	Optional<ShopCategory> findByName(String name);
	
	@Query("SELECT sc FROM ShopCategory sc LEFT JOIN FETCH sc.products sp LEFT JOIN sp.variations pv WHERE sc.shop.id = :idShop AND sp.shopCategory.id = sc.id AND pv.shopProduct.id = sp.id AND pv.hasStockControl = true")
    List<ShopCategory> findByShopAndHasStock( Long idShop);
}
