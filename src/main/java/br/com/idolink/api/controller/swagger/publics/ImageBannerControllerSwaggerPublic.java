package br.com.idolink.api.controller.swagger.publics;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.idolink.api.dto.response.ImageBannerResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller Banner de imagens (Público)")
public interface ImageBannerControllerSwaggerPublic {
	
	
	@ApiOperation(value = "Busca um banner pelo ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ImageBannerResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ImageBannerResponse>> findByIdo( @PathVariable Long id);
	
}
