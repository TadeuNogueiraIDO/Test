package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.ShopCategoryRequest;
import br.com.idolink.api.dto.response.ShopCategoryForMenuFooterReponse;
import br.com.idolink.api.dto.response.ShopCategoryResponse;
import br.com.idolink.api.dto.response.ShopCategorySimpleResponse;
import br.com.idolink.api.dto.response.ShopCategoryStockResponse;
import br.com.idolink.api.filter.ShopCategoryStockFilter;

public interface ShopCategoryService {

	ShopCategoryResponse findById(Long id);

	ShopCategoryResponse create(Long shopId, ShopCategoryRequest request);

	ShopCategoryResponse update(Long id, ShopCategoryRequest request);
	
	void updateHideProduct(Long id);

	void delete(Long id);

	List<ShopCategoryForMenuFooterReponse> findCategoryByShop(Long shopId);
	
	List<ShopCategorySimpleResponse> findByShop(Long shopId);
	
	List<ShopCategoryStockResponse> findMyProductsByShop(Long shopId, ShopCategoryStockFilter filter);
	
	List<ShopCategoryStockResponse> findStockByShop(Long shopId);
}
