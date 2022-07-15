package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.idolink.api.dto.request.IdoContactRequest;
import br.com.idolink.api.dto.request.IdoSettingsRequest;
import br.com.idolink.api.dto.request.WallpaperIdoRequest;
import br.com.idolink.api.dto.request.ido.IdoProfileRequest;
import br.com.idolink.api.dto.response.CategoryResponse;
import br.com.idolink.api.dto.response.IdoContactResponse;
import br.com.idolink.api.dto.response.IdoResponse;
import br.com.idolink.api.dto.response.PredefinedModelResponse;
import br.com.idolink.api.dto.response.WallpaperIdoResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.model.enums.PredefinedModelsTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller do IDO")
public interface IdoControllerSwagger {

	@ApiOperation(value = "Busca a lista de contados de um único Ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoContactResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<IdoContactResponse> getContact(Long id);

	@ApiOperation(value = "Salvar contatos para um Ido", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoContactResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<IdoContactResponse> saveContact(Long idIdo,IdoContactRequest request);
	
	@ApiOperation(value = "Atualiza contatos para um Ido", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoContactResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<IdoContactResponse> updateContact(Long idIdo,IdoContactRequest request);

	@ApiOperation(value = "Deleta os contatos do Ido passado em parâmetro", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoContactResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> deleteContact(Long idIdo);
	
	
	@ApiOperation(value = "Cria a estrutura inicial de um IDO", httpMethod = "POST")
	@ApiResponses({
		@ApiResponse(code = 200, response = IdoResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<?> createProfile(IdoProfileRequest request);
	
	@ApiOperation(value = "Busca as categorias para serem listadas na estrutura do IDO", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = CategoryResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<?>> findCategories(String name);

	@ApiOperation(value = "Busca todos os modelos pre-definidos", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = PredefinedModelResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<?>> findAllPredefinedModels();
	
	
	@ApiOperation(value = "Verifica se o domínio é válido", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = Boolean.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<?>> validateDomain(@RequestParam(value="domain", required=true) String domain);

	
	@ApiOperation(value = "Busca os Idos cadastrados para o cliente logado", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = IdoResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<?>> findMyIdos();
	
	
	@ApiOperation(value = "Atualiza um Ido já cadastrado", httpMethod = "PUT")
	@ApiResponses({
		@ApiResponse(code = 200, response = IdoResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<IdoResponse> update(Long id, IdoProfileRequest request);
		
	@ApiOperation(value = "Busca um único Ido", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = IdoResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation(value = "Salva um Wallpaper em um IDO.", httpMethod = "PUT")
	@ApiResponses({
		@ApiResponse(code = 200, response = WallpaperIdoResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<WallpaperIdoResponse> setWallpaper(WallpaperIdoRequest request, Long idIdo);

	@ApiOperation(value = "Atualiza a foto de um Ido já cadastrado", httpMethod = "PUT")
	@ApiResponses({
		@ApiResponse(code = 200, response = IdoResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<IdoResponse> updatePhotoIdo(Long idoId, Long iconId);

	@ApiOperation(value = "Cria e Retorna o rascunho para o IDO, obedecendo as seguintes regras: "
			+ " 1) Já é um Ido publicado - busca o rascunho desse IDO, caso não exista, cria o rascunho sendo uma cópia fiel do Ido publicado."
			+ " 2) Não é um Ido publicado - Retorna o próprio Ido para edição e publicação.", httpMethod = "POST")
	@ApiResponses({
	@ApiResponse(code = 200, response = IdoResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<IdoResponse> createDraftIdo(Long idoId);

	@ApiOperation(value = "Altera o Status do IDO para publicado. Caso o IDO seja um rascunho de um IDO já publicado, o IDO publicado é excluído, mantendo apenas 1.", httpMethod = "PUT")
	@ApiResponses({
	@ApiResponse(code = 200, response = IdoResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<IdoResponse> publishIdo(Long idoId);

	
	@ApiOperation(value = "Altera as configurações gerais do IDO como categoria, status, dominio e linguagem", httpMethod = "PUT")
	@ApiResponses({@ApiResponse(code = 200, response = IdoResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<IdoResponse> changeSettings(Long idoId, IdoSettingsRequest request);

	@ApiOperation(value = "Deleta um Ido a partir do Id passado em parâmetro", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = IdoResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(Long id);

	
	@ApiOperation(value = "Verifica se é permitido alterar o dominio do ido em parametro (Enviar o Ido rascunho)", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = Boolean.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<?>> validateChangeDomain(Long idoId);

	@ApiOperation(value = "Atualiza um template em um IDO.", httpMethod = "PUT")
	@ApiResponses({
		@ApiResponse(code = 200, response = IdoResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<IdoResponse> updateTemplate(PredefinedModelsTemplate template, Long idIdo);

	@ApiOperation(value = "Configura o template em um novo IDO. Gera novas ferramentas de acordo com o template", httpMethod = "POST")
	@ApiResponses({
		@ApiResponse(code = 200, response = IdoResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<IdoResponse> setNewTemplate(PredefinedModelsTemplate template, Long idIdo);
		
}
