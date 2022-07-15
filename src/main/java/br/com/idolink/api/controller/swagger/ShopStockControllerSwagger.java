package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.StockRequest;
import br.com.idolink.api.dto.response.ShopCategoryStockResponse;
import br.com.idolink.api.dto.response.ShopProductDigitalResponse;
import br.com.idolink.api.dto.response.ShopProductPhysicalResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller do Estoque de Loja")
public interface ShopStockControllerSwagger {
	
	@ApiOperation(value = "Buscar as categorias com seus produtos e o estoque", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopCategoryStockResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ShopCategoryStockResponse>> findAllByShop(@PathVariable(name = "shop_id") Long shopId);
	
	
	@ApiOperation(value = "Atualizar uma variação de produto Digital já cadastrado", httpMethod = "PUT")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductDigitalResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopProductDigitalResponse> updateStockVariationDigital(@PathVariable(name = "product_id") Long productId,  @PathVariable(name = "product_variation_id") Long variationId, @Valid @RequestBody StockRequest request);

	
	
	@ApiOperation(value = "Atualizar uma variação de produto Físico já cadastrado", httpMethod = "PUT")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductPhysicalResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<ShopProductPhysicalResponse> updateStockVariationPhysical(@PathVariable(name = "product_id") Long productId, @PathVariable(name = "product_variation_id") Long variationId, @Valid @RequestBody StockRequest request);
}
