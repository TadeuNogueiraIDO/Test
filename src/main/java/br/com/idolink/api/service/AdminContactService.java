package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.AdminContactRequest;
import br.com.idolink.api.dto.response.AdminContactResponse;

public interface AdminContactService {
	
	List<AdminContactResponse> list();
	
	AdminContactResponse create(AdminContactRequest request);
	
	AdminContactResponse update(Long id, AdminContactRequest request);
	
	void delete(Long id);

}
