package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.response.GeneralLeadsResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.model.enums.StatusLeads;
import br.com.idolink.api.model.enums.TypeLeads;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller Geral de leads")
public interface GeneralLeadsSwagger {
	
	@ApiOperation(value = "Busca Todas as Leads Do Usuario Logado", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response =  GeneralLeadsResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<GeneralLeadsResponse>> listByUser();

	@ApiOperation(value = "Gera Arquivo de Leads Para O Usuario Logado", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response =  GeneralLeadsResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity <byte[]> generateFile(Boolean noDataLimit);


	@ApiOperation(value = "Atualiza o Status da Lead do Usuario Logado", httpMethod = "PUT")
	@ApiResponses({
		@ApiResponse(code = 200, response =  GeneralLeadsResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<GeneralLeadsResponse>> updateStatus(StatusLeads status, TypeLeads type, String idTool);


	@ApiOperation(value = "Deleta uma Lead do Usuario Logado por seu Id e Tipo", httpMethod = "DELETE")
	@ApiResponses({
		@ApiResponse(code = 200, response =  GeneralLeadsResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<GeneralLeadsResponse> delete(Long idLead, TypeLeads type);
}

