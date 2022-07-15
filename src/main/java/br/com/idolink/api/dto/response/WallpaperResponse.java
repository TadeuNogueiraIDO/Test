package br.com.idolink.api.dto.response;

import br.com.idolink.api.api.integration.response.BlobFileResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class WallpaperResponse {
	
	private Long id;
	
	private String name;
	
	private BlobFileResponse wallpaper;
	
	private BlobFileResponse thumbnail;
}
