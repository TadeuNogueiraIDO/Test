package br.com.idolink.api.service;



import br.com.idolink.api.dto.request.AccessReportRequest;
import br.com.idolink.api.dto.response.AccessReportResponse;
import br.com.idolink.api.dto.response.VisitorsClicksResponse;
import br.com.idolink.api.model.enums.ReportPeriod;

public interface AccessReportService {
	
	AccessReportResponse create(AccessReportRequest request);
					
	void delete(Long id);

	/**
	 * retorna a quantidade de acessos por um periodo de tempo:mes ou ano
	 */
	VisitorsClicksResponse getVisitorsAndCliscks(Long userId, String idoIds, ReportPeriod period);
	
	VisitorsClicksResponse canSendReportEmailWeek(Long id, Boolean p);

	String validateIsNull(String ido);

	

	

	

	
}
