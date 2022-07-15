package br.com.idolink.api.service;

import br.com.idolink.api.dto.response.PortifolioMovimentationGeneralResponse;
import br.com.idolink.api.filter.PortifolioMovimentationFilter;

public interface PortifolioMovimentsService {
	
	PortifolioMovimentationGeneralResponse findPortifolioMovements(PortifolioMovimentationFilter filter, Long userId);
}
