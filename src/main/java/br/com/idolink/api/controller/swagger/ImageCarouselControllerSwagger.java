package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.ImageCarouselRequest;
import br.com.idolink.api.dto.response.ImageCarouselResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller do Carrossel de imagens")
public interface ImageCarouselControllerSwagger {
	
	@ApiOperation(value = "Busca um Carrosel de imagens por id", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ImageCarouselResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ImageCarouselResponse> findById(Long id);
	
	@ApiOperation(value = "Busca um Carrosel de imagens pelo ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ImageCarouselResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ImageCarouselResponse>> findByIdo(Long id);
	
	@ApiOperation(value = "Cria um Carrosel de imagens", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ImageCarouselResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ImageCarouselResponse> create(Long idoId, ImageCarouselRequest request);
	
	@ApiOperation(value = "Edita um Carrosel de imagens", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ImageCarouselResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ImageCarouselResponse> update(@RequestBody ImageCarouselRequest request, @PathVariable(name ="id") Long id);
	
	@ApiOperation(value = "Deleta um Carrosel de imagens", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ImageCarouselResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> delete(Long id);

}
