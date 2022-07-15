package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.idolink.api.dto.request.ImageBannerRequest;
import br.com.idolink.api.dto.response.ImageBannerResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller Banner de imagens")
public interface ImageBannerControllerSwagger {
	
	@ApiOperation(value = "Busca um banner por id", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ImageBannerResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ImageBannerResponse> findById(Long id);
	
	@ApiOperation(value = "Busca um banner pelo ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ImageBannerResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ImageBannerResponse>> findByIdo(Long id);
	
	@ApiOperation(value = "Cria um banner", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ImageBannerResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ImageBannerResponse> create(@PathVariable(name = "ido_id") Long idoId, ImageBannerRequest request);

	@ApiOperation(value = "Edita um banner", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ImageBannerResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ImageBannerResponse> update(ImageBannerRequest request, Long id);

	@ApiOperation(value = "Deleta um banner", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ImageBannerResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> delete(Long id);

}
