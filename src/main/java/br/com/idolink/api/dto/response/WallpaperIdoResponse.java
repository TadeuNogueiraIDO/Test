package br.com.idolink.api.dto.response;

import br.com.idolink.api.model.enums.WallpaperType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WallpaperIdoResponse {
	
	private Long idoId;
		
	private WallpaperType wallpaperType;
	
	private String wallpaper;
}
