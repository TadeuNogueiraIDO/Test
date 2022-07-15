package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShopCategoryRequest {

	@NotBlank
	private String name;

	private Long categoryCover;

	private Boolean hideProduct;
}
