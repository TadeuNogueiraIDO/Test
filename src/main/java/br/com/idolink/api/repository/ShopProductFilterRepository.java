package br.com.idolink.api.repository;

import java.util.List;

import br.com.idolink.api.filter.PersonalHomeFilter;
import br.com.idolink.api.model.ShopProduct;

public interface ShopProductFilterRepository {
	List<ShopProduct> findShopProductPersonal(PersonalHomeFilter filter);
}
