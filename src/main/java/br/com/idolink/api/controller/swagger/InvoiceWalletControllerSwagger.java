package br.com.idolink.api.controller.swagger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.idolink.api.dto.response.InvoiceWalletResponse;
import br.com.idolink.api.dto.response.ido.PaymentInvoiceResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.filter.InvoiceFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Carteira de Faturas")
public interface InvoiceWalletControllerSwagger {
	
	@ApiOperation(value = "Busca as faturas do usuario", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = InvoiceWalletResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<InvoiceWalletResponse> find(InvoiceFilter filter);
	
	@ApiOperation(value = "Busca o código do pix para o pagamento da faturas do usuario", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = PaymentInvoiceResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<PaymentInvoiceResponse> payInvoice(@PathVariable Long invoiceId);
	
}
