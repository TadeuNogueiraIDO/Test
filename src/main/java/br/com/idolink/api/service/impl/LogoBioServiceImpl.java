package br.com.idolink.api.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.LogoBioRequest;
import br.com.idolink.api.dto.response.LogoBioResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.LogoBioMapper;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.LogoBio;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.LogoBioRepository;
import br.com.idolink.api.service.LogoBioService;

@Service
public class LogoBioServiceImpl   extends GenericToolsServiceImpl implements LogoBioService{

	@Autowired
	private LogoBioRepository repository;
	
	@Autowired
	private LogoBioMapper mapper;
	
	@Autowired
	private IdoRepository idoRepository;
	
	@Override
	public LogoBioResponse publicFindByIdo(Long idoId) {
		
		Optional<Ido> ido = idoRepository.findById(idoId);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		Optional<LogoBio> model = repository.findByIdo(idoId);
		
		if(model.isPresent()) {
			return mapper.response(model.get());
		}
		
		return null;
		
	}
	
	@Override
	public LogoBioResponse findByIdo(Long idoId) {
		
		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		Optional<LogoBio> model = repository.findByIdo(idoId);
		
		if(model.isPresent()) {
			return mapper.response(model.get());
		}
		
		return null;
		
	}
	
	@Override
	@Transactional
	public LogoBioResponse updateByIdo(Long idoId, LogoBioRequest request) {

		Optional<Ido> modelIdo =  idoRepository.findByIdUserFilter(idoId);
		validate(modelIdo, "Ido", "api.error.ido.not.found");
		
		Optional<LogoBio> modelOpt = repository.findByIdo(idoId);
		
		if(modelOpt.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "LogoBio", "api.error.logo.bio.not.found");
		}
				
		Ido ido = modelIdo.get();
		validateSetIdo(ido,request);
		
		LogoBio model = modelOpt.get();
		
		BeanUtils.copyProperties(request, model, "id", "dateModel", "ido");
				
		model.setIdo(ido);
		
		repository.save(model);
		return mapper.response(model);
		
	}
	
	@Override
	@Transactional
	public LogoBioResponse createByIdo(Long idoId, LogoBioRequest request) {
		
		Optional<Ido> modelIdo =  idoRepository.findByIdUserFilter(idoId);
		validate(modelIdo, "Ido", "api.error.ido.not.found");
		
		Ido ido = modelIdo.get();
		validateSetIdo(ido,request);
		
		LogoBio model = mapper.create(request, ido);
		model = repository.save(model);
		
		super.createTools(ToolCodName.LOGOBIO, idoId, model.getId());
		return mapper.response(model);
		
	}
	
	
		
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
	
	
	private void validateSetIdo(Ido ido, LogoBioRequest request) {
		
		if(request.getNameActivated().equals(true)) {
		ido.setName(request.getName());
		
		}
				
		if(request.getBioActivated().equals(true)){
		ido.setDescription(request.getBio());
		}		
		if(request.getLogo().equals(true)) {
		ido.setIcon(request.getFileId());		
		
		}	
	}
	
}
