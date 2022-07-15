package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.DigitalDeliveryRequest;
import br.com.idolink.api.dto.request.LocalPickupRequest;
import br.com.idolink.api.dto.request.OwnShippingRequest;
import br.com.idolink.api.dto.request.PostOfficeShippingRequest;
import br.com.idolink.api.dto.request.ShippingSettingsRequest;
import br.com.idolink.api.dto.response.DigitalDeliveryResponse;
import br.com.idolink.api.dto.response.LocalPickupResponse;
import br.com.idolink.api.dto.response.OwnShippingResponse;
import br.com.idolink.api.dto.response.PostOfficeShippingResponse;
import br.com.idolink.api.dto.response.ShippingSettingsResponse;
import br.com.idolink.api.dto.response.ShopResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Entrega de uma loja")
public interface ShippingsControllerSwagger {
	
	@ApiOperation(value = "Settings - Busca as configurações de entrega de uma Loja", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShippingSettingsResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShippingSettingsResponse> findByShop(Long shopId);
	
	@ApiOperation(value = "Settings - Cria uma configuração de entrega de uma Loja", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ShippingSettingsResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShippingSettingsResponse> createSettings(Long shopId, @Valid ShippingSettingsRequest request);
	
	@ApiOperation(value = "Settings - Atualiza uma configuração de entrega de uma Loja", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShippingSettingsResponse> updateSettings(Long id, @Valid ShippingSettingsRequest request);

	@ApiOperation(value = "Settings - Deleta uma configuração de entrega de uma Loja", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> deleteSettings(Long id);

	@ApiOperation(value = "Frete Próprio - Busca as configurações de entrega por frete próprio para uma configuração de entrega já cadastrada", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = OwnShippingResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<OwnShippingResponse> findByShippingSettings(Long settingId);

	@ApiOperation(value = "Frete Próprio - Cria uma configuração de entrega por frete próprio para uma configuração de entrega já cadastrada", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ShippingSettingsResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<OwnShippingResponse> createOwnShipping(Long settingId, @Valid OwnShippingRequest request);

	@ApiOperation(value = "Frete Próprio - Atualiza uma configuração de entrega por frete próprio através do ID", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })	
	ResponseEntity<OwnShippingResponse> updateOwnShipping(Long id, @Valid OwnShippingRequest request);

	@ApiOperation(value = "Frete Próprio - Deleta uma configuração de entrega por frete próprio através do ID", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> deleteOwnShipping(Long id);

	@ApiOperation(value = "Correios - Busca as configurações de entrega por correios para uma configuração de entrega já cadastrada", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = PostOfficeShippingResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<PostOfficeShippingResponse> findPostOfficeByShippingSettings(Long settingId);

	@ApiOperation(value = "Correios - Cria uma configuração de entrega por correios para uma configuração de entrega já cadastrada", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = PostOfficeShippingResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<PostOfficeShippingResponse> createPostOffice(Long settingId,
			@Valid PostOfficeShippingRequest request);

	@ApiOperation(value = "Correios - Atualiza uma configuração de entrega por correios através do ID", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = PostOfficeShippingResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<PostOfficeShippingResponse> updatePostOffice(Long id, @Valid PostOfficeShippingRequest request);

	@ApiOperation(value = "Correios - Deleta uma configuração de entrega por correios através do ID", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = PostOfficeShippingResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> deletePostOffice(Long id);

	@ApiOperation(value = "Retirada no local - Busca os endereços disponíveis para retirada no local de uma configuração de entrega já cadastrada", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = LocalPickupResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<LocalPickupResponse>> findLocalPickupByShippingSettings(Long settingId, Boolean isEnabled);

	@ApiOperation(value = "Retirada no local - Adiciona um novo endereço de retirada no local para uma configuração de entrega já cadastrada", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = LocalPickupResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<LocalPickupResponse> createLocalPickup(Long settingId, @Valid LocalPickupRequest request);

	@ApiOperation(value = "Retirada no local - Atualiza um endereço de retirada no local através do ID", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = LocalPickupResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<LocalPickupResponse> updateLocalPickup(Long id, @Valid LocalPickupRequest request);

	@ApiOperation(value = "Retirada no local - Deleta um endereço de retirada no local através do ID", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = LocalPickupResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> deleteLocalPickup(Long id);

	@ApiOperation(value = "Entrega Digital - Busca as configurações de entrega digital para uma configuração de entrega já cadastrada", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = DigitalDeliveryResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<DigitalDeliveryResponse> findDigitalDeliveryByShippingSettings(Long settingId);

	@ApiOperation(value = "Entrega Digital - Cria uma configuração de entrega digital para uma configuração de entrega já cadastrada", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = DigitalDeliveryResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<DigitalDeliveryResponse> createDigitalDelivery(Long settingId,
			@Valid DigitalDeliveryRequest request);

	@ApiOperation(value = "Entrega Digital - Atualiza uma configuração de entrega digital através do ID", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = DigitalDeliveryResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<DigitalDeliveryResponse> updateDigitalDelivery(Long id, @Valid DigitalDeliveryRequest request);

	@ApiOperation(value = "Entrega Digital - Deleta uma configuração de entrega digital através do ID", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = DigitalDeliveryResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> deleteDigitalDelivery(Long id);
	

}
