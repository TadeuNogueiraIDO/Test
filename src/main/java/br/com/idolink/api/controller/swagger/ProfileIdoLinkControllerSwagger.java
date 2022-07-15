package br.com.idolink.api.controller.swagger;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.idolink.api.dto.request.ProfileIdoLinkRequest;
import br.com.idolink.api.dto.response.ProfileIdoLinkResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.model.enums.ProfileIdoCodName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Perfil Do Ido Link ")
public interface ProfileIdoLinkControllerSwagger {
	
	
	@ApiOperation(value = "Busca a lista de ProfileIdoLink por um fitro de nome", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ProfileIdoLinkResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<ProfileIdoLinkResponse> findByCodName(@RequestParam ProfileIdoCodName codName);


	@ApiOperation(value = "Salva uma profileIdoLink", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ProfileIdoLinkResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ProfileIdoLinkResponse> create(@Valid @RequestBody ProfileIdoLinkRequest request);

	
	@ApiOperation(value = "Atualiza uma profileIdoLink", httpMethod = "PUT")
	@ApiResponses({
		@ApiResponse(code = 200, response = ProfileIdoLinkResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<ProfileIdoLinkResponse> update(@PathVariable Long id, @Valid @RequestBody ProfileIdoLinkRequest request);
	
}
