package br.com.idolink.api.dto.request.ido;

import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.idolink.api.dto.request.CategoryRequest;
import lombok.Data;

@Data
public class IdoProfileRequest {
		
	@NotBlank
	private String name;

//	private CountryRequest country;

	@NotBlank
	private String domain;

	private String description;
	
	//@NotBlank
	private List<CategoryRequest> categories;
}
