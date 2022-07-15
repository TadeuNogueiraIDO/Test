package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.AppearanceTextRequest;
import br.com.idolink.api.dto.response.AppearanceTextResponse;
import br.com.idolink.api.dto.response.ColorsResponse;
import br.com.idolink.api.dto.response.TextFontResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller da Aparência do Texto")
public interface AppearanceTextControllerSwagger {

	@ApiOperation(value = "Busca Todas as Fontes de Texto", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = TextFontResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<TextFontResponse>> findFonts();

	@ApiOperation(value = "Busca Todas as Cores para Fonte de Texto", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ColorsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ColorsResponse>> findColors();

	@ApiOperation(value = "Buscar Configurações de Texto pelo Ido", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, response = AppearanceTextResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceTextResponse> findByIdo(@PathVariable(name = "ido_id") Long id);

	@ApiOperation(value = "Busca Todas Configurações de Texto", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, response = AppearanceTextResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<AppearanceTextResponse>> findAll();

	@ApiOperation(value = "Salvar Configuração de Texto", httpMethod = "POST")
	@ApiResponses({
			@ApiResponse(code = 200, response = AppearanceTextRequest.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceTextResponse> create(Long idoId, @Valid @RequestBody AppearanceTextRequest request);

	@ApiOperation(value = "Atualizar Configuração do Texto", httpMethod = "PUT")
	@ApiResponses({
			@ApiResponse(code = 200, response = AppearanceTextRequest.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceTextResponse> update(@RequestBody AppearanceTextRequest request, Long id);
	
	@ApiOperation(value = "Atualiza a aparência do texto do IDO para ser igual a aparência usada pelo template", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = AppearanceTextResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceTextResponse> setAppearanceTextTemplate(@PathVariable(name = "ido_id") Long id);

	@ApiOperation(value = "Retorna a aparência do texto padrão do template usado pelo IDO", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = AppearanceTextResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AppearanceTextResponse> getAppearanceTextTemplate(@PathVariable(name = "ido_id") Long idoId);
}
