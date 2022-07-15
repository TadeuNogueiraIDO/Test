package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.IdoPlanPackageRequest;
import br.com.idolink.api.dto.response.IdoPlanPackageResponse;

public interface UserPlanPackageService {
	
	IdoPlanPackageResponse create(IdoPlanPackageRequest request, Long idoId);
	
	List<IdoPlanPackageResponse> findAll(Long userId, String idoId);
	
	void delete(Long planId, Long idoId);
}
