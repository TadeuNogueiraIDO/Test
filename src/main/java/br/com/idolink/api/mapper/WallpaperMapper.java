package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.response.WallpaperResponse;
import br.com.idolink.api.model.Wallpaper;

@Service
public class WallpaperMapper {
	
	@Autowired
	private StorageApi api;

	public WallpaperResponse response(Wallpaper model) {
		return WallpaperResponse.builder()
		.id(model.getId())
		.name(model.getName())
		.wallpaper(getFile(model.getWallpaper()))
		.thumbnail(getFile(model.getThumbnail()))
		.build();
	}
	
	public List<WallpaperResponse> response(List<Wallpaper> model) {
		return model.stream().map(m -> response(m)).collect(Collectors.toList());
	}
	
	public BlobFileResponse getFile(String uuid) {
		BlobFileResponse response = new BlobFileResponse();
		try {
			response = api.findByUuid(uuid);
		} catch (Exception e) {
		}
		return response;
	}
	
}
