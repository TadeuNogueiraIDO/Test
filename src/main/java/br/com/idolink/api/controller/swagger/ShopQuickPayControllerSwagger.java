package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.QuickPayFinishRequest;
import br.com.idolink.api.dto.request.QuickPayOrderRequest;
import br.com.idolink.api.dto.request.ShopQuickPayCheckoutRequest;
import br.com.idolink.api.dto.response.ShopQuickPayCheckoutResponse;
import br.com.idolink.api.dto.response.ShopQuickPayResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(tags = "Controller de Venda Rápida - Com loja e Sem Gateray Ido")
public interface ShopQuickPayControllerSwagger {

	
	@ApiOperation(value = "Busca uma Venda por ID", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopQuickPayResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> findById(Long id);

	@ApiOperation(value = "Busca as vendas realizadas de acordo ao Id do usuário logado", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopQuickPayResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ShopQuickPayResponse>> findShopQuickPayByUser();

	@ApiOperation(value = "Faz um novo pedido, retornando: Dados do pedido, subtotal e total ", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopQuickPayCheckoutResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopQuickPayCheckoutResponse> createShopQuickPay(ShopQuickPayCheckoutRequest profileRequest);

	@ApiOperation(value = "Atualiza o pedido, informando o MODO de pagamento (Pix, receber na hora ou enviar proposta) e os dados do cliente (opcional)", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopQuickPayResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopQuickPayResponse> updateOrderShopQuickPay(Long payId, @Valid QuickPayOrderRequest profileRequest);

	@ApiOperation(value = "Atualiza o pedido. Atualiza o TIPO de pagamento. Altera status do pagamento para pago e o status do envio para entregue", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopQuickPayResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopQuickPayResponse> orderPaymentAndSeding(Long payId,
			@Valid QuickPayFinishRequest profileRequest);

	@ApiOperation(value = "Atualiza o pedido. Atualiza o TIPO de pagamento. Altera status do pagamento para pago e o status do envio para Pendente", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopQuickPayResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopQuickPayResponse> orderPaymentAndWaitingDelivery(Long payId,
			@Valid QuickPayFinishRequest profileRequest);
			
}
