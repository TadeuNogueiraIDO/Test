package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.ProductPositionRequest;
import br.com.idolink.api.dto.response.ProductPositionResponse;

public interface ProductPositionService {
	
	 List<ProductPositionResponse> create(Long shopCategoryId, List<ProductPositionRequest> request); 
 
	List<ProductPositionResponse> updateAllPositions(List<ProductPositionRequest>  positionsRequest, Long categoryId);

	ProductPositionResponse updatePosition(ProductPositionRequest positionRequest, Long productTypeId);

	 List<ProductPositionResponse> findProductBypositionType(Long shopCategory);
}