package br.com.idolink.api.controller.swagger;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.MenuOrderCancelItemRequest;
import br.com.idolink.api.dto.response.GenericDetailOrderResponse;
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

@Api(tags = "Controller de Menu de Pedidos Shop")
public interface MenuOrderShopControllerSwagger {

	@ApiOperation(value = "Shop - Busca os detalhes dos itens do pedido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderDetailItemResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderDetailItemResponse> findMenuOrderShopDetailItem(Long orderId);
	
	@ApiOperation(value = "Avulso - Busca as informações de pagamento do pedido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderPaymentResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderPaymentResponse> findMenuOrderShopPayment(Long orderId);
	
	@ApiOperation(value = "Avulso - Busca as informações de envio do pedido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderDeliveryResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderDeliveryResponse> findMenuOrderShopDelivery(Long orderId);
	
	@ApiOperation(value = "Shop - Cancela itens do pedido", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderDetailItemResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderDetailItemResponse> cancelOrderShop(Long orderId, MenuOrderCancelItemRequest request);
	
	@ApiOperation(value = "Shop - Altera o status de envio do pedido", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderDetailItemResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderDeliveryResponse> updateMenuOrderDeliveryShopStatus(Long orderId, QuickPaySedingStatus statusSeding);
	
	@ApiOperation(value = "Shop - Altera o status de pagamento do pedido", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderPaymentResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderPaymentResponse> updateMenuOrderPaymentShopStatus(Long orderId, QuickPayPaymentStatus statusPayment);

	@ApiOperation(value = "Shop - Altera o frete do pedido", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderDeliveryResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderDeliveryResponse> updateMenuOrderDeliveryShopfreight(Long orderId, String freigth, TypeShipping typeFreigth);
	
	@ApiOperation(value = "Shop - Altera o tipo de pagamento do pedido", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderPaymentResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuOrderPaymentResponse> updateMenuOrderShopPaymentType(Long orderId,
			QuickPayFinalizationType finalizationType,Long paymentTypeId, String anotherPaymentType);

	@ApiOperation(value = "Shop - Busca todas as informações do pedido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = GenericDetailOrderResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<GenericDetailOrderResponse> findShopQuickPaydetails(Long orderId);
	
	
	
}
