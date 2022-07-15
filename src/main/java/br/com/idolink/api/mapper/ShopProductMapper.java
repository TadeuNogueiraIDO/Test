package br.com.idolink.api.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.request.ShopProductRequest;
import br.com.idolink.api.dto.response.PersonalHomeShopResponse;
import br.com.idolink.api.dto.response.PersonalProductShop;
import br.com.idolink.api.dto.response.ShopProductCategoryFilterResponse;
import br.com.idolink.api.dto.response.ShopProductDigitalResponse;
import br.com.idolink.api.dto.response.ShopProductPhysicalResponse;
import br.com.idolink.api.dto.response.ShopProductStockResponse;
import br.com.idolink.api.dto.response.ShopProductVariationStockResponse;
import br.com.idolink.api.dto.response.ShopSimpleProductResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.model.ShopCategory;
import br.com.idolink.api.model.ShopProduct;
import br.com.idolink.api.model.ShopProductVariation;
import br.com.idolink.api.repository.ShopProductVariationRepository;

@Component
public class ShopProductMapper {
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private StorageApi storageApi;

	@Autowired
	private ShopProductVariationRepository shopProductVariationRepository;

	@Autowired
	private ShopProductVariationMapper variationMapper;

	public ShopProduct model(ShopProductRequest request, ShopCategory shopCategory) {
				
		ShopProduct model = mapper.map(request, ShopProduct.class);
		model.setShopCategory(shopCategory);
		
		model.setAdditionalImages(convertRequestInDataUser(request.getAdditionalImages()));
		return model;
	}

	public ShopProductPhysicalResponse responsePhysical(ShopProduct model, boolean isComplete) {
		ShopProductPhysicalResponse response = mapper.map(model, ShopProductPhysicalResponse.class);
		try {
			if (Objects.nonNull(model.getMainImage())) {
				response.setMainImage(storageApi.findFileById(model.getMainImage()));
			}
		} catch (Exception e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Product",
					"api.error.shop.product.image.inconsistency");
		}

		if (isComplete) {
			response.setAdditionalImages(convertStringInFile(model.getAdditionalImages()));
		}
		
		response.setShopCategory(model.getShopCategory().getId());
		ShopProductCategoryFilterResponse responseCategory = ShopProductCategoryFilterResponse.builder().id(model.getShopCategory().getId())
				.name(model.getShopCategory().getName()).categoryCover(model.getShopCategory().getCategoryCover()).
				hideCategory(model.getShopCategory().getHideCategory()).shop(model.getShopCategory().getShop().getId())
				.build();
		response.setCategory(responseCategory);
		

		
		
		return response;
	}

	public List<ShopProductPhysicalResponse> responsePhysical(List<ShopProduct> model) {
		return model.stream().map(m -> this.responsePhysical(m, false)).collect(Collectors.toList());
	}

	public ShopProductDigitalResponse responseDigital(ShopProduct model, boolean isComplete) {
		ShopProductDigitalResponse response = mapper.map(model, ShopProductDigitalResponse.class);
		
		try {
			if (Objects.nonNull(model.getMainImage())) {
				response.setMainImage(storageApi.findFileById(model.getMainImage()));
			}
		} catch (Exception e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Product",
					"api.error.shop.product.image.inconsistency");
		}

		if (isComplete) {
			response.setAdditionalImages(convertStringInFile(model.getAdditionalImages()));
		}

		response.setShopCategory(model.getShopCategory().getId());

		return response;
	}

	public PersonalProductShop modelToPersonalProduct(ShopProduct product) {
		PersonalProductShop response = PersonalProductShop.builder()
				.additionalImages(convertStringInFile(product.getAdditionalImages()))
				.categoryId(product.getShopCategory().getId())
				.description(product.getDescription())
				.id(product.getId())
				.link(product.getShopCategory().getShop().getIdo().getDomain())
				.name(product.getName())
				.price(product.getVariations().size() > 0 ? product.getVariations().get(0).getPrice() : BigDecimal.ZERO).build();

		try {
			if (Objects.nonNull(product.getMainImage())) {
				response.setMainImage(storageApi.findFileById(product.getMainImage()));
			}
		} catch (Exception e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Product",
					"api.error.shop.product.image.inconsistency");
		}
		System.out.println(response);
		return response;
	}
	
	public PersonalHomeShopResponse modelToPersonalHomeProduct(ShopProduct product) {
		PersonalHomeShopResponse response = PersonalHomeShopResponse.builder()
				.id(product.getShopCategory().getShop().getId())
				.product(modelToPersonalProduct(product)).build();
			try {
			if (Objects.nonNull(product.getMainImage())) {
				response.setMainImage(storageApi.findFileById(product.getShopCategory().getShop().getStoreBanner()));
			}
		} catch (Exception e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Product",
					"api.error.shop.product.image.inconsistency");
		}
		return response;
	}
	
	public List<ShopSimpleProductResponse> response(List<ShopProduct> model) {
		return model.stream().map(m -> this.response(m, false)).collect(Collectors.toList());
	}
	
	public List<ShopProductVariationStockResponse> modelToResponse(List<ShopProduct> model) {
		return model.stream().map(m -> this.modelToResponse(m, false)).collect(Collectors.toList());
	}
	
	public ShopProductVariationStockResponse modelToResponse(ShopProduct model, boolean isComplete) {
		ShopProductVariationStockResponse response = mapper.map(model, ShopProductVariationStockResponse.class);
		try {
			if (Objects.nonNull(model.getMainImage())) {
				response.setMainImage(storageApi.findFileById(model.getMainImage()));
			}
		} catch (Exception e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Product",
					"api.error.shop.product.image.inconsistency");
		}

		return response;
	}

	public ShopSimpleProductResponse response(ShopProduct model, boolean isComplete) {
		ShopSimpleProductResponse response = mapper.map(model, ShopSimpleProductResponse.class);
		try {
			if (Objects.nonNull(model.getMainImage())) {
				response.setMainImage(storageApi.findFileById(model.getMainImage()));
			}
		} catch (Exception e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Product",
					"api.error.shop.product.image.inconsistency");
		}

		if (isComplete) {
			response.setAdditionalImages(convertStringInFile(model.getAdditionalImages()));
		}

		return response;
	}

	public List<ShopProductDigitalResponse> responseDigital(List<ShopProduct> model) {
		return model.stream().map(m -> this.responseDigital(m, false)).collect(Collectors.toList());
	}

	/**
	 * converte a string de lista arquivos do banco para uma lista de arquivos blob
	 * 
	 * @param request
	 * @return
	 */
	private List<BlobFileResponse> convertStringInFile(String dataFile) {

		if(Objects.isNull(dataFile)) {
			return null;
		}
		
		String[] splitDataFile = {};
		List<BlobFileResponse> files = new ArrayList<>();

		splitDataFile = dataFile.split(";");
		
		for (String data : splitDataFile) {

			try {
				BlobFileResponse file = storageApi.findFileById(Long.valueOf(data));
				files.add(file);
			} catch (Exception e) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "Product",
						"api.error.shop.product.image.inconsistency");
			}
		}
		

		return files;
	}

	
	
	public ShopProductStockResponse shopProductToShopProductStockResponse(ShopProduct model) {
		return ShopProductStockResponse.builder()
				.id(model.getId())
				.amount(model.getVariations().stream()
						.map(v -> v.getAmount()).reduce(0, Integer::sum)
						).mainImage(model.getMainImage())
				.name(model.getName())
				.descrition(model.getDescription()).build();
	}

	/**
	 * converte a lista de long de arquivos em uma String para salvar no banco
	 * 
	 * @param longFiles
	 */
	private String convertRequestInDataUser(List<Long> longFiles) {
		
		
		if (Objects.isNull(longFiles)) {
			return null;
		}

		StringBuffer dataFile = new StringBuffer();
		for (Long longFile : longFiles) {
			dataFile.append(longFile).append(";");
		}

		return dataFile.toString();
	}

}
