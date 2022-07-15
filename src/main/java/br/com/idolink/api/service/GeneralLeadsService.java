package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.response.GeneralLeadsResponse;
import br.com.idolink.api.model.enums.StatusLeads;
import br.com.idolink.api.model.enums.TypeLeads;


public interface GeneralLeadsService {

//	List<GeneralLeadsResponse> findByUser(Long IdUser);
//
	byte[] generateLeads(Long IdUser, Boolean send);
	
	List<GeneralLeadsResponse> listByUser(Long idUser);
	
	List<GeneralLeadsResponse> update(Long idUser, String idTools, TypeLeads type, StatusLeads status);

	void deleteLeads(Long idUser, TypeLeads type, Long idTool);


}
