package br.com.idolink.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idolink.api.annotation.CheckSecurity;
import br.com.idolink.api.controller.swagger.WallpaperControllerSwagger;
import br.com.idolink.api.dto.response.WallpaperResponse;
import br.com.idolink.api.model.WallpaperColor;
import br.com.idolink.api.model.WallpaperGradient;
import br.com.idolink.api.service.WallpaperService;

@RestController
@RequestMapping("/wallpaper-template")
public class WallpaperController implements WallpaperControllerSwagger{
	
	@Autowired
	public WallpaperService service;
	

	@GetMapping("/gallery")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<Page<WallpaperResponse>> findAllWallpaper(Pageable pageable) {
		Page<WallpaperResponse> response = service.listWallpapers(pageable);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/gradients")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<WallpaperGradient>> findAllColorGradient() {
		List<WallpaperGradient> response = service.listGradients();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/colors")
	@CheckSecurity.User.PodeAcessar
	public ResponseEntity<List<WallpaperColor>> findAllColor() {
		List<WallpaperColor> response = service.listColors();
		return ResponseEntity.ok(response);
	}

}
