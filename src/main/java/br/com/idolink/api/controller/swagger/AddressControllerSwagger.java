package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.AddressRequest;
import br.com.idolink.api.dto.response.AddressResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Endereço")
public interface AddressControllerSwagger {
	
	@ApiOperation(value = "Busca Endreço Do Usuário Logado", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response =  AddressResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")})
	ResponseEntity<List<AddressResponse>> listByaddressUserLogged();
	
	
	@ApiOperation(value = "Buscar os Endereços Cadastrados no sistema", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = AddressResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<AddressResponse>> findAll();

	@ApiOperation(value = "Buscar um Endereço por Id", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = AddressResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AddressResponse> findById(@PathVariable(name = "id") Long id);
	
	@ApiOperation(value = "Cadastrar Um Endereço", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = AddressResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<AddressResponse>create( @Valid @RequestBody AddressRequest request);
	
	@ApiOperation(value = "Atualizar Um Endereço", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = AddressResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AddressResponse> update(@Valid @RequestBody AddressRequest request, @PathVariable(name = "id") Long id);

	@ApiOperation(value = "Deleta Um Endereço", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = AddressResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(@PathVariable Long id);

}
