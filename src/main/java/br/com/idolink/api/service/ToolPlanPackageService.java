package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.ToolPlanPackagesRequest;
import br.com.idolink.api.dto.response.ToolPlanPackagesResponse;
import br.com.idolink.api.model.enums.ToolPlanPackageType;

public interface ToolPlanPackageService {

	ToolPlanPackagesResponse create(ToolPlanPackagesRequest request);
	
	ToolPlanPackagesResponse findToolPlanPackage(ToolPlanPackageType toolType);
	
	ToolPlanPackagesResponse update(ToolPlanPackagesRequest request,Long id);

	List<ToolPlanPackagesResponse> findAll();
}
