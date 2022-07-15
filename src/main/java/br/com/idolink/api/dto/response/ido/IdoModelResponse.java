package br.com.idolink.api.dto.response.ido;

import java.util.List;

import br.com.idolink.api.dto.response.CategoryResponse;
import br.com.idolink.api.dto.response.IdoModelParamResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdoModelResponse {

    private Long id;
	
	private String name;

	private String country;

	private String domain;

	private String description;
	
	private List<CategoryResponse> categories;

	private List<IdoModelParamResponse> modelParams;
			
}
