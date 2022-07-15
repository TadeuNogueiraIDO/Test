package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.idolink.api.dto.request.IdoPlanPackageRequest;
import br.com.idolink.api.dto.response.IdoPlanPackageResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller para Plano de Pacotes do Usuario")
public interface UserPlanPackageControllerSwagger {

	@ApiOperation(value = "Vincula um plano de pacotes ao usuario", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoPlanPackageResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<IdoPlanPackageResponse> create(@Valid @RequestBody IdoPlanPackageRequest request, @PathVariable(name = "ido_id") Long idoId,
			UriComponentsBuilder uri);
	
	@ApiOperation(value = "Busca todos os planos de pacotes do usuario", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoPlanPackageResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<IdoPlanPackageResponse>> findAll(String idoIdos);
	
	@ApiOperation(value = "Deleta um planos de pacotes do usuario", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoPlanPackageResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<IdoPlanPackageResponse> delete(Long planId, Long idoId);
	
}
