package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.idolink.api.dto.response.MyShopResponse;
import br.com.idolink.api.dto.response.ShopCategorySimpleResponse;
import br.com.idolink.api.dto.response.ShopCategoryStockResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.filter.ShopCategoryStockFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller da Minha Loja")
public interface MyShopControllerSwagger {
	
	@ApiOperation(value = "Buscar todas categorias e produtos cadastrados para a Loja", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopCategorySimpleResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ShopCategoryStockResponse>> findAllByShop(@PathVariable(name = "shop_id") Long shopId, ShopCategoryStockFilter filter);
	
	@ApiOperation(value = "Dashboard contendo informações de categorias e produtos da minha loja", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = MyShopResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MyShopResponse> findByIdo(@PathVariable(name = "ido_id") Long id);
	
	@ApiOperation(value = "Ocultar uma Categoria de Loja", httpMethod = "PUT")
	@ApiResponses({
			@ApiResponse(code = 200, response = void.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> updateHideProduct(Long category_id) ;

}
