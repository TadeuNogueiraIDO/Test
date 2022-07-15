package br.com.idolink.api.dto.response;


import br.com.idolink.api.api.integration.response.BlobFileResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShopGenericResponse {
	
	private Long id;
	
	private String name;
	
	private BlobFileResponse storeBanner;

	

}
