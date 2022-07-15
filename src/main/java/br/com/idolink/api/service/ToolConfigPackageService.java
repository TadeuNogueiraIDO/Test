package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.ToolConfigPackageRequest;
import br.com.idolink.api.dto.response.ToolPlanResponse;

public interface ToolConfigPackageService {
	
	ToolPlanResponse create(ToolConfigPackageRequest request);

	List<ToolPlanResponse> findAll();

	ToolPlanResponse findById(Long id);

	ToolPlanResponse update(ToolConfigPackageRequest request, Long id);

	void delete(Long id);
}
