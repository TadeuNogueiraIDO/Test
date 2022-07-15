package br.com.idolink.api.controller.swagger;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.NotificationSettingRequest;
import br.com.idolink.api.dto.request.UserRequest;
import br.com.idolink.api.dto.response.NotificationSettingResponse;
import br.com.idolink.api.dto.response.UserResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Configuração de Conta")
public interface AccountSetupControllerSwagger {

		
	@ApiOperation(value = "Atualizar a Configuração De Notificação", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = NotificationSettingResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<NotificationSettingResponse> update(@PathVariable(name = "id") Long id, @Valid @RequestBody NotificationSettingRequest request);
	
	
	@ApiOperation(value = "Deleta Conta", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = UserRequest.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<UserResponse> deleteAccount();
}
