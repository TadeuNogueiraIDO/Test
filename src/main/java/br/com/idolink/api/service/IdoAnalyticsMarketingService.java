package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.IdoAnalyticsMarketingRequestList;
import br.com.idolink.api.dto.response.IdoAnalyticsMarketingResponse;


public interface IdoAnalyticsMarketingService{

	 IdoAnalyticsMarketingResponse findById(Long id);

	 List<IdoAnalyticsMarketingResponse> createOrUpdate(Long idoId, IdoAnalyticsMarketingRequestList request);

	 void delete(Long id);

	 List<IdoAnalyticsMarketingResponse> listByIdo(Long idoId);
    
}