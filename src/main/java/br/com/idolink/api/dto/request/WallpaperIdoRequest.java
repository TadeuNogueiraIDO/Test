package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.WallpaperType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WallpaperIdoRequest {

	@NotNull
	private WallpaperType wallpaperType;
	
	@NotNull
	private String wallpaper;
	
}
