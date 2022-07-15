package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.LinkRequest;
import br.com.idolink.api.dto.response.LinkResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Link")
public interface LinkControllerSwagger {

	@ApiOperation(value = "Busca todos os links de um IDO", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = LinkResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<LinkResponse>> findAllByIdo(Long idIdo);

	@ApiOperation(value = "Salvar um novo link em um ido", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = LinkResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<LinkResponse> create(Long idoId, LinkRequest request);

	@ApiOperation(value = "Deleta um link", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = LinkResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(Long id);
	
	@ApiOperation(value = "Atualiza um link ja cadastrado", httpMethod = "PUT")
	@ApiResponses({
		@ApiResponse(code = 200, response = LinkResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<LinkResponse> update(Long id, LinkRequest request);

	@ApiOperation(value = "Busca um link por id", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = LinkResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<LinkResponse> findById(Long id);

}
