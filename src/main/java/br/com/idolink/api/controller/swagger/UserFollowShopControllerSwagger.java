package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.response.ShopResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller para Lojas Seguidas pelo Usuario")
public interface UserFollowShopControllerSwagger {
	
	@ApiOperation(value = "Busca Todas as lojas seguidas pelo usuario", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<ShopResponse>> find(String name);
	
	@ApiOperation(value = "Segue uma loja", httpMethod = "POST")
	@ApiResponses({
		@ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<ShopResponse> vinculate(Long shopId);
	
	@ApiOperation(value = "Deixar de seguir uma loja", httpMethod = "DELETE")
	@ApiResponses({
		@ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<ShopResponse> delete(Long shopId);
	
}
