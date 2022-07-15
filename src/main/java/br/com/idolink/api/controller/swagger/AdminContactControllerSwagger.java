package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.AdminContactRequest;
import br.com.idolink.api.dto.response.AdminContactResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Contato do Administrador")
public interface AdminContactControllerSwagger {
	
	@ApiOperation(value = "Buscar os Contatos do Administrador Cadastrados no sistema", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = AdminContactResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<AdminContactResponse>> findAll();
	
	@ApiOperation(value = "Cadastrar Um Contato do Administrador", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = AdminContactResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AdminContactResponse> create(@Valid @RequestBody AdminContactRequest request);

	@ApiOperation(value = "Atualizar Um Contato do Administrador", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = AdminContactResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AdminContactResponse> update(@Valid @RequestBody AdminContactRequest request,
			@PathVariable(name = "id") Long id);

	@ApiOperation(value = "Deleta Um Contato do Administrador", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = AdminContactResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(@PathVariable Long id);


}
