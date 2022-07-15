package br.com.idolink.api.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.filter.PersonalHomeFilter;
import br.com.idolink.api.mapper.ShopProductMapper;
import br.com.idolink.api.mapper.ShopProductVariationMapper;
import br.com.idolink.api.model.ProductType;
import br.com.idolink.api.model.ShopCategory;
import br.com.idolink.api.model.ShopProduct;
import br.com.idolink.api.model.ShopProductVariation;
import br.com.idolink.api.model.enums.UnitOfColor;
import br.com.idolink.api.model.enums.UnitOfMeasure;
import br.com.idolink.api.model.enums.UnitOfSize;
import br.com.idolink.api.model.enums.UnitOfWeight;
import br.com.idolink.api.repository.ProductTypeRepository;
import br.com.idolink.api.repository.ShopCategoryRepository;
import br.com.idolink.api.repository.ShopProductRepository;
import br.com.idolink.api.repository.ShopProductVariationRepository;
import br.com.idolink.api.service.ShopProductService;

@Service
public class ShopProductServiceImpl implements ShopProductService {


	@Autowired
	private ShopProductRepository repository;

	@Autowired
	private ProductTypeRepository producTypeRepository;

	@Autowired
	private ShopCategoryRepository shopCategoryRepository;

	@Autowired
	private ShopProductMapper mapper;

	@Autowired
	private ShopProductVariationMapper mapperVariation;

	@Autowired
	private ShopProductVariationRepository productVariationRepository;

	@Override
	public List<ShopSimpleProductResponse> findByShopCategory(Long shopCategoryId) {
		findShopCategoryById(shopCategoryId);
		List<ShopProduct> model = repository.findByShopCategory(shopCategoryId);
		return mapper.response(model);
	}

	@Override
	public List<ShopProductVariationStockResponse> findByShopCategoryStock(Long shopCategoryId) {
		findShopCategoryById(shopCategoryId);
		List<ShopProduct> model = repository.findByShopCategory(shopCategoryId);
		return mapper.modelToResponse(model);
	}

	@Override
	public List<ShopProductPhysicalResponse> findByShopByName(Pageable pageable, Long shopId, String name, Boolean filterStock) {

		if( Objects.nonNull(filterStock)) {	
			
			List<ShopProduct> model = repository.findByShopByNameByStock(shopId, filterStock ,pageable);
			return mapper.responsePhysical(model);	
	      }
			if (Objects.isNull(name) || name.isBlank()) {
			List<ShopProduct> model = repository.findByShop(shopId, pageable);
			return mapper.responsePhysical(model);
		} else {
			List<ShopProduct> model = repository.findByShopByName(shopId, name.toUpperCase(), pageable);
			return mapper.responsePhysical(model);
		}
	}

	@Transactional
	@Override
	public void updateisVitrineShow(Long id) {
		ShopProduct model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"ShopProduct",  "api.error.shop.product.not.found"));

		if (model.getIsVitrineShow()) {
			model.setIsVitrineShow(false);
		} else {
			model.setIsVitrineShow(true);
		}

		model = repository.save(model);
	}

	@Override
	public void enableAndDisable(Long id) {
		ShopProduct model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"Produto",  "api.error.shop.product.not.found"));

		if (model.getEnableDisable()) {
			model.setEnableDisable(false);
		} else {
			model.setEnableDisable(true);
		}

		model = repository.save(model);
	}

	@Transactional
	@Override
	public ShopProductPhysicalResponse createPhysical(Long shopCategoryId, ShopProductPhysicalRequest request) {

		ShopCategory shopCategory = this.findShopCategoryById(shopCategoryId);

		ShopProduct model = mapper.model(request, shopCategory);

		Optional<ProductType> type = producTypeRepository.findById(1L);

		model.setTypeProduct(type.get());

		repository.save(model);

		List<ShopProductVariation> variations = new ArrayList<>();

		for (ShopProductPhysicalVariationRequest variationRequest : request.getVariations()) {
			ShopProductVariation variation = mapperVariation.modelPhysical(variationRequest);
			variation.setShopProduct(model);
			productVariationRepository.save(variation);
			variations.add(variation);
		}

		ShopProductPhysicalResponse response = mapper.responsePhysical(model, true);
		response.setVariations(mapperVariation.responsePhysical(variations));
		return response;
	}

	@Transactional
	@Override
	public ShopProductDigitalResponse createDigital(Long shopCategoryId, ShopProductDigitalRequest request) {

		ShopCategory shopCategory = this.findShopCategoryById(shopCategoryId);

		ShopProduct model = mapper.model(request, shopCategory);

		Optional<ProductType> type = producTypeRepository.findById(2L);

		model.setTypeProduct(type.get());
		repository.save(model);

		List<ShopProductVariation> variations = new ArrayList<>();

		for (ShopProductDigitalVariationRequest variationRequest : request.getVariations()) {
			ShopProductVariation variation = mapperVariation.modelDigital(variationRequest);
			variation.setShopProduct(model);

			variation.setDigitalUrl(validateLink(variation.getDigitalUrl()));

			productVariationRepository.save(variation);
			variations.add(variation);
		}

		ShopProductDigitalResponse response = mapper.responseDigital(model, true);
		response.setVariations(mapperVariation.responseDigital(variations));
		return response;
	}

	@Override
	@Transactional
	public ShopProductDigitalResponse updateDigital(Long id, ShopProductDigitalRequest request) {

		ShopProduct model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "ShopProduct", "api.error.shop.product.not.found"));

		// deleta variações não enviadas
		// List<Long> variationsIdOlds =
		// productVariationRepository.findIdByShopProduct(model.getId());
		List<Long> variationsIdOlds = model.getVariations().stream().map(ShopProductVariation::getId)
				.collect(Collectors.toList());

		List<Long> newIds = request.getVariations().stream().map(ShopProductDigitalVariationRequest::getId)
				.collect(Collectors.toList());

		for (Long variationId : variationsIdOlds) {
			if (!newIds.contains(variationId)) {
				productVariationRepository.deleteById(variationId);
			}
		}

		// adicionando ou editando uma nova variação
		for (ShopProductDigitalVariationRequest variationRequest : request.getVariations()) {
			// Long productId, Long idVariation, ShopProductDigitalVariationRequest request
			if (Objects.isNull(variationRequest.getId())) {
				ShopProductVariation variation = mapperVariation.modelDigital(variationRequest);
				variation.setShopProduct(model);
				productVariationRepository.save(variation);
			} else {

				Optional<ShopProductVariation> optVariation = productVariationRepository
						.findById(variationRequest.getId());
				validate(optVariation, "ShopProductVariation", "api.error.shop.product.variation.not.found");

				ShopProductVariation variation = optVariation.get();

				BeanUtils.copyProperties(variationRequest, variation, "id", "dateModel", "shopProduct");
				productVariationRepository.save(variation);
			}
		}
		BeanUtils.copyProperties(request, model, "id", "dateModel", "typeProduct", "variations");
		model = repository.save(model);

		return mapper.responseDigital(model, true);
	}

	@Override
	@Transactional
	public ShopProductPhysicalResponse updatePhysical(Long id, ShopProductPhysicalRequest request) {

		ShopProduct model = repository.findById(id)

				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "ShopProduct",  "api.error.shop.product.not.found"));

		// deleta variações não enviadas
		List<Long> variationsIdOlds = model.getVariations().stream().map(ShopProductVariation::getId)
				.collect(Collectors.toList());

		List<Long> newIds = request.getVariations().stream().map(ShopProductPhysicalVariationRequest::getId)
				.collect(Collectors.toList());

		for (Long variationId : variationsIdOlds) {
			if (!newIds.contains(variationId)) {
				productVariationRepository.deleteById(variationId);
			}
		}

		// adicionando ou editando uma nova variação
		for (ShopProductPhysicalVariationRequest variationRequest : request.getVariations()) {
			// Long productId, Long idVariation, ShopProductDigitalVariationRequest request
			if (Objects.isNull(variationRequest.getId())) {
				ShopProductVariation variation = mapperVariation.modelPhysical(variationRequest);
				variation.setShopProduct(model);
				productVariationRepository.save(variation);
			} else {
				Optional<ShopProductVariation> optVariation = productVariationRepository
						.findById(variationRequest.getId());
				validate(optVariation, "ShopProductVariation", "api.error.shop.product.variation.not.found");

				ShopProductVariation variation = optVariation.get();
				BeanUtils.copyProperties(variationRequest, variation, "id", "dateModel", "shopProduct");
				productVariationRepository.save(variation);
			}
		}

		BeanUtils.copyProperties(request, model, "id", "dateModel", "typeProduct", "variations");
		model = repository.save(model);

		return mapper.responsePhysical(model, true);
	}

	@Override
	@Transactional
	public ShopProductResponse update(Long id, ShopProductRequest request, Long shopCategoryId) {

		ShopProduct model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "ShopProduct",  "api.error.shop.product.not.found"));

		ShopCategory shopCategory = this.findShopCategoryById(shopCategoryId);

		ShopProduct shopServiceProduct = mapper.model(request, shopCategory);

		BeanUtils.copyProperties(shopServiceProduct, model, "id", "dateModel", "typeProduct");
		model = repository.save(model);
		return mapper.responsePhysical(model, true);
	}

	@Transactional
	@Override
	public ShopProductDigitalResponse createDigitalVariation(Long idShopProduct,
			ShopProductDigitalVariationRequest request) {

		ShopProduct model = repository.findById(idShopProduct)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "ShopProduct",  "api.error.shop.product.not.found"));

		ShopProductVariation variation = mapperVariation.modelDigital(request);
		variation.setShopProduct(model);
		productVariationRepository.save(variation);

		return findDigitalbyId(idShopProduct);

	}
	

	
	

	@Transactional
	@Override
	public ShopProductPhysicalResponse createPhysicalVariation(Long idShopProduct,
			ShopProductPhysicalVariationRequest request) {

		ShopProduct model = repository.findById(idShopProduct)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "ShopProduct",  "api.error.shop.product.not.found"));

		ShopProductVariation variation = mapperVariation.modelPhysical(request);
		variation.setShopProduct(model);
		productVariationRepository.save(variation);
		return findPhysicalById(idShopProduct);
	}

	@Transactional
	@Override
	public ShopProductDigitalResponse updateDigitalVariation(Long productId, Long idVariation,
			ShopProductDigitalVariationRequest request) {

		Optional<ShopProduct> optProduct = repository.findById(idVariation);
		validate(optProduct, "ShopProduct",  "api.error.shop.product.not.found");

		Optional<ShopProductVariation> optVariation = productVariationRepository.findById(idVariation);
		validate(optVariation, "ShopProductVariation", "api.error.shop.product.variation.not.found");

		ShopProductVariation variation = optVariation.get();

		BeanUtils.copyProperties(request, variation, "id", "dateModel", "shopProduct");
		variation = productVariationRepository.save(variation);
		return findDigitalbyId(variation.getShopProduct().getId());

	}


	@Transactional
	@Override
	public ShopProductPhysicalResponse updatePhysicalVariation(Long productId, Long idVariation,
			ShopProductPhysicalVariationRequest request) {

		Optional<ShopProduct> optProduct = repository.findById(idVariation);
		validate(optProduct, "Produto", "api.error.shop.product.not.found");

		Optional<ShopProductVariation> optVariation = productVariationRepository.findById(idVariation);
		validate(optVariation, "Variação", "api.error.shop.product.variation.not.found");

		ShopProductVariation variation = optVariation.get();

		BeanUtils.copyProperties(request, variation, "id", "dateModel", "shopProduct");
		variation = productVariationRepository.save(variation);
		return findPhysicalById(variation.getShopProduct().getId());

	}


	
	@Transactional
	@Override
    public ShopProductPhysicalResponse updateStockVariationPhysical(Long productId, Long variationId, StockRequest request) {
		
		Optional<ShopProduct> optProduct = repository.findById(productId);
		validate(optProduct, "Produto", "api.error.shop.product.not.found");
		
		Optional<ShopProductVariation> optVariation = productVariationRepository.findById(variationId);
		validate(optVariation, "Variação", "api.error.shop.product.variation.not.found");
		
		ShopProductVariation variation = optVariation.get();
		variation.setAmount(request.getAmount());
		variation.setDisableOutOfStock(request.getDisableOutOfStock());
		BeanUtils.copyProperties(request, variation, "id", "dateModel", "shopProduct");
		variation = productVariationRepository.save(variation);
		return  findPhysicalById(variation.getShopProduct().getId());
		
	}
	
	@Transactional
	@Override
    public ShopProductDigitalResponse updateStockVariationDigital(Long productId, Long variationId, StockRequest request) {
		
		Optional<ShopProduct> optProduct = repository.findById(productId);
		validate(optProduct, "Produto", "api.error.shop.product.not.found");
		
		
		Optional<ShopProductVariation> optVariation = productVariationRepository.findById(variationId);
		validate(optVariation, "Variação", "api.error.shop.product.variation.not.found");
		
		ShopProductVariation variation = optVariation.get();
		variation.setAmount(request.getAmount());
		variation.setDisableOutOfStock(request.getDisableOutOfStock());
		BeanUtils.copyProperties(request, variation, "id", "dateModel", "shopProduct");
		variation = productVariationRepository.save(variation);
		return  findDigitalbyId(variation.getShopProduct().getId());
		
	}
	

	
	@Override
	public ShopProductPhysicalResponse findPhysicalById(Long id) {

		ShopProduct product = repository.findById(id).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "ShopProduct",  "api.error.shop.product.not.found"));
	

		return mapper.responsePhysical(product, true);

	}

	@Override
	public ShopProductDigitalResponse findDigitalbyId(Long id) {
          ShopProduct product = repository.findById(id).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,
        		  "ShopProduct",  "api.error.shop.product.not.found"));	

	
		return mapper.responseDigital(product, true);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Optional<ShopProduct> model = repository.findById(id);
		validate(model, "ShopProduct",  "api.error.shop.product.not.found");
		repository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteVariation(Long productId, Long id) {

		Optional<ShopProduct> opProduct = repository.findById(id);
		validate(opProduct, "ShopProduct", "api.error.shop.product.not.found");
		Optional<ShopProductVariation> model = productVariationRepository.findById(id);
		validate(model, "ShopProductVariation", "api.error.shop.product.variation.not.found");
		productVariationRepository.deleteById(id);
	}

	@Override
	public EnumUnitResponse getEnumUnits() {

		return EnumUnitResponse.builder().unitOfColor(UnitOfColor.getUnitOfColor())
				.unitOfMeasure(UnitOfMeasure.getUnitOfMeasure()).unitOfWeight(UnitOfWeight.getUnitOfWeight())
				.unitOfSize(UnitOfSize.getUnitOfSize()).build();

	}

	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}

	private ShopCategory findShopCategoryById(Long shopCategoryId) {
		Optional<ShopCategory> shopCategory = shopCategoryRepository.findById(shopCategoryId);
		validate(shopCategory, "ShopCategory", "api.error.shop.category.not.found");
		return shopCategory.get();
	}

	/**
	 * TODO -> melhorar esse codigo
	 * 
	 * @param link
	 * @return
	 */
	private String validateLink(String link) {

		if (link == null || link.isBlank()) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "url", "api.error.url.incorrect");
		}

		String[] linkSplit = link.split("//");

		if (linkSplit.length >= 2) {
			link = "https://" + linkSplit[1];
		} else {
			link = "https://" + linkSplit[0];
		}

		try {
			@SuppressWarnings("unused")
			URL u = new URL(link);
		} catch (MalformedURLException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "url", "api.error.url.incorrect");
		}
		return link;

	}

	@Override
	public List<ShopProductStockResponse> findStockProduct(Long userId, Long categoryId) {
		List<ShopProduct> products = repository.findByShopCategory(categoryId);
		return products.stream().map(p -> mapper.shopProductToShopProductStockResponse(p)).collect(Collectors.toList());
	}

	@Override
	public PersonalProductShop findPersonalProduct(Long id) {
		ShopProduct shop = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "ShopProduct", "api.error.shop.product.not.found"));
		return mapper.modelToPersonalProduct(shop);
	}

	@Override
	public List<PersonalHomeShopResponse> findPersonalHome(PersonalHomeFilter filter) {
		List<ShopProduct> products = repository.findShopProductPersonal(filter);
		return products.stream().map(p -> mapper.modelToPersonalHomeProduct(p)).collect(Collectors.toList());
	}

}
