package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.response.UserResponse;
import br.com.idolink.api.dto.response.WallpaperResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.model.WallpaperColor;
import br.com.idolink.api.model.WallpaperGradient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Templates de Wallpaper")
public interface WallpaperControllerSwagger {
	
	@ApiOperation(value = "Busca a Galeria de papael de parede", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = UserResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<Page<WallpaperResponse>> findAllWallpaper(Pageable pageable);
	
	@ApiOperation(value = "Busca todos os degrades de papel de parede", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = UserResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<WallpaperGradient>> findAllColorGradient();
	
	@ApiOperation(value = "Busca todos as cores de fundo de papel de parede", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = UserResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<WallpaperColor>> findAllColor();
}
