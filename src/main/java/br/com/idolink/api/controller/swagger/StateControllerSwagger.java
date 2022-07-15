package br.com.idolink.api.controller.swagger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.response.StateResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Estados")
public interface StateControllerSwagger {
	
	@ApiOperation(value = "Buscar as Atividades cadastradas no sistema", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = StateResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Page<StateResponse>> findAll(Pageable pageable);

}
