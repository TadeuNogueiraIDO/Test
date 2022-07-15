package br.com.idolink.api.service;

import br.com.idolink.api.dto.request.BannerPublicityRequest;
import br.com.idolink.api.dto.response.BannerPublicityResponse;

public interface BannerPublicityService {

	BannerPublicityResponse findById(Long id);

	BannerPublicityResponse create(BannerPublicityRequest request);

	BannerPublicityResponse update(Long id, BannerPublicityRequest request);

	void delete(Long id);

	BannerPublicityResponse findCarousel();

	
}
