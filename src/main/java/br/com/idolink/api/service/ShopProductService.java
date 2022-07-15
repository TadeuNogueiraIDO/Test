package br.com.idolink.api.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import br.com.idolink.api.dto.request.ShopProductDigitalRequest;
import br.com.idolink.api.dto.request.ShopProductDigitalVariationRequest;
import br.com.idolink.api.dto.request.ShopProductPhysicalRequest;
import br.com.idolink.api.dto.request.ShopProductPhysicalVariationRequest;
import br.com.idolink.api.dto.request.ShopProductRequest;
import br.com.idolink.api.dto.request.StockRequest;
import br.com.idolink.api.dto.response.EnumUnitResponse;
import br.com.idolink.api.dto.response.PersonalHomeShopResponse;
import br.com.idolink.api.dto.response.PersonalProductShop;
import br.com.idolink.api.dto.response.ShopProductDigitalResponse;
import br.com.idolink.api.dto.response.ShopProductPhysicalResponse;
import br.com.idolink.api.dto.response.ShopProductResponse;
import br.com.idolink.api.dto.response.ShopProductStockResponse;
import br.com.idolink.api.dto.response.ShopProductVariationStockResponse;
import br.com.idolink.api.dto.response.ShopSimpleProductResponse;
import br.com.idolink.api.filter.PersonalHomeFilter;

public interface ShopProductService
{

	EnumUnitResponse getEnumUnits ();

	ShopProductPhysicalResponse findPhysicalById (Long id);

	ShopProductDigitalResponse findDigitalbyId (Long id);
	
	PersonalProductShop findPersonalProduct (Long id);

	ShopProductDigitalResponse createDigitalVariation (Long idShopProduct, ShopProductDigitalVariationRequest request);

	ShopProductPhysicalResponse createPhysicalVariation (Long idShopProduct, ShopProductPhysicalVariationRequest request);
	
	ShopProductPhysicalResponse createPhysical (Long shopCategoryId, ShopProductPhysicalRequest request);

	ShopProductDigitalResponse createDigital (Long shopCategoryId, ShopProductDigitalRequest request);

	ShopProductDigitalResponse updateDigitalVariation (Long productId, Long variationId, ShopProductDigitalVariationRequest request);

	ShopProductPhysicalResponse updatePhysicalVariation (Long productId, Long variationId, ShopProductPhysicalVariationRequest request);

	ShopProductResponse update (Long id, ShopProductRequest request, Long shopCategoryId);

	ShopProductDigitalResponse updateDigital (Long id, ShopProductDigitalRequest request);

	ShopProductPhysicalResponse updatePhysical (Long id, ShopProductPhysicalRequest request);

	ShopProductPhysicalResponse updateStockVariationPhysical (Long productId, Long variationId, StockRequest request);
	
	ShopProductDigitalResponse updateStockVariationDigital(Long productId, Long variationId, StockRequest request);

	List<PersonalHomeShopResponse> findPersonalHome (PersonalHomeFilter filter);

	List<ShopProductStockResponse> findStockProduct (Long userId, Long categoryId);

	List<ShopProductVariationStockResponse> findByShopCategoryStock (Long shopCategoryId);

	List<ShopSimpleProductResponse> findByShopCategory (Long shopCategoryId);

	void delete (Long id);

	void updateisVitrineShow (Long id);

	void enableAndDisable (Long id);

	void deleteVariation (Long productId, Long id);

	List<ShopProductPhysicalResponse> findByShopByName(Pageable pageable, Long shopId, String name,
			Boolean filterStock);

}
