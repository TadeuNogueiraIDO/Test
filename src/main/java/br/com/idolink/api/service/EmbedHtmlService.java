package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.EmbedHtmlRequest;
import br.com.idolink.api.dto.response.EmbedHtmlResponse;


public interface EmbedHtmlService{

	 EmbedHtmlResponse findById(Long id);

	 EmbedHtmlResponse create(Long idoId, EmbedHtmlRequest request);

	 EmbedHtmlResponse update(Long id, EmbedHtmlRequest request);

     void delete(Long id);

	 List<EmbedHtmlResponse> listByIdo(Long idoId);
	 
	 List<EmbedHtmlResponse> publicListByIdo(Long idoId);
	 
}