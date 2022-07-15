package br.com.idolink.api.controller.swagger.publics;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.FormContactUsRequest;
import br.com.idolink.api.dto.response.FormContactUsResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Configuração de Formulário Fale Conosco e de envio de mensagens Fale Conosco (Público)")
public interface FormContactUsControllerSwaggerPublic {

	@ApiOperation(value = "Salvar/enviar um formulário/mensagem Fale Conosco", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = FormContactUsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<FormContactUsResponse> create(@PathVariable(name = "ido_id") Long idoId, @Valid @RequestBody FormContactUsRequest request);
	
}

