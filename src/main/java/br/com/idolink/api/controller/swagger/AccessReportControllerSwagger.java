package br.com.idolink.api.controller.swagger;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.AccessReportRequest;
import br.com.idolink.api.dto.response.AccessReportResponse;
import br.com.idolink.api.dto.response.VisitorsClicksResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.model.enums.ReportPeriod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(tags = "Controller de Dashboard")
public interface AccessReportControllerSwagger {

	
	@ApiOperation(value = "Adiciona um novo registro de acesso", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = AccessReportResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AccessReportResponse> create(AccessReportRequest request);
	
	@ApiOperation(value = "Deleta um registro de acesso", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = AccessReportResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(Long id);

	@ApiOperation(value = "Busca os dados para gerar o dashboard", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = VisitorsClicksResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<VisitorsClicksResponse> getVisitorsAndCliscks(String idoIds, ReportPeriod period);
	
	@ApiOperation(value = "Busca os usuarios validados a receberem email", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = VisitorsClicksResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<VisitorsClicksResponse> ConditionEmailSend(Boolean sendEmail);

		
}
