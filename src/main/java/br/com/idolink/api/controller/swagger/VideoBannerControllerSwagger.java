package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.idolink.api.dto.request.VideoBannerRequest;
import br.com.idolink.api.dto.response.VideoBannerResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller Banner de Video")
public interface VideoBannerControllerSwagger {
	
	@ApiOperation(value = "Busca um banner de video por id", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = VideoBannerResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<VideoBannerResponse> findById(Long id);
	
	@ApiOperation(value = "Busca um banner de video pelo ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = VideoBannerResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<VideoBannerResponse>> findByIdo(Long id);
	
	@ApiOperation(value = "Cria um banner", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = VideoBannerResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<VideoBannerResponse> create(@PathVariable(name = "id_ido") Long id, VideoBannerRequest request);

	@ApiOperation(value = "Edita um banner", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = VideoBannerResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<VideoBannerResponse> update(VideoBannerRequest request, Long id);

	@ApiOperation(value = "Deleta um banner", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = VideoBannerResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> delete(Long id);

}
