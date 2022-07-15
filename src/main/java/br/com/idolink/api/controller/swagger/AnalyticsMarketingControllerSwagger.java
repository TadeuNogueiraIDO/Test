package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.IdoAnalyticsMarketingRequestList;
import br.com.idolink.api.dto.response.AnalyticsMarketingResponse;
import br.com.idolink.api.dto.response.IdoAnalyticsMarketingResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Analytics Marketing")
public interface AnalyticsMarketingControllerSwagger {

	@ApiOperation(value = "Busca todos os Analytics Marketings configurados e permitidos no Sistema IdoLink", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = AnalyticsMarketingResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<AnalyticsMarketingResponse>> findAllAnalyticsMarketing();
	
	@ApiOperation(value = "Busca as configurações de Analytics Marketings de um Ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoAnalyticsMarketingResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<IdoAnalyticsMarketingResponse>> findConfigAnalyticsMarketingByIdo(Long idoId);

	@ApiOperation(value = "Configura o parâmetros de Analytics Marketing de um ido", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoAnalyticsMarketingResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<IdoAnalyticsMarketingResponse>> configAnalyticsMarketing(Long idoId, IdoAnalyticsMarketingRequestList request);

	
}
