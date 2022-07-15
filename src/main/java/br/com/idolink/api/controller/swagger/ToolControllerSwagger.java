package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.IdoToolRequest;
import br.com.idolink.api.dto.request.ToolActivatedRequest;
import br.com.idolink.api.dto.request.ToolRequest;
import br.com.idolink.api.dto.response.CurrencyResponse;
import br.com.idolink.api.dto.response.IdoToolResponse;
import br.com.idolink.api.dto.response.ToolActivatedResponse;
import br.com.idolink.api.dto.response.ToolResponse;
import br.com.idolink.api.dto.response.ToolStatusListResponse;
import br.com.idolink.api.dto.response.UserResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.filter.ToolFilter;
import br.com.idolink.api.model.enums.IdoToolStatus;
import br.com.idolink.api.model.enums.ToolCodName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Ferramentas")
public interface ToolControllerSwagger {
	
	@ApiOperation(value = "Busca Todas as moedas e preços das ferramentas", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = UserResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<CurrencyResponse>> findCoins();

	@ApiOperation(value = "Busca a lista de ferramentas", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ToolResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ToolResponse>> findAll(ToolFilter filter);
	
	@ApiOperation(value = "Busca uma ferramenta pelo ID", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ToolResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ToolResponse> findById(Long id);

	@ApiOperation(value = "Salva uma ferramenta", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ToolResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ToolResponse> create(ToolRequest request);

	@ApiOperation(value = "Deleta uma ferramenta", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ToolResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(Long id);
	
	@ApiOperation(value = "Atualiza uma ferramenta", httpMethod = "PUT")
	@ApiResponses({
		@ApiResponse(code = 200, response = ToolResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<ToolResponse> update(Long id, ToolRequest request);
	
	@ApiOperation(value = "Habilitar e desabilitar uma ferramenta", httpMethod = "PUT")
	@ApiResponses({
		@ApiResponse(code = 200, response = ToolResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")})
	 ResponseEntity<ToolActivatedResponse> enableDisableTool(@PathVariable("cod_name") ToolCodName codName,  @PathVariable(name = "id") Long id,@Valid @RequestBody ToolActivatedRequest activatedRequest);
	
	@ApiOperation(value = "Verifica se a versão do app é suportada pela versão da ferramenta (Formato ex: 0.0.1)", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = String.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<String> isSuportedTools(String appVersion, String toolsVersion);
	
	@ApiOperation(value = "Busca a lista de ferramentas disponiveis, e seu status com o IDO. Status possiveis - AVAILABLE, INSTALLED, BOUGHT", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoToolResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ToolStatusListResponse> findAllIdoTools(Long idoId, ToolFilter filter);

	@ApiOperation(value = "Associa uma ferramenta a um Ido", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoToolResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<IdoToolResponse> createIdoTool(Long idoId, IdoToolRequest request);

	@ApiOperation(value = "Busca uma associação de ferramenta com ido pelo ID da associação", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoToolResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<IdoToolResponse> findIdoToolById(Long id);

	
	@ApiOperation(value = "Atualiza uma associação de ferramenta com Ido de acordo com o ID passado por parametro", httpMethod = "PUT")
	@ApiResponses({
		@ApiResponse(code = 200, response = IdoToolResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<IdoToolResponse> updateIdoTool(Long id, @Valid IdoToolStatus status);

	@ApiOperation(value = "Busca a lista de ferramentas associadas, de acordo com o id do ido e o id da ferramenta", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoToolResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ToolStatusListResponse> findByIdoAndTools(Long idoId, Long toolId);
}
