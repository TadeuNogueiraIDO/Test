package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.LinkRequest;
import br.com.idolink.api.dto.response.LinkResponse;


public interface LinkService{

	 LinkResponse findById(Long id);

	 LinkResponse create(Long idoId, LinkRequest request);

	 LinkResponse update(Long id, LinkRequest request);

     void delete(Long id);

	 List<LinkResponse> listByIdo(Long idoId);
	 
	 List<LinkResponse> publicListByIdo(Long idoId);
	 
}