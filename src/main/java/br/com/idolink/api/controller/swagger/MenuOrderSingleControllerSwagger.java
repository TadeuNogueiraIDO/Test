package br.com.idolink.api.controller.swagger;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.MenuOrderCancelSingleItemRequest;
import br.com.idolink.api.dto.response.GenericDetailOrderSingleResponse;
import br.com.idolink.api.dto.response.MenuOrderDeliveryResponse;
import br.com.idolink.api.dto.response.MenuOrderDetailItemResponse;
import br.com.idolink.api.dto.response.MenuOrderPaymentResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.model.enums.QuickPayFinalizationType;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.model.enums.TypeShipping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Menu de Pedidos Single")
public interface MenuOrderSingleControllerSwagger {

	
	
	@ApiOperation(value = "Avulso - Busca os detalhes dos itens do pedido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderDetailItemResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderDetailItemResponse> findMenuOrderSingleDetailItem(Long orderId);
		
	@ApiOperation(value = "Shop - Busca as informações de pagamento do pedido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderPaymentResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderPaymentResponse> findMenuOrderSinglePayment(Long orderId);
	
	@ApiOperation(value = "Shop - Busca as informações de envio do pedido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderDeliveryResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderDeliveryResponse> findMenuOrderSingleDelivery(Long orderId);
	
	@ApiOperation(value = "Avulso - Cancela itens do pedido", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderDetailItemResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderDetailItemResponse> cancelOrderSingle(Long orderId, MenuOrderCancelSingleItemRequest request);
	
	@ApiOperation(value = "Avulso - Altera o status de envio do pedido", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderDetailItemResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderDeliveryResponse> updateMenuOrderDeliverySingleStatus(Long orderId, QuickPaySedingStatus statusSeding);
	
	@ApiOperation(value = "Avulso - Altera o status de pagamento do pedido", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderPaymentResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderPaymentResponse> updateMenuOrderPaymentSingleStatus(Long orderId, QuickPayPaymentStatus statusPayment);


	@ApiOperation(value = "Avulso - Altera o frete do pedido", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderDeliveryResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderDeliveryResponse> updateMenuOrderDeliverySinglefreight(Long orderId, String freigth, TypeShipping typeFreigth);

	@ApiOperation(value = "Avulso - Altera o tipo de pagamento do pedido", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderPaymentResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderPaymentResponse> updateMenuOrderSinglePaymentType(Long orderId,
			QuickPayFinalizationType finalizationType, Long paymentTypeId, String anotherPaymentType);

	@ApiOperation(value = "Avulso - Busca todas as informações do pedido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = GenericDetailOrderSingleResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<GenericDetailOrderSingleResponse> findSingleQuickPaydetails(Long orderId);
	

	
}
