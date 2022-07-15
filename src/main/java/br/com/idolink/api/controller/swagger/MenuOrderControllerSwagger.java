package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.response.MenuOrderResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.filter.MenuOrderFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Menu de Pedidos")
public interface MenuOrderControllerSwagger {
	
	@ApiOperation(value = "Busca os pedidos do usuario baseado em filtros", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuOrderResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<MenuOrderResponse>> findMenuOrder(MenuOrderFilter filter);
	

}
