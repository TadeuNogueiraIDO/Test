package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.response.WallpaperResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.WallpaperMapper;
import br.com.idolink.api.model.Wallpaper;
import br.com.idolink.api.model.WallpaperColor;
import br.com.idolink.api.model.WallpaperGradient;
import br.com.idolink.api.model.enums.WallpaperType;
import br.com.idolink.api.repository.WallpaperColorRepository;
import br.com.idolink.api.repository.WallpaperGradientRepository;
import br.com.idolink.api.repository.WallpaperRepository;
import br.com.idolink.api.service.WallpaperService;

@Service
public class WallpaperServiceImpl implements WallpaperService{

	@Autowired
	private WallpaperGradientRepository gradientRepository;
	
	@Autowired
	private WallpaperColorRepository colorRepository;
	
	@Autowired
	private WallpaperRepository wallpaperRepository;
	
	@Autowired
	private WallpaperGradientRepository wallpaperGradientRepository;
	
	@Autowired
	private StorageApi storageApi;

	@Autowired
	private WallpaperMapper mapper;
	
	@Override
	public Page<WallpaperResponse> listWallpapers(Pageable pageable) {
		
		Page<Wallpaper> model = wallpaperRepository.findAll(pageable);
		
		return  model.map(m -> mapper.response(m));
	}
	
	@Override
	public WallpaperResponse findWallpaperById(Long id) {
		
		Optional<Wallpaper> model = wallpaperRepository.findById(id);
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND,"Wallpaper","api.error.wallpaper.not.found");
		}
		return mapper.response(model.get());
	}
	
	@Override
	public WallpaperResponse findWallpaperByUiid(String uiid) {
		
		Optional<Wallpaper> model = wallpaperRepository.findByFileUiid(uiid);
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND,"Wallpaper","api.error.wallpaper.not.found");
		}
		return mapper.response(model.get());
	}
	
	@Override
	public List<WallpaperGradient> listGradients() {
		List<WallpaperGradient> response = gradientRepository.findAll();
		return response;
	}
	
	@Override
	public WallpaperGradient findGradientById(Long id) {
		Optional<WallpaperGradient> model = gradientRepository.findById(id);
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND,"Wallpaper","api.error.wallpaper.not.found");
		}
		return model.get();
	}
	
	@Override
	public List<WallpaperColor> listColors() {
		List<WallpaperColor> response = colorRepository.findAll();
		return response;
	}
	
	@Override
	public WallpaperColor findColorById(Long id) {
		Optional<WallpaperColor> model = colorRepository.findById(id);
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND,"Wallpaper","api.error.wallpaper.not.found");
		}
		return model.get();
	}
	
	
	@Override
	public String validateAndSetWallpaper(WallpaperType wallpapertype,  String id) {
	if(Objects.nonNull(wallpapertype))	{
		switch (wallpapertype) {
		case COLOR:
			Optional<WallpaperColor> color = colorRepository.findById(Long.valueOf(id));
			if(color.isEmpty()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Color", "api.error.wallpaper.color.invalid");
			}
		break;	
		case GALLERY:
			Optional<Wallpaper> wallpaper = wallpaperRepository.findById(Long.valueOf(id));
			if(wallpaper.isEmpty()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND,"Wallpaper", "api.error.wallpaper.not.found");
			}
		break;		
		case GRADIENT:
			Optional<WallpaperGradient> wallpape = wallpaperGradientRepository.findById(Long.valueOf(id));
			if(wallpape.isEmpty()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND,"Gradient" ,"api.error.wallpaper.gradient.not.found");
			}
		break;
		case UPLOAD:
			BlobFileResponse upload = storageApi.findFileById(Long.valueOf(id));
			if(upload == null) {
				throw new BaseFullException(HttpStatus.NOT_FOUND,"File","api.error.wallpaper.upload.not.found");
			}
		break;
		
		
		}
	 }	
		return id.toString();
				
	}
			
}
