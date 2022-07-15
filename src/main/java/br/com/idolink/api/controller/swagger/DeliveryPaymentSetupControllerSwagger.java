package br.com.idolink.api.controller.swagger;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.DeliveryPaymentSetupRequest;
import br.com.idolink.api.dto.response.DeliveryPaymentSetupResponse;
import br.com.idolink.api.dto.response.PaymentSetupResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Configuração de Pagamento na Entrega")
public interface DeliveryPaymentSetupControllerSwagger {

	@ApiOperation(value = "Buscar Configurações de Pagamento na Entrega Pelo Shop", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = PaymentSetupResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<DeliveryPaymentSetupResponse> findByShop(@PathVariable(name = "shop_id") Long id);

	@ApiOperation(value = "Salvar Uma Configuração de Pagamento na Entrega", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = DeliveryPaymentSetupResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<DeliveryPaymentSetupResponse> create(@PathVariable(name = "shop_id") Long shopId,
			@Valid @RequestBody DeliveryPaymentSetupRequest request);
	
	@ApiOperation(value = "Atualizar Uma Configuração de Pagamento na Entrega", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = DeliveryPaymentSetupResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<DeliveryPaymentSetupResponse> update(@Valid @RequestBody DeliveryPaymentSetupRequest request,
			@PathVariable(name = "id") Long id);

	
}
