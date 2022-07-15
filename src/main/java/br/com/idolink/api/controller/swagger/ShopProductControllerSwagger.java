package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.idolink.api.dto.request.ShopProductDigitalRequest;
import br.com.idolink.api.dto.request.ShopProductDigitalVariationRequest;
import br.com.idolink.api.dto.request.ShopProductPhysicalRequest;
import br.com.idolink.api.dto.request.ShopProductPhysicalVariationRequest;
import br.com.idolink.api.dto.request.ShopProductRequest;
import br.com.idolink.api.dto.response.EnumUnitResponse;
import br.com.idolink.api.dto.response.ProductTypeResponse;
import br.com.idolink.api.dto.response.ShopProductDigitalResponse;
import br.com.idolink.api.dto.response.ShopProductPhysicalResponse;
import br.com.idolink.api.dto.response.ShopProductResponse;
import br.com.idolink.api.dto.response.ShopProductVariationResponse;
import br.com.idolink.api.dto.response.ShopSimpleProductResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Produtos da Loja")
public interface ShopProductControllerSwagger {

	

	@ApiOperation(value = "Buscar os produtos pela categoria", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopProductResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ShopSimpleProductResponse>> findByShopCategory(Long id);

	@ApiOperation(value = "Buscar os tipos de produtos", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ProductTypeResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ProductTypeResponse>> findAll();
	
	@ApiOperation(value = "Buscar os tipos de unidades do sistema para op produto", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, response = EnumUnitResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<EnumUnitResponse> getUnits();

	@ApiOperation(value = "Buscar os produtos de uma loja e/ou pelo nome com/sem filtro de existencia de stock", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopProductResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<ShopProductPhysicalResponse>> findAllByShopByName(Pageable pageable, Long shopId, String name, Boolean stock);
	
	@ApiOperation(value = "Deletar um produto pelo id", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopProductResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(@PathVariable Long id);
	
	@ApiOperation(value = "Alterar o status da exibição do produto na vitrine", httpMethod = "PUT")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> updateisVitrineShow(@PathVariable Long id);
	
	@ApiOperation(value = "Ativar e desativar um produto", httpMethod = "PUT")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> enableAndDisable(@PathVariable Long id);
	
	@ApiOperation(value = "Atualizar os dados básicos de um produto de uma loja. Não modifica a variação do produto",  httpMethod = "PUT")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopProductResponse> update(Long categoryId, @Valid ShopProductRequest request,
			Long id);
	
	//DIGITAL
	
	@ApiOperation(value = "Buscar um único produto Digital", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductDigitalResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopProductDigitalResponse> findDigitalById(Long id);
	
	@ApiOperation(value = "Criar um produto Digital para a categoria passada por parâmetro", httpMethod = "POST")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductDigitalResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopProductDigitalResponse> createDigital(Long categoryId, @Valid ShopProductDigitalRequest request);

	@ApiOperation(value = "Criar uma variação para o produto Digital passado por parâmetro", httpMethod = "POST")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductDigitalResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopProductDigitalResponse> createDigitalVariation(Long idShopProduct,
			@Valid ShopProductDigitalVariationRequest request);

	@ApiOperation(value = "Atualizar uma variação de produto Digital já cadastrado", httpMethod = "PUT")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductDigitalResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopProductDigitalResponse> updateDigitalVariation(Long productId, Long variationId,
			@Valid ShopProductDigitalVariationRequest request);

	@ApiOperation(value = "Deleta uma variação de produto Digital pelo id", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopProductVariationResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> deleteDigitalVariation(Long productId,  Long id);

	@ApiOperation(value = "Atualizar um produto Digital já cadastrado", httpMethod = "PUT")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductDigitalResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopProductResponse> updateDigital(Long productId, @Valid ShopProductDigitalRequest request);

	//PHYSICAL
	
	@ApiOperation(value = "Criar um produto físico para a categoria passada por parâmetro", httpMethod = "POST")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductPhysicalResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopProductPhysicalResponse> createPhysical(Long categoryId,
			@Valid ShopProductPhysicalRequest request);
	
	@ApiOperation(value = "Buscar um unico produto físico", httpMethod = "GET")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductPhysicalResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopProductPhysicalResponse> findPhysicalById(Long id);
	
	@ApiOperation(value = "Criar uma variação para o produto físico passado por parâmetro", httpMethod = "POST")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductPhysicalResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopProductPhysicalResponse> createPhysicalVariation(Long idShopProduct,
			@Valid ShopProductPhysicalVariationRequest request);
	
	@ApiOperation(value = "Atualizar uma variação de produto Físico já cadastrado", httpMethod = "PUT")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductPhysicalResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopProductPhysicalResponse> updatePhysicalVariation(Long productId, Long variationId,
			@Valid ShopProductPhysicalVariationRequest request);
	
	@ApiOperation(value = "Atualizar um produto Físico já cadastrado", httpMethod = "PUT")
	@ApiResponses({
			@ApiResponse(code = 200, response = ShopProductPhysicalResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<ShopProductResponse> updatePhysical(Long productId, @Valid ShopProductPhysicalRequest request);

	@ApiOperation(value = "Deleta uma variação de produto Físico pelo id", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = ShopProductVariationResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> deletePhysicalVariation(Long productId, Long id);
}