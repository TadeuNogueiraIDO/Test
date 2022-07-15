package br.com.idolink.api.controller.swagger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.ContactRequest;
import br.com.idolink.api.dto.response.ContactResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Contatos")
public interface ContactControllerSwagger {

	@ApiOperation(value = "Busca a lista de tipos de contados", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ContactResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<Page<ContactResponse>> findAll(Pageable pageable);

	@ApiOperation(value = "Salvar um tipo de contato", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ContactResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ContactResponse> create(ContactRequest request);

	@ApiOperation(value = "Deleta um tipo de contato", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ContactResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(Long id);
	
	@ApiOperation(value = "Atualiza um tipo de Contato", httpMethod = "PUT")
	@ApiResponses({
		@ApiResponse(code = 200, response = ContactResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<ContactResponse> update(Long id, ContactRequest request);
	
	
	@ApiOperation(value = "Atualiza o estatus de um tipo de Contato", httpMethod = "PUT")
	@ApiResponses({@ApiResponse(code = 200, response = ContactResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")})
	ResponseEntity<ContactResponse> updateStatus( Long idContact, Boolean status);
		
		
}
