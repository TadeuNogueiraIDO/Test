package br.com.idolink.api.controller.swagger;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.ConfigNewsletterFormRequest;
import br.com.idolink.api.dto.request.NewsletterFormRequest;
import br.com.idolink.api.dto.response.ConfigNewsletterFormResponse;
import br.com.idolink.api.dto.response.NewsletterFormResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller do Formulário Newsletter")
public interface NewsletterFormControllerSwagger {

	@ApiOperation(value = "Busca um único Formulário de Newsletters por ID", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = NewsletterFormResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> findById(Long id);

	@ApiOperation(value = "Busca os formulários de Newsletter registrados para uma configuração de Newsletter", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = NewsletterFormResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> findByConfig(Long id);
	
	
	@ApiOperation(value = "Salvar Um Formulário de Newsletter de acordo ao newsletter configurado no Ido", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = NewsletterFormResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> create( Long configNewsletterFormId, NewsletterFormRequest request);

	@ApiOperation(value = "Deletar um Formulário de Newsletter de acordo ao id passado em parâmetro", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = NewsletterFormResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> delete(Long id);

	@ApiOperation(value = "Busca as configurações de Formulários newsletters cadastrados para um IDO", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ConfigNewsletterFormResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ConfigNewsletterFormResponse> findByIdo(Long idoId);

	
	@ApiOperation(value = "Cria uma nova configuração de formulário Newsletter para um IDO", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ConfigNewsletterFormResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> createConfig(Long idoId, ConfigNewsletterFormRequest request);

	@ApiOperation(value = "Atualiza uma nova configuração de formulário Newsletter", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ConfigNewsletterFormResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> updateConfig(Long id, ConfigNewsletterFormRequest request);

	@ApiOperation(value = "Busca uma única configuração de Formulário de Newsletter por ID", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ConfigNewsletterFormResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> findConfigById(Long id);

	@ApiOperation(value = "Deleta uma única configuração de Formulário de Newsletter por ID", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = NewsletterFormResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> deleteConfig(Long id);

	
	
	
}
