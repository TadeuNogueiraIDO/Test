package br.com.idolink.api.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopProductRequest {
	
	private String name;
	
	private String description;
	
	private Long MainImage;
	
	private List<Long> additionalImages;
	
	@JsonIgnore
	private ProductTypeRequest typeProduct;
		
	private Boolean isVitrineShow;
	
	private Boolean hasVariation;
			
}
