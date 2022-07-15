package br.com.idolink.api.dto.response.ido;

import java.util.List;

import br.com.idolink.api.dto.response.CategoryResponse;
import br.com.idolink.api.dto.response.CountryResponse;
import br.com.idolink.api.model.enums.WallpaperType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdoProfileResponse {

	private Long id;
	
	private String name;

	private CountryResponse country;

	private String domain;

	private String description;
	
	private List<CategoryResponse> categories;
	
	private WallpaperType wallpaperType;
	
	private Object wallpaper;
}
