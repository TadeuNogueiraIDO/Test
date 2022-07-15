package br.com.idolink.api.controller.swagger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.idolink.api.dto.response.GatewayIdoResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Gateway do Ido")
public interface GatewayIdoControllerSwagger {

	@ApiOperation(value = "Busca as informações do gateway do ido da carteira do usuario", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response =  GatewayIdoResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")})
	ResponseEntity<GatewayIdoResponse> findGatewayId(@PathVariable Long walletId);
	
	@ApiOperation(value = "Saca o saldo disponivel no gateway do ido da carteira do usuario", httpMethod = "POST")
	@ApiResponses({
		@ApiResponse(code = 200, response =  GatewayIdoResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")})
	ResponseEntity<GatewayIdoResponse> toWithdraw(@PathVariable Long walletId);
}
