package br.com.idolink.api.repository;

import java.util.List;

import br.com.idolink.api.filter.ShopCategoryStockFilter;
import br.com.idolink.api.model.ShopCategory;

public interface ShopCategoryFilterRepository {
	
	List<ShopCategory> findShopCategoryByShopId(Long shopId, ShopCategoryStockFilter filter);
}
