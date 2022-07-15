package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.ProductPositionRequest;
import br.com.idolink.api.dto.response.IdoToolPositionResponse;
import br.com.idolink.api.dto.response.ImageCarouselResponse;
import br.com.idolink.api.dto.response.ProductPositionResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Reposicionamento de produto")
public interface ProductPositionControllerSwagger {

	@ApiOperation(value = "Busca todas as posições das produto cadastradas", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoToolPositionResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<List<ProductPositionResponse>> findByProduct(Long shopCategoryId);
			
	@ApiOperation(value = "Atualiza uma lista de posições das produto", httpMethod = "PUT")
	@ApiResponses({@ApiResponse(code = 200, response = IdoToolPositionResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<ProductPositionResponse>> update(@RequestBody List<ProductPositionRequest> requestList, Long shopCategoryId);

	@ApiOperation(value = "Criação de um posicionamento  para um produto", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ImageCarouselResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ProductPositionResponse>> create(Long shopCategoryId, @Valid @RequestBody List<ProductPositionRequest> request);
	
}
