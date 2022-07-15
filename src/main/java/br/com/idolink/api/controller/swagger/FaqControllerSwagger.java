package br.com.idolink.api.controller.swagger;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.ConfigFaqRequest;
import br.com.idolink.api.dto.response.ConfigFaqResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Faq")
public interface FaqControllerSwagger {
	
	@ApiOperation(value = "Busca configurações de FAQ pelo id", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ConfigFaqResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ConfigFaqResponse> findById(Long id);

	@ApiOperation(value = "Busca configurações de FAQ pelo ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ConfigFaqResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ConfigFaqResponse> findByIdo(Long id);

	@ApiOperation(value = "Salva uma configuração de FAQ para um Ido", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ConfigFaqResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ConfigFaqResponse> create(Long idIdo, ConfigFaqRequest request);
	
	@ApiOperation(value = "Edita uma configuração de FAQ", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ConfigFaqResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ConfigFaqResponse> update(@Valid @RequestBody ConfigFaqRequest request, Long id);

	@ApiOperation(value = "Deleta uma configuração de FAQ pelo id", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ConfigFaqResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(Long id);


}
