package br.com.idolink.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.idolink.api.dto.request.ContactRequest;
import br.com.idolink.api.dto.response.ContactResponse;
import br.com.idolink.api.model.Contact;

public interface ContactService {

	Page<ContactResponse> findAll(Pageable pageable);
	
	Contact validateContact(Long id);
	
	ContactResponse save(ContactRequest request);
	
	ContactResponse update(Long id, ContactRequest request);
	
	void delete(Long id);
	
	ContactResponse updateStatus(Long idContact, Boolean status);
}
