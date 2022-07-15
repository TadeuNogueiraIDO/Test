package br.com.idolink.api.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(value = Include.NON_NULL)
public class PersonalProductShop {

	private Long id;
	
	private String name;
	
	private String description;
	
	private BigDecimal price;
	
	private List<BlobFileResponse> additionalImages;
	
	private BlobFileResponse mainImage;
	
	private String link;
	
	private Long categoryId;
}
