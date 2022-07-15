package br.com.idolink.api.controller.swagger;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.idolink.api.dto.request.AttachedPdfLeadsRequest;
import br.com.idolink.api.dto.request.AttachedPdfRequest;
import br.com.idolink.api.dto.response.AttachedPdfLeadsResponse;
import br.com.idolink.api.dto.response.AttachedPdfResponse;
import br.com.idolink.api.execption.handler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Controller do Ferramentas - Anexo PDF")
public interface AttachedPdfControllerSwagger {

	@ApiOperation(value = "Busca a lista de pdfs de um Ido", httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, response = AttachedPdfResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<List<AttachedPdfResponse>> getAttachedPdfByIdo(Long idoId);

	@ApiOperation(value = "Salvar os pdfs para um Ido", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = AttachedPdfResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AttachedPdfResponse> create(Long idoId, @Valid @RequestBody AttachedPdfRequest request);

	@ApiOperation(value = "Deleta um pdf de um Ido", httpMethod = "DELETE")
	@ApiResponses({ @ApiResponse(code = 200, response = AttachedPdfResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<Void> delete(Long id);
	
	
	@ApiOperation(value = "Atualiza um pdf de um Ido", httpMethod = "PUT")
	@ApiResponses({
		@ApiResponse(code = 200, response = AttachedPdfResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<AttachedPdfResponse> update(Long id, AttachedPdfRequest request);
	
	
	@ApiOperation(value = "Salvar um leads para um pdf", httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, response = AttachedPdfLeadsResponse.class, message = "Requisição com sucesso"),
			@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado") })
	ResponseEntity<AttachedPdfLeadsResponse> saveLeads(Long idPdf, AttachedPdfLeadsRequest request);
	
	
	@ApiOperation(value = "Busca um único PDF", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = AttachedPdfResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<?> findById(Long id);
	
	@ApiOperation(value = "Busca um único Leads", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = AttachedPdfLeadsResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<?> findLeadsById(Long id);

	@ApiOperation(value = "Busca os Leads alcançados por um PDF", httpMethod = "GET")
	@ApiResponses({
		@ApiResponse(code = 200, response = AttachedPdfLeadsResponse.class, message = "Requisição com sucesso"),
		@ApiResponse(code = 404, response = Problem.class, message = "O recurso não foi encontrado")
	})
	ResponseEntity<List<AttachedPdfLeadsResponse>> findLeadsByAttachedPdf(Long pdfId);
	
	
	
	
	
}
