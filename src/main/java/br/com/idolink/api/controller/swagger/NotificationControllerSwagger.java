package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.idolink.api.dto.response.NotificationFilterResponse;
import br.com.idolink.api.dto.response.NotificationResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.model.enums.NotificationType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(tags = "Controller de Notificações")
public interface NotificationControllerSwagger {
	
	@ApiOperation(value = "Busca Todas as Notificações Do Usuario Logado", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response =  NotificationResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<NotificationFilterResponse>> listByUserLogged();
	
	@ApiOperation(value = "Busca Todas as Notificações do usuario logado pelo tipo de notificação  informado", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response =  NotificationResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<NotificationResponse>>listByNotificationBytype(@RequestParam  NotificationType type);
	
	
	@ApiOperation(value = "Busca Todas as Notificações de Todos os Usuarios", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response =  NotificationResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<NotificationResponse>> findAllNotification();

	@ApiOperation(value = "Deleta Uma Notificação", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = NotificationResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<Void> delete(@PathVariable Long id);
	
	@ApiOperation(value = "Deleta Todas as Notificações de Um Usuário", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = NotificationResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<Void> deleteAll(@PathVariable Long userId);

	@ApiOperation(value = "Atualiza as Notificações Informadas do Usuário logado", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = NotificationResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<NotificationResponse>> updatRead(Boolean read, String idsNotifications);

	
}
