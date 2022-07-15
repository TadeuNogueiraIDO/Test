package br.com.idolink.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.ShopCategoryRequest;
import br.com.idolink.api.dto.response.ShopCategoryForMenuFooterReponse;
import br.com.idolink.api.dto.response.ShopCategoryResponse;
import br.com.idolink.api.dto.response.ShopCategorySimpleResponse;
import br.com.idolink.api.dto.response.ShopCategoryStockResponse;
import br.com.idolink.api.dto.response.ShopProductVariationResponse;
import br.com.idolink.api.dto.response.ShopProductVariationStockResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.filter.ShopCategoryStockFilter;
import br.com.idolink.api.mapper.ShopCategoryMapper;
import br.com.idolink.api.model.Shop;
import br.com.idolink.api.model.ShopCategory;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.ShopCategoryProductRepository;
import br.com.idolink.api.repository.ShopCategoryRepository;
import br.com.idolink.api.repository.ShopRepository;
import br.com.idolink.api.service.ShopCategoryService;

@SuppressWarnings("unused")
@Service
public class ShopCategoryServiceImpl extends GenericToolsServiceImpl implements ShopCategoryService {

	@Autowired
	private ShopCategoryMapper mapper;

	@Autowired
	private ShopCategoryProductRepository shopCategoryProductRepository;

	@Autowired
	private ShopCategoryRepository repository;

	@Autowired
	private ShopRepository shopRepository;

	@Override
	public List<ShopCategorySimpleResponse> findByShop(Long shopId) {

		Optional<Shop> shop = shopRepository.findById(shopId);
		validate(shop, "shop", "api.error.shop.not.found");
		List<ShopCategory> model = repository.findByShop(shop.get());
		List<ShopCategorySimpleResponse> response = mapper.responseSimple(model);
		return response;
	}

	@Override
	public List<ShopCategoryForMenuFooterReponse> findCategoryByShop(Long shopId) {

		Optional<Shop> shop = shopRepository.findById(shopId);
		validate(shop, "shop", "api.error.shop.not.found");
		List<ShopCategory> model = repository.findByShop(shop.get());
		List<ShopCategoryForMenuFooterReponse> response = mapper.responseForMenuFooter(model);
		return response;
	}

	@Override
	public List<ShopCategoryStockResponse> findMyProductsByShop(Long shopId, ShopCategoryStockFilter filter) {

		Optional<Shop> shop = shopRepository.findById(shopId);
		validate(shop, "shop", "api.error.shop.not.found");
		List<ShopCategory> model = repository.findShopCategoryByShopId(shop.get().getId(), filter);
		List<ShopCategoryStockResponse> response = mapper.responseCategorySimple(model);
		return response;
	}

	@Override
	public List<ShopCategoryStockResponse> findStockByShop(Long shopId) {

		Optional<Shop> shop = shopRepository.findById(shopId);
		validate(shop, "shop", "api.error.shop.not.found");
		
		List<ShopCategory> model = repository.findByShopAndHasStock(shopId);
		
		List<ShopCategoryStockResponse> response = mapper.responseCategorySimple(model);


		return response;
	
	}

	@Override
	public ShopCategoryResponse findById(Long id) {
		Optional<ShopCategory> model = repository.findById(id);
		validate(model, "ShopCategory", "api.error.shop.category.not.found");

		ShopCategoryResponse response = mapper.response(model.get());
		return response;
	}

	@Transactional
	@Override
	public ShopCategoryResponse create(Long shopId, ShopCategoryRequest request) {

		Optional<Shop> shop = shopRepository.findById(shopId);
		validate(shop, "Shop", "api.error.shop.not.found");

		ShopCategory model = mapper.model(request);
		model.setShop(shop.get());

		Long qtd = Long.valueOf(shop.get().getCategories().size()) + 1;

		super.validToolsUserResource(shop.get().getIdo().getId(), ToolCodName.SHOP, qtd);

		model = repository.save(model);
		return mapper.response(model);
	}

	@Transactional
	@Override
	public ShopCategoryResponse update(Long id, ShopCategoryRequest request) {

		@SuppressWarnings("serial")
		ShopCategory model = repository.findById(id).orElseThrow(
				() -> new BaseFullException(HttpStatus.NOT_FOUND, "ShopCategory", "api.error.shop.category.not.found") {
				});
		BeanUtils.copyProperties(request, model, "id", "shop");
		return mapper.response(repository.save(model));
	}

	@Transactional
	@Override
	public void updateHideProduct(Long id) {
		ShopCategory model = repository.findById(id).orElseThrow(
				() -> new BaseFullException(HttpStatus.NOT_FOUND, "ShopCategory", "api.error.shop.category.not.found"));

		if (model.getHideProduct()) {
			model.setHideProduct(false);
		} else {
			model.setHideProduct(true);
		}

		model = repository.save(model);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Long semCategoriaId = null;

		Optional<ShopCategory> model = repository.findById(id);
		validate(model, "ShopCategory", "api.error.shop.category.not.found");

		ShopCategoryResponse shopCategory = new ShopCategoryResponse();

		ShopCategoryRequest shopCategoryRequest = ShopCategoryRequest.builder().name("SEM CATEGORIA").build();
		Optional<ShopCategory> category = repository.findByName("SEM CATEGORIA");

		if (category.isEmpty()) {
			shopCategory = this.create(model.get().getShop().getId(), shopCategoryRequest);
			semCategoriaId = shopCategory.getId();
		} else {
			semCategoriaId = category.get().getId();
		}
		shopCategoryProductRepository.updateCategoryProducts(model.get().getId(), semCategoriaId);
		try {

			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "ShopCategory", "api.error.shop.category.conflict");
		}
	}

	private void validate(Optional<?> model, String field, String message) {
		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
}
