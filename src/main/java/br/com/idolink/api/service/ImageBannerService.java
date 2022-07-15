package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.ImageBannerRequest;
import br.com.idolink.api.dto.response.ImageBannerResponse;

public interface ImageBannerService {

	ImageBannerResponse findById(Long id);
	
	List<ImageBannerResponse> findByIdo(Long id);
	
	List<ImageBannerResponse> publicFindByIdo(Long idoid);
	
	ImageBannerResponse save(Long idoId, ImageBannerRequest request);
	
	ImageBannerResponse update(ImageBannerRequest request, Long id);
	
	void delete(Long id);
}
