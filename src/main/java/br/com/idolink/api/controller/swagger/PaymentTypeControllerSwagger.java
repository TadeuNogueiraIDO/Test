package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.response.PaymentTypeResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(tags = "Controller de Tipos de Pagamento")
public interface PaymentTypeControllerSwagger {

	@ApiOperation(value = "Busca todos os tipos de pagamentos", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = PaymentTypeResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<PaymentTypeResponse>> listPaymentTypes();
	
	@ApiOperation(value = "Busca o tipo de pagamento por id", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = PaymentTypeResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<PaymentTypeResponse> findPaymentTypeById( Long id) ;

		
}
