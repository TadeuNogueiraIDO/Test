package br.com.idolink.api.controller.swagger;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.BannerPublicityRequest;
import br.com.idolink.api.dto.response.BannerPublicityResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(tags = "Controller de Carrossel de Publicidade")
public interface BannerPublicityControllerSwagger {

	
	@ApiOperation(value = "Buscar o carrossel de publicidade cadastrado", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = BannerPublicityResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<BannerPublicityResponse> findCarrousel();
	
	@ApiOperation(value = "Cria um novo carrossel de publicidade", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = BannerPublicityResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<BannerPublicityResponse> create(BannerPublicityRequest request);

	@ApiOperation(value = "Atualiza um carrossel de publicidade passando o identificador único", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = BannerPublicityResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<BannerPublicityResponse> update(BannerPublicityRequest request, Long id);

	@ApiOperation(value = "Deleta um carrossel de publicidade já cadastrado", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = BannerPublicityResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(Long id);
	
}
