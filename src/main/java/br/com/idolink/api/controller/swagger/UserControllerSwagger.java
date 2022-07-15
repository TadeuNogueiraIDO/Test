package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.idolink.api.dto.request.GeneralSettingsRequest;
import br.com.idolink.api.dto.request.PasswordRecoveryRequest;
import br.com.idolink.api.dto.request.PersonalDataUserRequest;
import br.com.idolink.api.dto.request.UserPasswordRequest;
import br.com.idolink.api.dto.request.UserRequest;
import br.com.idolink.api.dto.request.UserStatusRequest;
import br.com.idolink.api.dto.response.GeneralSettingsResponse;
import br.com.idolink.api.dto.response.LanguageResponse;
import br.com.idolink.api.dto.response.PersonalDataUserResponse;
import br.com.idolink.api.dto.response.UserResponse;
import br.com.idolink.api.dto.response.UserStatusResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Usuário")
public interface UserControllerSwagger {

	@ApiOperation(value = "Buscar todos os Usuários", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = UserResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Page<UserResponse>> findAll(Pageable pageable);

	@ApiOperation(value = "Buscar um único Usuário", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = UserResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<UserResponse> findById(Long id);

	@ApiOperation(value = "Salvar Um Usuário", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = UserRequest.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<UserResponse> create(@RequestBody UserRequest request);
	
	@ApiOperation(value = "Busca Todas os Idiomas Para Uma Conta", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = LanguageResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<LanguageResponse>> findLanguages();

	@ApiOperation(value = "Atualiza Idioma e Configurações da Conta", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = GeneralSettingsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<GeneralSettingsResponse> update(@Valid @RequestBody GeneralSettingsRequest request);
	
	@ApiOperation(value = "Atualiza o País do usuario logado", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response =   UserResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	 ResponseEntity<UserResponse> updateCountry(@PathVariable(name = "country_id") Long idCountry);

	@ApiOperation(value = "Atualiza o Status da Conta do Usuário", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = UserStatusResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 400, response = Problem.class, message = "Usuário inativo") })
	ResponseEntity<UserStatusResponse> updateStatus(@Valid @RequestBody UserStatusRequest request);

	@ApiOperation(value = "Deletar um Usuário", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = UserRequest.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<UserResponse> delete(Long id);

	@ApiOperation(value = "Alterar senha do Usuário", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = UserPasswordRequest.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<UserResponse> updatePassword(UserPasswordRequest password);
	
	@ApiOperation(value = "Atualizar o Email de um Usuário", httpMethod = "PUT")
	@ApiResponses({@ApiResponse(code = 200, response = UserResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")})
	 ResponseEntity<UserResponse> updateEmailUser( @RequestParam(value="email") String email);

	@ApiOperation(value = "Atualizar o Avatar de um Usuário", httpMethod = "PUT")
	@ApiResponses({@ApiResponse(code = 200, response = UserResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")})
	 ResponseEntity<UserResponse> updateAvatarUser(@RequestParam(value="fileAvatar") Long avatar);

	@ApiOperation(value = "Atualizar os Dados Pessoais de um Usuário", httpMethod = "PUT")
	@ApiResponses({@ApiResponse(code = 200, response = PersonalDataUserResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")})
	ResponseEntity<PersonalDataUserResponse> updateStatus(@Valid @RequestBody PersonalDataUserRequest request);

	@ApiOperation(value = "Buscar um usuário logado", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = UserResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<UserResponse> userlogged();

	@ApiOperation(value = "Enviar Email de Recuperação de senha", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = UserRequest.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<UserResponse> generatePasswordReccovery(@RequestParam String email);

	@ApiOperation(value = "Recuperar Senha por codigo", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = UserRequest.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<UserResponse> recoverPassword(@RequestBody @Valid PasswordRecoveryRequest request);

	@ApiOperation(value = "Deleta um Usuário Logado", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = UserRequest.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<UserResponse> deleteUserLogged();

	
	@ApiOperation(value = "Autorizar o email do usuario logado ", httpMethod = "GET")
	@ApiResponses({@ApiResponse(code = 200, response = UserResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")})
	ResponseEntity<String> emailAuthorization(String token);
	
	@ApiOperation(value = "Validar conta do usuário pelo email eviado para confirmação", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = UserRequest.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<UserResponse> validateAccount();
	
	
}
