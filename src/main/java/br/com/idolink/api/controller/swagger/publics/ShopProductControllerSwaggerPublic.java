package br.com.idolink.api.controller.swagger.publics;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.response.ShopProductDigitalResponse;
import br.com.idolink.api.dto.response.ShopProductPhysicalResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Produtos de Loja (Público)")
public interface ShopProductControllerSwaggerPublic {
	
	@ApiOperation(value = "Buscar um unico produto físico", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductPhysicalResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopProductPhysicalResponse> findPhysicalById(Long id);
	
	@ApiOperation(value = "Buscar um único produto Digital", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductDigitalResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopProductDigitalResponse> findDigitalById(Long id);

}
