package br.com.idolink.api.controller.swagger;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.response.PortifolioMovimentationGeneralResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.filter.PortifolioMovimentationFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Carteira de Movimentações")
public interface PortifolioMovementsControllerSwagger {

	@ApiOperation(value = "Busca as movimentações de carteira", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = PortifolioMovimentationGeneralResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<PortifolioMovimentationGeneralResponse> findPortifolioMovements(PortifolioMovimentationFilter filter);
}
