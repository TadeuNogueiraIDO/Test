package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.VideoBannerRequest;
import br.com.idolink.api.dto.response.VideoBannerResponse;

public interface VideoBannerService {

	VideoBannerResponse findById(Long id);
	
	List<VideoBannerResponse> findByIdo(Long id);
	
	List<VideoBannerResponse> publicFindByIdo(Long idIdo);
	
	VideoBannerResponse save(Long idIdo, VideoBannerRequest request);
	
	VideoBannerResponse update(VideoBannerRequest request, Long id);
	
	void delete(Long id);
}
