package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.ImageCarouselRequest;
import br.com.idolink.api.dto.response.ImageCarouselResponse;

public interface ImageCarouselService {
	
	List<ImageCarouselResponse> findAll();
	
	ImageCarouselResponse findById(Long id);

	List<ImageCarouselResponse> findByIdo(Long idoId);
	
	List<ImageCarouselResponse> publicFindByIdo(Long idoId);

	ImageCarouselResponse create(Long idIdo, ImageCarouselRequest request);

	ImageCarouselResponse update(Long id, ImageCarouselRequest request);

	void delete(Long id);


	

}
