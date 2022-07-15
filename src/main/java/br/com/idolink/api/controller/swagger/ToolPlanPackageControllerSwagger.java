package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.ToolPlanPackagesRequest;
import br.com.idolink.api.dto.response.ToolPlanPackagesResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.model.enums.ToolPlanPackageType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller para Planos de Pacotes de Ferramentas")
public interface ToolPlanPackageControllerSwagger {

	@ApiOperation(value = "Busca os planos dos pacotes de ferramentas", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ToolPlanPackagesResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ToolPlanPackagesResponse> findToolPlanPackage(ToolPlanPackageType toolType);
	
	@ApiOperation(value = "Cria um plano de pacotes de ferramentas", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ToolPlanPackagesResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ToolPlanPackagesResponse> create(@Valid @RequestBody ToolPlanPackagesRequest request);
	
	@ApiOperation(value = "Atualiza um plano de pacotes de ferramentas", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ToolPlanPackagesResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ToolPlanPackagesResponse> update(@Valid @RequestBody ToolPlanPackagesRequest request, @PathVariable Long id);

	@ApiOperation(value = "Busca os planos dos pacotes de ferramentas ativos", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ToolPlanPackagesResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ToolPlanPackagesResponse>> findAll();
}
