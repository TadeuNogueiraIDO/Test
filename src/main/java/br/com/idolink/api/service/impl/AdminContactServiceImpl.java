package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.AdminContactRequest;
import br.com.idolink.api.dto.response.AdminContactResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.AdminContactMapper;
import br.com.idolink.api.model.AdminContact;
import br.com.idolink.api.repository.AdminContactRepository;
import br.com.idolink.api.service.AdminContactService;

@Service
public class AdminContactServiceImpl implements AdminContactService {

	@Autowired
	private AdminContactRepository repository;

	@Autowired
	private AdminContactMapper mapper;

	@Override
	public List<AdminContactResponse> list() {
		List<AdminContact> model = repository.findByOrderById();
		return mapper.response(model);
	}

	@Override
	@Transactional
	public AdminContactResponse create(AdminContactRequest request) {

		AdminContact model = mapper.requestToModel(request);

		try {
			model = repository.save(model);

		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Contato de Administrador",
					"api.error.admin.contact.conflict");
		}

		return mapper.response(model);
	}

	@Override
	@Transactional
	public AdminContactResponse update(Long id, AdminContactRequest request) {

		AdminContact model = repository.findById(id).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,
				"Contato de Administrador", "api.error.admin.contact.not.found"));

		BeanUtils.copyProperties(request, model, "id", "dateModel");
		return mapper.response(repository.save(model));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Optional<AdminContact> model = repository.findById(id);
		validate(model, "Contato de Administrador", "api.error.admin.contact.not.found");
		repository.deleteById(id);

	}

	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
}
