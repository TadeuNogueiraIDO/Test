package br.com.idolink.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WallpaperAligment {
	
	TOPLEFT("topLeft"),
	TOPCENTER("topCenter"),
	TOPRIGHT("topRight"), 
	CENTERLEFT("centerLeft"), 
	CENTER("center"), 
	CENTERRIGHT("centerRight"), 
	BOTTOMLEFT("bottomLeft"), 
	BOTTOMCENTER("bottomCenter"), 
	BOTTOMRIGHT("bottomRight"); 

	public String name;

}
