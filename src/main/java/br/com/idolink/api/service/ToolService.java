package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.ToolActivatedRequest;
import br.com.idolink.api.dto.request.ToolRequest;
import br.com.idolink.api.dto.response.ToolActivatedResponse;
import br.com.idolink.api.dto.response.ToolResponse;
import br.com.idolink.api.filter.ToolFilter;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.enums.ToolCodName;

public interface ToolService {
	
	ToolResponse create(ToolRequest request);
	
	ToolResponse update(Long id, ToolRequest request);
	
	ToolResponse findById(Long id);
	
	List<ToolResponse> list(ToolFilter filter);
	
	void delete(Long id);
	
	ToolActivatedResponse enableDisableTool(ToolCodName codName, Long id,ToolActivatedRequest activatedRequest);

	/**
	 * Informa se a versao do app suporta a ferramenta, de acordo com as versoes de cada um
	 * @param appVersion
	 * @param toolsVersion
	 * @return
	 */
	boolean isSuportedTools(String currentVersion, String toolsVersion);

	
	Tool findByCodName(ToolCodName codName);

}
