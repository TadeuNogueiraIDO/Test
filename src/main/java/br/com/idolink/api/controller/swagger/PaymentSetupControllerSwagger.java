package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.PaymentSetupRequest;
import br.com.idolink.api.dto.response.PaymentSetupResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Configuração de Pagamento")
public interface PaymentSetupControllerSwagger {

	@ApiOperation(value = "Buscar Todas Configurações de Pagamento Cadastradas no sistema", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = PaymentSetupResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<PaymentSetupResponse>> findAll();

	@ApiOperation(value = "Buscar Configurações de Pagamento Pelo Shop", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = PaymentSetupResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<PaymentSetupResponse> findByIdo(@PathVariable(name = "shop_id") Long id);

	@ApiOperation(value = "Salvar Uma Configuração de Pagamento", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = PaymentSetupResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<PaymentSetupResponse> create(@PathVariable(name = "shop_id") Long shopId,
			@Valid @RequestBody PaymentSetupRequest request);
	
	@ApiOperation(value = "Atualizar Uma Configuração de Pagamento", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = PaymentSetupResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<PaymentSetupResponse> update(@Valid @RequestBody PaymentSetupRequest request,
			@PathVariable(name = "id") Long id);
}
