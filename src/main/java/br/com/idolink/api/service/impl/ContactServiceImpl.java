package br.com.idolink.api.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.ContactRequest;
import br.com.idolink.api.dto.response.ContactResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ContactMapper;
import br.com.idolink.api.model.Contact;
import br.com.idolink.api.repository.ContactRepository;
import br.com.idolink.api.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository repository;

	@Autowired
	private ContactMapper mapper;

	@Override
	public Contact validateContact(Long id) {

		Contact model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Contact","api.error.contact.not.found"));

		return model;
	}

	@Override
	public Page<ContactResponse> findAll(Pageable pageable) {

		Page<Contact> model = repository.findAll(pageable);

		return model.map(m -> mapper.modelToResponse(m));
	}
	
	public ContactResponse updateStatus(Long idContact, Boolean status) {
		
		Contact model = repository.findById(idContact)
		.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Contact","api.error.contact.not.found"));
		
		
		model.setActivated(status);
		
		return mapper.modelToResponse(repository.save(model));
		
		
	}

	@Override
	@Transactional
	public ContactResponse save(ContactRequest request) {

		Contact model = mapper.requestToModel(request);
		model = repository.save(model);

		return mapper.modelToResponse(model);
	}

	@Override
	@Transactional
	public ContactResponse update(Long id, ContactRequest request) {

		Contact model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"Contact", "api.error.contact.not.found"));

		Contact contact = mapper.requestToModel(request);

		BeanUtils.copyProperties(contact, model, "id","dateModel");
		model = repository.save(model);

		return mapper.modelToResponse(model);
	}

	@Override
	@Transactional
	public void delete(Long id) {

		Contact model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Contact","api.error.contact.not.found"));
		try {

			repository.delete(model);
			repository.flush();

		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Contact","api.error.contact.conflict");
		}

	}


}
