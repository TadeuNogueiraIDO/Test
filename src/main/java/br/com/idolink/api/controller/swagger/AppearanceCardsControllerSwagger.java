package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.AppearanceCardsRequest;
import br.com.idolink.api.dto.response.AppearanceCardsResponse;
import br.com.idolink.api.dto.response.ButtonShapeResponse;
import br.com.idolink.api.dto.response.ColorsResponse;
import br.com.idolink.api.dto.response.TextFontResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller da Aparência dos Cards")
public interface AppearanceCardsControllerSwagger {
	
	@ApiOperation(value = "Busca Todos Formatos de Card", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = ButtonShapeResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<ButtonShapeResponse>> findShapes();
	
	@ApiOperation(value = "Busca Todas as Fontes para Um Card", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = TextFontResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<TextFontResponse>> findFonts();
	
	@ApiOperation(value = "Busca Todas as Cores para Um Card", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = ColorsResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<ColorsResponse>> findColors();
	
	@ApiOperation(value = "Buscar Configurações de Card pelo Ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = AppearanceCardsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceCardsResponse> findByIdo(@PathVariable(name = "ido_id") Long id);
	
	@ApiOperation(value = "Busca Todas Configurações de Um Card", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = AppearanceCardsResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<AppearanceCardsResponse>> findAll();

	@ApiOperation(value = "Busca Uma Única Aparência do Card", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = AppearanceCardsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceCardsResponse> findById(Long id);

	@ApiOperation(value = "Salvar Aparência dos Cards", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = AppearanceCardsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceCardsResponse> create(Long idoId, @Valid @RequestBody AppearanceCardsRequest request);

	@ApiOperation(value = "Atualiza Aparência dos Cards", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = AppearanceCardsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceCardsResponse> update(@RequestBody AppearanceCardsRequest request, Long id);

	@ApiOperation(value = "Atualiza a aparência do botão do IDO para ser igual a aparência usada pelo template", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = AppearanceCardsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceCardsResponse> setAppearanceCardTemplate(Long id);

	@ApiOperation(value = "Retorna a aparência do botão padrão do template usado pelo IDO", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = AppearanceCardsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceCardsResponse> getAppearanceCardTemplate(Long id);
}
