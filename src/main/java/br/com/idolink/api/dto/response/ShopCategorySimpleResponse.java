package br.com.idolink.api.dto.response;

import java.util.List;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopCategorySimpleResponse {

	private Long id;

	private String name;
	
	private BlobFileResponse categoryCover;

	private Boolean hideProduct;
	
	private Boolean hideCategory;
	
	private List<ShopSimpleProductResponse> products;

}
