package br.com.idolink.api.controller.swagger;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.ConfigContactUsRequest;
import br.com.idolink.api.dto.request.FormContactUsRequest;
import br.com.idolink.api.dto.response.ConfigContactUsResponse;
import br.com.idolink.api.dto.response.FormContactUsResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Configuração de Formulário Fale Conosco e de envio de mensagens Fale Conosco")
public interface FormContactUsControllerSwagger {

	@ApiOperation(value = "Busca um único Formulário de mensagem Fale Conosco enviado, por id", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = FormContactUsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> findById(Long id);

	@ApiOperation(value = "Salvar/enviar um formulário/mensagem Fale Conosco", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = FormContactUsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> create(Long configContactId, @Valid @RequestBody FormContactUsRequest request);

	@ApiOperation(value = "Deletar um formulário/mensagem Fale Conosco", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = FormContactUsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> delete(Long id);
	
	@ApiOperation(value = "Busca as configurações do Formulário Fale Conosco cadastradas para o negócio. Aqui é possível verificar os campos habilitados que serão mostrados no Formulário/Mensagem", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ConfigContactUsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ConfigContactUsResponse> findConfigContactUsByIdo(Long idoId);
	
	@ApiOperation(value = "Cria uma nova configuração de Formulário Fale Conosco para o Ido. Aqui é possível habilitar/desabilitar os campos que serão mostrados no formulário.", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ConfigContactUsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> createConfig(Long idoId, @Valid @RequestBody ConfigContactUsRequest request);
	
	@ApiOperation(value = "Atualiza uma configuração de Formulário Fale Conosco para o Ido. Aqui é possível habilitar/desabilitar os campos que serão mostrados no formulário.", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ConfigContactUsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> updateConfig(Long id, @Valid @RequestBody ConfigContactUsRequest request);
	
	@ApiOperation(value = "Busca uma única configuração de Formulário Fale Conosco por id", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ConfigContactUsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> findConfigById(Long id);
	
	
	@ApiOperation(value = "Deleta uma configuração de Formulário Fale Conosco passando o id", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ConfigContactUsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> deleteConfig(Long id);
	

}

