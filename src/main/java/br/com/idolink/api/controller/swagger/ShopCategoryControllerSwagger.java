package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.ShopCategoryRequest;
import br.com.idolink.api.dto.response.ShopCategoryResponse;
import br.com.idolink.api.dto.response.ShopCategorySimpleResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Categoria de Loja")
public interface ShopCategoryControllerSwagger {
	
	@ApiOperation(value = "Buscar as categorias cadastradas para a Loja", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopCategorySimpleResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ShopCategorySimpleResponse>> findAllByShop(Long shopId);
	
	@ApiOperation(value = "Busca Uma Única Categoria de Loja", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopCategoryResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopCategoryResponse> findById(@PathVariable(name = "id") Long id);

	@ApiOperation(value = "Criar uma Categoria de Loja", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopCategoryResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopCategoryResponse> create(@PathVariable Long shopId, @Valid @RequestBody ShopCategoryRequest request);

	@ApiOperation(value = "Atualizar uma Categoria de Loja", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopCategoryResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopCategoryResponse> update(@PathVariable Long id, @Valid @RequestBody ShopCategoryRequest request);
	
	@ApiOperation(value = "Atualizar visualização de um produto da categoria", httpMethod = "PUT")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopCategoryResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> updateHideProduct(@PathVariable Long id);

	@ApiOperation(value = "Deletar uma Categoria de Loja", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopCategoryResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(@PathVariable Long id);


}
