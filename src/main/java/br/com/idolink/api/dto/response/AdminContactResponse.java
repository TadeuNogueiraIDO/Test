package br.com.idolink.api.dto.response;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminContactResponse {
	
	private Long id;
	
	private String title;
	
	private String description;
	
	private BlobFileResponse mainIcon;
	
	private BlobFileResponse secondaryIcon;
	
	private String url;

}
