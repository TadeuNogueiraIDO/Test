package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.IdoToolPositionRequest;
import br.com.idolink.api.dto.response.IdoToolPositionResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Reposicionamento de Ferramentas no Ido")
public interface IdoToolPositionControllerSwagger {
	
	@ApiOperation(value = "Busca todas as posições das ferramentas cadastradas no Ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoToolPositionResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<IdoToolPositionResponse>> findByIdo(Long idoId);
			
	@ApiOperation(value = "Atualiza uma lista de posições das ferramentas no Ido", httpMethod = "PUT")
	@ApiResponses({@ApiResponse(code = 200, response = IdoToolPositionResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<IdoToolPositionResponse>> update(List<IdoToolPositionRequest> requestList , Long idoId);
	
}
