//package br.com.idolink.api.controller.swagger;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import br.com.idolink.api.dto.request.GeneralSettingsRequest;
//import br.com.idolink.api.dto.request.UserStatusRequest;
//import br.com.idolink.api.dto.response.GeneralSettingsResponse;
//import br.com.idolink.api.dto.response.LanguageResponse;
//import br.com.idolink.api.dto.response.UserStatusResponse;
//import br.com.idolink.api.execption.handler.Problem;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//
//@Api(tags = "Controller de Configuração Geral da Conta")
//public interface GeneralSettingsControllerSwagger {
//
//	@ApiOperation(value = "Busca Todas os Idiomas Para Uma Conta", httpMethod = "GET")
//	@ApiResponses({ @ApiResponse(code = 200, response = LanguageResponse.class, message = "Requisição com sucesso"),
//			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
//	ResponseEntity<List<LanguageResponse>> findLanguages();
//
//	@ApiOperation(value = "Atualiza a Configuração da Conta", httpMethod = "PUT")
//	@ApiResponses({ @ApiResponse(code = 200, response = GeneralSettingsResponse.class, message = "Requisição com sucesso"),
//			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
//	ResponseEntity<GeneralSettingsResponse> update(@Valid @RequestBody GeneralSettingsRequest request,
//			@PathVariable(name = "id") Long id);
//
//	@ApiOperation(value = "Atualiza o Status da Conta do Usuário", httpMethod = "PUT")
//	@ApiResponses({ @ApiResponse(code = 200, response = UserStatusResponse.class, message = "Requisição com sucesso"),
//			@ApiResponse(code = 400, response = Problem.class, message = "Usuário inativo") })
//	ResponseEntity<UserStatusResponse> updateStatus(@Valid @RequestBody UserStatusRequest request,
//			@PathVariable(name = "id") Long id);
//}
