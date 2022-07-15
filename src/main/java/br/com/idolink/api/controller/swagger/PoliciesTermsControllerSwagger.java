package br.com.idolink.api.controller.swagger;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.PoliciesTermsUseRequest;
import br.com.idolink.api.dto.response.PoliciesTermsUseResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Politicas de uso")
public interface PoliciesTermsControllerSwagger {
	
	@ApiOperation(value = "Deleta as politicas de uso", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = PoliciesTermsUseResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(Long id);

	@ApiOperation(value = "Atualiza politicas de uso", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = PoliciesTermsUseResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<PoliciesTermsUseResponse> update(PoliciesTermsUseRequest request, Long Id);

	@ApiOperation(value = "Busca pelo id da loja as politicas de uso", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = PoliciesTermsUseResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<PoliciesTermsUseResponse> findById(Long id);
}
