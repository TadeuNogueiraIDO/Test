package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.AnalyticsMarketingRequest;
import br.com.idolink.api.dto.response.AnalyticsMarketingResponse;


public interface AnalyticsMarketingService{

	 AnalyticsMarketingResponse findById(Long id);

	 AnalyticsMarketingResponse create(Long idoId,  AnalyticsMarketingRequest request);

	 AnalyticsMarketingResponse update(Long id,  AnalyticsMarketingRequest request);

     void delete(Long id);

	List<AnalyticsMarketingResponse> findAll();
	     
}