package br.com.idolink.api.controller.swagger.publics;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.NewsletterFormRequest;
import br.com.idolink.api.dto.response.NewsletterFormResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller do Formulário Newsletter (Público)")
public interface NewsletterFormControllerSwaggerPublic {	
	
	@ApiOperation(value = "Salvar Um Formulário de Newsletter de acordo ao newsletter configurado no Ido", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = NewsletterFormResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<NewsletterFormResponse> create( Long configNewsletterFormId, NewsletterFormRequest request );

}
