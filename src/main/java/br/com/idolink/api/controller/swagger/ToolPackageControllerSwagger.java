package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.ToolConfigPackageRequest;
import br.com.idolink.api.dto.response.ToolPlanResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Configuração de Ferramenta para Pacote")
public interface ToolPackageControllerSwagger {

	@ApiOperation(value = "Salva um pacote de ferramentas", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ToolPlanResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ToolPlanResponse> create(@Valid @RequestBody ToolConfigPackageRequest request);
	
	@ApiOperation(value = "Busca todos os pacotes de ferramentas", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ToolPlanResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ToolPlanResponse>> findAll();
	
	@ApiOperation(value = "Busca um pacote de ferramenta", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ToolPlanResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ToolPlanResponse> findById(@PathVariable Long id);
	
	@ApiOperation(value = "Atualiza um pacote de ferramenta", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ToolPlanResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ToolPlanResponse> update(@Valid @RequestBody ToolConfigPackageRequest request, @PathVariable Long id);
	
	@ApiOperation(value = "Deleta um pacote de ferramenta", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ToolPlanResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ToolPlanResponse> delete(@PathVariable Long id);
	
	
	
	

}
