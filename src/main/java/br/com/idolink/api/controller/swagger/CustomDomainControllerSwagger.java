package br.com.idolink.api.controller.swagger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.idolink.api.dto.request.CustomDomainRequest;
import br.com.idolink.api.dto.response.CustomDomainResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Dominio customizado")
public interface CustomDomainControllerSwagger {
	
	@ApiOperation(value = "Buscar o dominio customizado pelo ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = CustomDomainResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	public ResponseEntity<CustomDomainResponse> getByIdo(@PathVariable("id") Long idoId);
	
	@ApiOperation(value = "Cadastrar Um dominio Customizado", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = CustomDomainResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	public ResponseEntity<CustomDomainResponse> create(CustomDomainRequest request, @PathVariable("id") Long idoId);

	@ApiOperation(value = "Deletar Um dominio Customizado pelo ido", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = CustomDomainResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(Long Ido);

	@ApiOperation(value = "Atualiza Um dominio Customizado pelo ido", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = CustomDomainResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<CustomDomainResponse> update(CustomDomainRequest request, Long idoId);
	
}
