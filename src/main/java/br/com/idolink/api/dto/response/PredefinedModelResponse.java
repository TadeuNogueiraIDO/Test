package br.com.idolink.api.dto.response;

import java.util.List;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.model.enums.TypeTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredefinedModelResponse {

	private Long id;
	
	private String name;
	
	private BlobFileResponse image;
	
	private Boolean showcase;
	
	private List<PredefinedModelParamResponse> predefModelParams;
	
	private TypeTemplate type;

	private Long classification;
}
