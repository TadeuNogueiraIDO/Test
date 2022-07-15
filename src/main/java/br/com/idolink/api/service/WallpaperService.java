package br.com.idolink.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.idolink.api.dto.response.WallpaperResponse;
import br.com.idolink.api.model.WallpaperColor;
import br.com.idolink.api.model.WallpaperGradient;
import br.com.idolink.api.model.enums.WallpaperType;

public interface WallpaperService {
	
	Page<WallpaperResponse> listWallpapers(Pageable pageable);
	
	WallpaperResponse findWallpaperById(Long id);

	List<WallpaperGradient> listGradients();
	
	WallpaperGradient findGradientById(Long id);

	List<WallpaperColor> listColors();

	/**
	 * valida o wallpaper passado por parametro
	 * @param wallpapertype
	 * @param objectWallpaper
	 * @return
	 */
	String validateAndSetWallpaper(WallpaperType wallpapertype, String objectWallpaper);

	WallpaperColor findColorById(Long id);

	WallpaperResponse findWallpaperByUiid(String uiid);
	
}
