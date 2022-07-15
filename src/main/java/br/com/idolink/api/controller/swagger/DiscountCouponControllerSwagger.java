package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.NewCouponRequest;
import br.com.idolink.api.dto.response.DiscountCouponResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller de Cupom de Loja" )
public interface DiscountCouponControllerSwagger {
	
	@ApiOperation(value = "Busca cupom pela loja ", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = DiscountCouponResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<DiscountCouponResponse>> findByShop(Long shopId);

	@ApiOperation(value = "Adiciona um novo cupom", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = DiscountCouponResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<DiscountCouponResponse> create (@Valid @RequestBody NewCouponRequest request, Long Id);

	@ApiOperation(value = "Deleta um cupom", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = DiscountCouponResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<DiscountCouponResponse> deleteById(@PathVariable Long id);

	@ApiOperation(value = "Atualiza um cupom pelo seu id", httpMethod = "PUT")
	@ApiResponses({ @ApiResponse(code = 200, response = DiscountCouponResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<DiscountCouponResponse> update(@Valid NewCouponRequest request, Long idCoupon, Boolean status, Long shopId);

	@ApiOperation(value = "Busca um cupom pelo seu id", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = DiscountCouponResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<DiscountCouponResponse> findById(Long idCoupon);


}
