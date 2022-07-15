package br.com.idolink.api.controller.swagger;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.response.ShopDashboardResponse;
import br.com.idolink.api.dto.response.VisitorsClicksResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.filter.ShopDashboardFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Dashboard de Shop")
public interface ShopDashboardControllerSwagger {
	
	@ApiOperation(value = "Busca o dashboard do shop por usuário", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopDashboardResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopDashboardResponse> find(ShopDashboardFilter filter);
	
	@ApiOperation(value = "Busca os usuarios validados a receberem email", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = VisitorsClicksResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	public ResponseEntity<ShopDashboardResponse>  conditionEmailSendMonth(boolean sendEmail);
}
