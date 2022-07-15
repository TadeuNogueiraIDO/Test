package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.ShopRequest;
import br.com.idolink.api.dto.request.UpdateFormatCardShopRequest;
import br.com.idolink.api.dto.response.ShopResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Apresentação de Loja")
public interface ShopControllerSwagger {
	
	@ApiOperation(value = "Buscar uma Loja pelo Ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopResponse> findByIdo(Long idoId);
	
	@ApiOperation(value = "Buscar todos os shoppings do usuário logado", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ShopResponse>> findAllByUser();

	@ApiOperation(value = "Criar uma Apresentação de Loja contendo os dados iniciais", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopResponse> create(@PathVariable(name = "ido_id") Long idoId, @Valid @RequestBody ShopRequest request);

	@ApiOperation(value = "Atualizar uma Apresentação de Loja passando os dados iniciais", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopResponse> update(@PathVariable Long id,
			@Valid @RequestBody ShopRequest request);
	
	@ApiOperation(value = "Alterar os formatos de visualização dos cards do produto e da vitrine", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopResponse> updateFormatCardShop(@PathVariable(name = "id") Long id,
			@Valid @RequestBody UpdateFormatCardShopRequest request);

	@ApiOperation(value = "Deletar uma Apresentação de Loja pelo identificador", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(@PathVariable Long id);

	@ApiOperation(value = "Buscar uma Loja pelo id", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopResponse> findById(Long id);

	@ApiOperation(value = "Alterar o status de uma Loja", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopResponse.class, message = "Requisição com sucesso"),
	@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopResponse> updateShopStatus(Long id, Boolean status);
}
