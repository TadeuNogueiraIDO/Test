package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.response.BankResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.model.Bank;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller para Bancos")
public interface BankControllerSwagger {

	@ApiOperation(value = "Busca todos os bancos cadastrados", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = Bank.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<List<BankResponse>> getAllBanks();
}
