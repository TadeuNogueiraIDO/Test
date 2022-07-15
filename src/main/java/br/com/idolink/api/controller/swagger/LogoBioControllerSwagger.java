package br.com.idolink.api.controller.swagger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.LogoBioRequest;
import br.com.idolink.api.dto.response.LogoBioResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Logo e Bio")
public interface LogoBioControllerSwagger {

	@ApiOperation(value = "Busca Logobio Pelo Ido", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response =  LogoBioResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")})
	ResponseEntity<LogoBioResponse> findByIdo(@PathVariable(name = "ido_id") Long idoId);
	
	@ApiOperation(value = "Atualizar a Logo e Bio do Ido", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = LogoBioResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<LogoBioResponse> updateByIdo(@PathVariable(name = "ido_id")Long idoId,@RequestBody LogoBioRequest request);

}
