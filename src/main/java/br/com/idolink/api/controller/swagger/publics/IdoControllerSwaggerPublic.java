package br.com.idolink.api.controller.swagger.publics;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.idolink.api.dto.response.IdoResponse;
import br.com.idolink.api.dto.response.IdoToolPositionResponse;
import br.com.idolink.api.dto.response.ShopCategoryStockResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller do IDO (Público)")
public interface IdoControllerSwaggerPublic {

	@ApiOperation(value = "Busca um único Ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<IdoResponse> publicFindById(@PathVariable(name = "id") Long id);

	@ApiOperation(value = "Busca um único Ido pelo dominio", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<IdoResponse> findByDomain(String domain);
	
	@ApiOperation(value = "Buscar as categorias com seus produtos e suas variações a partir da Loja", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopCategoryStockResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ShopCategoryStockResponse>> findAllByShop(@PathVariable(name = "shop_id") Long shopId);
	
	@ApiOperation(value = "Busca todas as posições das ferramentas cadastradas no Ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoToolPositionResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<IdoToolPositionResponse>> findByIdo(Long idoId);

	@ApiOperation(value = "Busca um rascunho pelo dominio", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<IdoResponse> findByIdo(String domain);
}
