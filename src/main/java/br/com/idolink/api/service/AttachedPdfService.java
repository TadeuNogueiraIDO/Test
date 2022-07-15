package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.AttachedPdfLeadsRequest;
import br.com.idolink.api.dto.request.AttachedPdfRequest;
import br.com.idolink.api.dto.response.AttachedPdfLeadsResponse;
import br.com.idolink.api.dto.response.AttachedPdfResponse;

public interface AttachedPdfService {

	List<AttachedPdfResponse> findByIdo(Long id);
	
	List<AttachedPdfResponse> publicFindByIdo(Long idoId);
	
	AttachedPdfResponse save(Long idoId, AttachedPdfRequest request);
	
	AttachedPdfResponse update(Long id, AttachedPdfRequest request);
	
	void delete(Long id);

	AttachedPdfLeadsResponse saveLeads(Long idPdf, AttachedPdfLeadsRequest request);

	AttachedPdfResponse findById(Long id);

	AttachedPdfLeadsResponse findLeadsById(Long id);

	List<AttachedPdfLeadsResponse> findLeadsByAttachedPdf(Long attachedPdfId);

	
}
