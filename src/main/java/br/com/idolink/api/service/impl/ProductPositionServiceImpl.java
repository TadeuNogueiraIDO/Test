package br.com.idolink.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.ProductPositionRequest;
import br.com.idolink.api.dto.response.ProductPositionResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ProductPositionMapper;
import br.com.idolink.api.model.ProductPosition;
import br.com.idolink.api.model.ShopCategory;
import br.com.idolink.api.model.ShopProduct;
import br.com.idolink.api.repository.ProductPositionRepository;
import br.com.idolink.api.repository.ShopCategoryRepository;
import br.com.idolink.api.repository.ShopProductRepository;
import br.com.idolink.api.service.ProductPositionService;

@Service
public class ProductPositionServiceImpl implements ProductPositionService{

	@Autowired
	private ProductPositionMapper mapper;
	
	@Autowired
	private ProductPositionRepository repository; 
	
	@Autowired
	private ShopProductRepository shopProductRepository;
	
	@Autowired
	private ShopCategoryRepository shopCategoryRepository;
	
	@Override
	@Transactional
	public List<ProductPositionResponse> create(Long shopCategoryId, List<ProductPositionRequest> request) {
		
		List<ProductPosition> positionsList = new ArrayList<>();
		
		validateRepeatedPosition(request);
		
		for (ProductPositionRequest productPositionList : request) {
			
		
		Optional<ShopCategory> category = shopCategoryRepository.findById(shopCategoryId);
		validate(category, "Category", "api.error.category.not.found");
		
		Optional<ShopProduct> shopProduct = shopProductRepository.findById(productPositionList.getShopProductId());
		validate(shopProduct, "ShopProduct", "api.error.shop.product.not.found");
		
		ProductPosition productPosition = repository.findByPosition(shopCategoryId,productPositionList.getPosition());
		if(Objects.nonNull(productPosition)) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Position", "api.error.product.position.bad.request");
		}
		
		ProductPosition model = mapper.requestToModel(productPositionList,category.get(),shopProduct.get());
		
		positionsList.add(model);	
		
		}
		
	 	repository.saveAll(positionsList);
		return mapper.modelToResponse(positionsList);
				
	}	

	@Override
	@Transactional
	public List<ProductPositionResponse> updateAllPositions(List<ProductPositionRequest>  positionsRequest, Long shopCategoryId) {
		
		Optional<ShopCategory> shopCategory = shopCategoryRepository.findById(shopCategoryId);
		validate(shopCategory, "ShopCategory", "api.error.category.not.found");
		
		
		List<ProductPositionResponse> responses = new ArrayList<ProductPositionResponse>();
			
		
		validateRepeatedPosition(positionsRequest);
		
		for (ProductPositionRequest productPositionRequest : positionsRequest) {
			responses.add(updatePosition( productPositionRequest,shopCategoryId));	
		}
							
		return responses;
	}
	
	@Override
	@Transactional
	public ProductPositionResponse updatePosition(ProductPositionRequest positionRequest, Long shopCategoryId) {
		
		Optional<ShopCategory> shopCategory = shopCategoryRepository.findById(shopCategoryId);
		validate(shopCategory, "ShopCategory", "api.error.category.not.found");
		
		Optional<ShopProduct> shopProduct = shopProductRepository.findById(positionRequest.getShopProductId());
		validate(shopProduct, "ShopProduct", "api.error.shop.product.not.found");
		
		ProductPosition position = repository.findByProductPositionId(shopCategoryId, positionRequest.getShopProductId());
		
		
		if(Objects.nonNull(position)) {
			position.setPosition(positionRequest.getPosition());
			position = repository.save(position);
		}
		else {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Position", "api.error.product.position.bad.request");
		}
		
		return mapper.modelToResponse(position);
	}
	
	@Override
	public List<ProductPositionResponse> findProductBypositionType(Long shopCategoryId){
		
		List<ProductPosition> positions = repository.findByShopCategoryIdOrderByPositionAsc(shopCategoryId);
		
		return mapper.modelToResponse(positions);
		
					
	}
	
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
	
	private void validateRepeatedPosition(List<ProductPositionRequest>  positionsRequest) {
		List<Long> positions = new ArrayList<>(); 
		positionsRequest.stream().forEach(position -> positions.add(position.getPosition()));
		
		Stream<Long> positionsDistinct = positions.stream().distinct();
		
		if(positions.size()!= positionsDistinct.count()) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Position", "api.error.product.position.bad.request");
		}
		
		
			
	}
}
