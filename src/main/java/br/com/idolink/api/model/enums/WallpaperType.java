package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WallpaperType {

	GALLERY(1),
	COLOR(2),
	GRADIENT(3),
	UPLOAD(4);
	
	private Integer id;
	
	public static WallpaperType findByType(String type) {

		for(WallpaperType e : values()) {
	        if(e.name().equals(type)) 
	        	return e;
	    }
	    return null;
	}
}
