package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.request.ActivityRequest;
import br.com.idolink.api.dto.response.ActivityFullResponse;
import br.com.idolink.api.dto.response.ActivityResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.model.enums.TypeActivity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(tags = "Controller de Atividade da Loja")
public interface ActivityControllerSwagger {

	@ApiOperation(value = "Buscar as Atividades cadastradas no sistema", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ActivityResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ActivityResponse>> findByTypeActivity(TypeActivity typeActivity);

	@ApiOperation(value = "Buscar uma Atividade por Id", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ActivityResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ActivityResponse> findById(Long id);
	
	@ApiOperation(value = "Cria uma nova atividade para relacionar com uma ou mais loja", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ActivityResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ActivityResponse> create(ActivityRequest request);

	@ApiOperation(value = "Atualiza uma atividade passando o identificador único", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ActivityResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ActivityResponse> update(ActivityRequest request, Long id);

	@ApiOperation(value = "Deleta a Atividade já cadastrada", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ActivityResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(Long id);

	@ApiOperation(value = "Buscar todas as atividades de loja cadastradas no sistema junto com as imagens de apresentação.", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ActivityFullResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Page<ActivityFullResponse>> findAll(Pageable pageable);

	@ApiOperation(value = "Buscar todas as atividades de loja cadastradas no sistema filtradas por Loja. Contém imagem de apresentação da categoria", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ActivityFullResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Page<ActivityFullResponse>> findAllByName(String name, Pageable pageable);
	
}
