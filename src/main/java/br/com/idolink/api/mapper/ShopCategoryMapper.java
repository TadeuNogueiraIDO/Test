package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.ShopCategoryRequest;
import br.com.idolink.api.dto.response.ShopCategoryForMenuFooterReponse;
import br.com.idolink.api.dto.response.ShopCategoryResponse;
import br.com.idolink.api.dto.response.ShopCategorySimpleResponse;
import br.com.idolink.api.dto.response.ShopCategoryStockResponse;
import br.com.idolink.api.dto.response.ShopCategoryStockSimpleResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.model.ShopCategory;
import br.com.idolink.api.service.ShopProductService;

@Component
public class ShopCategoryMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ShopProductService shopProductService;

	@Autowired
	private StorageApi storageApi;

	public ShopCategoryResponse response(ShopCategory model) {
		ShopCategoryResponse response = mapper.map(model, ShopCategoryResponse.class);
		response.setShopId(model.getShop().getId());
		try {
			if (Objects.nonNull(model.getCategoryCover())) {
				response.setCategoryCover(storageApi.findFileById(model.getCategoryCover()));
			}
		} catch (Exception e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Cover", "api.error.shop.category.image.inconsistency");
		}
		response.setProducts(shopProductService.findByShopCategory(response.getId()));
		return response;
	}
	
	public ShopCategoryStockSimpleResponse modelToResponse(ShopCategory model) {
		ShopCategoryStockSimpleResponse response = mapper.map(model, ShopCategoryStockSimpleResponse.class);
		response.setShopId(model.getShop().getId());

		response.setProducts(shopProductService.findByShopCategoryStock(response.getId()));
		return response;
	}
	
	public List<ShopCategoryStockSimpleResponse> modelToResponse(List<ShopCategory> model) {
		return model.stream().map(m -> this.modelToResponse(m)).collect(Collectors.toList());
	}
	
	public List<ShopCategorySimpleResponse> response(List<ShopCategory> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
	
	private ShopCategoryStockResponse responseCategorySimple(ShopCategory model) {
		ShopCategoryStockResponse response = mapper.map(model, ShopCategoryStockResponse.class);
		if(model.getProducts()!= null) {
		response.setProducts(shopProductService.findByShopCategoryStock(response.getId()));
		response.setProductsSize(Long.valueOf(model.getProducts().size()));
		}
		response.setEnable(model.getHideCategory());
		return response;
	}
		
	public List<ShopCategoryStockResponse> responseCategorySimple(List<ShopCategory> model) {
		return model.stream().map(m -> this.responseCategorySimple(m)).collect(Collectors.toList());
	}
	
	private ShopCategorySimpleResponse responseSimple(ShopCategory model) {
		ShopCategorySimpleResponse response = mapper.map(model, ShopCategorySimpleResponse.class);
		try {
			if (Objects.nonNull(model.getCategoryCover())) {
				response.setCategoryCover(storageApi.findFileById(model.getCategoryCover()));
			}
		} catch (Exception e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Cover", "api.error.shop.category.image.inconsistency");
		}
		response.setProducts(shopProductService.findByShopCategory(response.getId()));
		return response;
	}
		
	public List<ShopCategorySimpleResponse> responseSimple(List<ShopCategory> model) {
		return model.stream().map(m -> this.responseSimple(m)).collect(Collectors.toList());
	}
	
	private ShopCategoryForMenuFooterReponse responseForMenuFooter(ShopCategory model) {
		return mapper.map(model, ShopCategoryForMenuFooterReponse.class);		
	}
		
	public List<ShopCategoryForMenuFooterReponse> responseForMenuFooter(List<ShopCategory> model) {
		return model.stream().map(m -> this.responseForMenuFooter(m)).collect(Collectors.toList());
	}
	
	public ShopCategory model(ShopCategoryRequest request) {
		return mapper.map(request, ShopCategory.class);
	}
		
}
