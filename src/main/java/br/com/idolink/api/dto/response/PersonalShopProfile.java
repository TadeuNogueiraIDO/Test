package br.com.idolink.api.dto.response;

import java.util.List;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalShopProfile {
	private Long id;
	private String name;
	private String description;
	private BlobFileResponse storeBanner;
	private String link;
	
	private List<PersonalProductShop> shopProduct;
	
}
