package br.com.idolink.api.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.CustomDomainRequest;
import br.com.idolink.api.dto.response.CustomDomainResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.CustomDomainMapper;
import br.com.idolink.api.model.CustomDomain;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.CustomDomainRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.service.CustomDomainService;

@Service
public class CustomDomainServiceImpl extends GenericToolsServiceImpl implements CustomDomainService{

	@Autowired
	private CustomDomainRepository repository;
	
	@Autowired
	private IdoRepository idoRepository;
	
	@Autowired
	private CustomDomainMapper mapper;
	
	@Override
	public CustomDomainResponse findById(Long id) {
		repository.findById(id);
		return null;
	}

	@Override
	public CustomDomainResponse findByIdo(Long ido) {
		Optional<CustomDomain> model = repository.findByIdo(ido);
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Domain", "api.error.domain.not.found");
		}
		return mapper.modelToResponse(model.get());
	}

	@Override
	public CustomDomainResponse create(CustomDomainRequest request, Long idoId) {
		
		validateDomain(idoId);
		
		Optional<CustomDomain> domain = repository.findByIdo(idoId);
		if(domain.isPresent()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND,"Domain", "api.error.domain.not.found");
		}
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.domain.not.found");
		}
		
		CustomDomain model = new CustomDomain();
		model.setDomain(request.getDomain());
		model.setIdo(ido.get());
		model = repository.save(model);
		super.createTools(ToolCodName.CUSTOM_DOMAIN, idoId, model.getId());
				
		return mapper.modelToResponse(model);

	}

	@Override
	public CustomDomainResponse update(CustomDomainRequest request, Long ido) {
		
		Optional<CustomDomain> model = repository.findByIdo(ido);
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Domain", "api.error.domain.not.found");
		}
		
		BeanUtils.copyProperties(request, model.get(), "id", "dataModel", "ido");
		repository.save(model.get());
	
		
		return mapper.modelToResponse(model.get());
	}

	@Override
	public void delete(Long ido) {
		Optional<CustomDomain> model = repository.findByIdo(ido);
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Domain", "api.error.domain.not.found");		}
		repository.deleteById(model.get().getId());
		
	}

	private void validateDomain(Long ido) {
		
		Optional<CustomDomain> model = repository.findByIdo(ido);
		if(model.isPresent()) {
			
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Ido", "api.error.domain.already.exists.for.ido.conflict");
			
		}
		

	}

}
