package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.idolink.api.dto.request.WalletRequest;
import br.com.idolink.api.dto.response.WalletDetailsResponse;
import br.com.idolink.api.dto.response.WalletGeneralResponse;
import br.com.idolink.api.dto.response.WalletResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Carteira")
public interface WalletControllerSwagger {
	
	@ApiOperation(value = "Cadastra uma carteira para o usuario", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = WalletDetailsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<WalletDetailsResponse> createWallet(@RequestBody WalletRequest request, UriComponentsBuilder uri);
	
	@ApiOperation(value = "Atualiza uma carteira do usuario", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = WalletDetailsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<WalletDetailsResponse> updateWallet( @PathVariable Long id, @RequestBody WalletRequest request);
	
	@ApiOperation(value = "Deleta uma carteira do usuario", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = WalletDetailsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<WalletDetailsResponse> deleteWallet(@PathVariable Long id);
	
	@ApiOperation(value = "Busca uma carteira do usuario", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = WalletDetailsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<WalletDetailsResponse> findById(@PathVariable Long id);
	
	@ApiOperation(value = "Busca todas as carteiras do usuario", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = WalletResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<List<WalletResponse>> findAll();
	
	@ApiOperation(value = "Busca a carteira geral do usuario", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = WalletGeneralResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<WalletGeneralResponse> findWalletGeneral();
}
