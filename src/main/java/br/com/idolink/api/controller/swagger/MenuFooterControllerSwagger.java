package br.com.idolink.api.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.ItemMenuFooterRequest;
import br.com.idolink.api.dto.request.MenuFooterRequest;
import br.com.idolink.api.dto.request.UpdateMenuFooterRequest;
import br.com.idolink.api.dto.response.ItemMenuFooterResponse;
import br.com.idolink.api.dto.response.MenuFooterResponse;
import br.com.idolink.api.dto.response.ShopCategoryForMenuFooterReponse;
import br.com.idolink.api.dto.response.ToolsIdoReponse;
import br.com.idolink.api.dto.response.UpdateMenuFooterResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller do Menu e Rodapé")
public interface MenuFooterControllerSwagger {
	
	@ApiOperation(value = "Buscar as categorias cadastradas para a Loja", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopCategoryForMenuFooterReponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ShopCategoryForMenuFooterReponse>> findAllByShop(@PathVariable(name = "shop_id") Long shopId);
	
	@ApiOperation(value = "Buscar as Ferramentas instaladas para a o Ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ToolsIdoReponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ToolsIdoReponse>> findToolsByIdo(@PathVariable(name = "ido_id") Long idoId);
	
	@ApiOperation(value = "Buscar um Menu e Rodapé pelo Ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuFooterResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuFooterResponse>findByIdo(@PathVariable(name = "ido_id") Long idoId);
	
	@ApiOperation(value = "Buscar um Menu e Rodapé por Id", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuFooterResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuFooterResponse> findById(Long id);
	
	@ApiOperation(value = "Adicionar um item ao Menu e Rodapé", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuFooterResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ItemMenuFooterResponse> addItem(@RequestBody ItemMenuFooterRequest request,
			@PathVariable(name = "menu_id") Long menuId);
	
	@ApiOperation(value = "Criar um Menu e Rodapé", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuFooterResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuFooterResponse> create( Long idoId,  MenuFooterRequest request);
	
	@ApiOperation(value = "Edita um Menu e Rodapé", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = UpdateMenuFooterResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<UpdateMenuFooterResponse> updateMenuFooter(@RequestBody UpdateMenuFooterRequest request, @PathVariable(name ="id") Long id);
	
	@ApiOperation(value = "Edita um Item do Menu e Rodapé", httpMethod = "PUT")
    @ApiResponses({ @ApiResponse(code = 200, response = ItemMenuFooterResponse.class, message = "Requisição com sucesso"),
            @ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
    ResponseEntity<ItemMenuFooterResponse> updateItemMenuFooter(ItemMenuFooterRequest request, Long itemId, Long menuId);
	
	@ApiOperation(value = "Ocultar um Item do Menu e Rodapé", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = ItemMenuFooterResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ItemMenuFooterResponse> hideItem(@PathVariable(name ="item_id") Long itemId, @PathVariable(name ="menu_id") Long menuId);
	
	@ApiOperation(value = "Deletar um Menu e Rodapé", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?>deleteMenuFooter(@PathVariable(name = "id") Long id);
	
	@ApiOperation(value = "Deletar um Item do Menu e Rodapé", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ItemMenuFooterResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<?> delete(Long itemId, Long menuId);
	
	@ApiOperation(value = "Atualizar Menu e Rodapé", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = MenuFooterResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<MenuFooterResponse> update(MenuFooterRequest request, Long id);
	
		
		

}
