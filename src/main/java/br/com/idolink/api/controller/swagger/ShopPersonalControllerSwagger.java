package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.idolink.api.dto.response.PersonalHomeShopResponse;
import br.com.idolink.api.dto.response.PersonalProductShop;
import br.com.idolink.api.dto.response.PersonalShopProfile;
import br.com.idolink.api.dto.response.ShopGenericResponse;
import br.com.idolink.api.execption.handler.Problem;
import br.com.idolink.api.filter.PersonalHomeFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller para Personal Shop")
public interface ShopPersonalControllerSwagger {
	
	@ApiOperation(value = "Busca o perfil da loja", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = PersonalShopProfile.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<PersonalShopProfile> findProfileShop(Long id);
	
	@ApiOperation(value = "Busca todos perfis de lojas", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = PersonalShopProfile.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ShopGenericResponse>> findAllProfileShop(String filter);
	
	@ApiOperation(value = "Busca o produto da loja", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = PersonalProductShop.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<PersonalProductShop> findProfileProduct(Long id);
	
	@ApiOperation(value = "Busca a as lojas para o home", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = PersonalHomeShopResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<PersonalHomeShopResponse>> findHomeShop(PersonalHomeFilter filter);
	
	
}
