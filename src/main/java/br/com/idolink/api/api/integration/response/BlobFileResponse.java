package br.com.idolink.api.api.integration.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlobFileResponse {
	
	private Long id;
	
	private String uuid;
	
	private String url;
	
	private String hash;
}
