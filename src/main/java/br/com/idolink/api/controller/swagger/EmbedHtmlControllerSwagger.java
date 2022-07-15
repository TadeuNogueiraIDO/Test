package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.EmbedHtmlRequest;
import br.com.idolink.api.dto.response.EmbedHtmlResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Html Embutido")
public interface EmbedHtmlControllerSwagger {

	@ApiOperation(value = "Busca todos os HTML embutidos (Embed Html) de um IDO", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = EmbedHtmlResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<EmbedHtmlResponse>> findAllByIdo(Long idIdo);

	@ApiOperation(value = "Salvar um novo HTML embutido (Embed Html) em um ido", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = EmbedHtmlResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<EmbedHtmlResponse> create(Long idoId, EmbedHtmlRequest request);

	@ApiOperation(value = "Deleta um HTML embutido (Embed Html) a partir do Id", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = EmbedHtmlResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(Long id);
	
	@ApiOperation(value = "Atualiza um HTML embutidos (Embed Html) já cadastrado", httpMethod = "PUT")
	@ApiResponses({
		@ApiResponse(code = 200, response = EmbedHtmlResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<EmbedHtmlResponse> update(Long id, EmbedHtmlRequest request);

	@ApiOperation(value = "Busca um HTML embutidos (Embed Html) por Id", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = EmbedHtmlResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<EmbedHtmlResponse> findById(Long id);
		
}
