package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.AppearanceButtonRequest;
import br.com.idolink.api.dto.response.AppearanceButtonResponse;
import br.com.idolink.api.dto.response.ButtonShapeResponse;
import br.com.idolink.api.dto.response.ColorsResponse;
import br.com.idolink.api.dto.response.TextFontResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller da Aparência dos Botões")
public interface AppearanceButtonControllerSwagger {
	
	@ApiOperation(value = "Busca Todos Formatos de botão", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = ButtonShapeResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<ButtonShapeResponse>> findShapes();
	
	@ApiOperation(value = "Busca Todas as Fontes para Um botão", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = TextFontResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<TextFontResponse>> findFonts();
	
	@ApiOperation(value = "Busca Todas as Cores para Um botão", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = ColorsResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<ColorsResponse>> findColors();
	
	@ApiOperation(value = "Buscar Configurações de Botão pelo Ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = AppearanceButtonResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceButtonResponse> findByIdo(@PathVariable(name = "ido_id") Long id);
	
	@ApiOperation(value = "Busca Todas Configurações de Um Botão", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = AppearanceButtonResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<AppearanceButtonResponse>> findAll();

	@ApiOperation(value = "Busca Uma Única Aparência do Botão", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = AppearanceButtonResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceButtonResponse> findById(Long id);

	@ApiOperation(value = "Salvar Uma Aparência do Botão", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = AppearanceButtonRequest.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceButtonResponse> create(Long idoId, @Valid @RequestBody AppearanceButtonRequest request);

	@ApiOperation(value = "Atualiza Uma Aparência do Botão", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = AppearanceButtonRequest.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceButtonResponse> update(@RequestBody AppearanceButtonRequest request, Long id);

	@ApiOperation(value = "Retorna a aparência do botão padrão do template usado pelo IDO", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = AppearanceButtonRequest.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceButtonResponse> getAppearanceButtonTemplate(Long id);

	@ApiOperation(value = "Atualiza a aparência do botão do IDO para ser igual a aparência usada pelo template", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = AppearanceButtonRequest.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceButtonResponse> setAppearanceButtonTemplate(Long id);
}
