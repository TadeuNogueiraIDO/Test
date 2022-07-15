package br.com.idolink.api.repository;

import java.time.LocalDate;
import java.util.List;

import br.com.idolink.api.filter.ToolFilter;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.UserPlanPackage;
import br.com.idolink.api.model.enums.ToolCodName;

public interface ToolFilterRepository {
	
	List<Tool> findAll(ToolFilter filter);
	
	Tool findByCodName(ToolCodName codName);

	List<Tool> findAll(ToolFilter filter, Boolean isListable);
	
	List<UserPlanPackage> findToolInvoice(LocalDate date, Long userId, List<Long> idosId);
	
	
}
