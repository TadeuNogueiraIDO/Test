package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.response.TimezoneResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Fuso Horário")
public interface TimezoneControllerSwagger {
	
	@ApiOperation(value = "Busca Todas os Fuso Horários", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response =  TimezoneResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<TimezoneResponse>> findAll();

}
