package br.com.idolink.api.controller.swagger;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.BusinessHourRequest;
import br.com.idolink.api.dto.response.BusinessHourResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controler para Horario de funcionamento")
public interface BusinessHourControllerSwagger {

	@ApiOperation(value = "busca todos os horario de funcionamento", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = BusinessHourResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	 ResponseEntity<Page<BusinessHourResponse>> findAll(Pageable pageable);

	@ApiOperation(value = "busca por ido um horario de funcionamento", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = BusinessHourResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<BusinessHourResponse> findByIdo(@PathVariable(name = "ido_id") Long id);

	@ApiOperation(value = "busca por id um horario de funcionamento", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = BusinessHourResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<BusinessHourResponse> findById(Long id);

	@ApiOperation(value = "Cria um horario de funcionamento", httpMethod = "POST")
	@ApiResponses({
		@ApiResponse(code = 200, response = BusinessHourResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<BusinessHourResponse> create(@PathVariable(name = "ido_id") Long id, 
			@Valid @RequestBody BusinessHourRequest request);

	@ApiOperation(value = "Atualiza um horario de funcionamento", httpMethod = "PUT")
	@ApiResponses({
		@ApiResponse(code = 200, response = BusinessHourResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<BusinessHourResponse> update(BusinessHourRequest request, Long id);

	@ApiOperation(value = "Deleta um horario de funcionamento", httpMethod = "DELETE")
	@ApiResponses({
		@ApiResponse(code = 200, response = BusinessHourResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<Void> delete(Long id);

}
