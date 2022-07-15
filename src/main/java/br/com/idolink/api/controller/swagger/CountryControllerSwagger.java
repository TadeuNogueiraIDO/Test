package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.response.CountryResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Países")
public interface CountryControllerSwagger {
	
	@ApiOperation(value = "Busca uma Lista de Países, DDI e suas Moedas", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = CountryResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<CountryResponse>> findAll();

}
