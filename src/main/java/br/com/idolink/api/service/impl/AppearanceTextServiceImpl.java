package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.AppearanceTextRequest;
import br.com.idolink.api.dto.response.AppearanceTextResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.AppearanceTextMapper;
import br.com.idolink.api.model.AppearanceText;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.TextFont;
import br.com.idolink.api.repository.AppearanceTextRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.TextFontRepository;
import br.com.idolink.api.service.AppearanceTextService;

@Service
public class AppearanceTextServiceImpl implements AppearanceTextService {

	@Autowired
	private AppearanceTextRepository repository;
		
	@Autowired
	private TextFontRepository fontRepository;
	
	@Autowired
	private IdoRepository idoRepository;
	
	@Autowired
	private AppearanceTextMapper mapper;
	
	@Override
	public Optional<AppearanceText> findByIdo(Ido ido) {
		Optional<AppearanceText> optional = repository.findByIdo(ido.getId());
		return optional;
	}
	
	@Override
	public AppearanceTextResponse publicFindByIdo(Long id) {
		return this.publicFindByIdo(id, true);
	}
	
	
	@Override
	public AppearanceTextResponse publicFindByIdo(Long id, boolean validation) {
		
		Optional<Ido> ido =  idoRepository.findById(id);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
				
		Optional<AppearanceText> model = repository.findByIdo(id);
		
		if(validation) {
			validate(model, "AppearanceText", "api.error.appearance.text.not.found");
		}
		
		return model.isPresent()? mapper.response(model.get()) : null;
	}
	
	@Override
	public AppearanceTextResponse findByIdo(Long id) {
		return this.findByIdo(id, true);
	}
	
	
	@Override
	public AppearanceTextResponse findByIdo(Long id, boolean validation) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(id);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
				
		Optional<AppearanceText> model = repository.findByIdo(id);
		
		if(validation) {
			validate(model, "AppearanceText", "api.error.appearance.text.not.found");
		}
		
		return model.isPresent()? mapper.response(model.get()) : null;
	}
	
	@Override
	public List<AppearanceTextResponse> list() {

		List<AppearanceText> model = repository.findAll();

		return mapper.response(model);
	}
	
	@Override
	@Transactional
	public AppearanceTextResponse create(Long idIdo, AppearanceTextRequest request) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idIdo);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		validateButtonIdo(idIdo);
		
		AppearanceText model = mapper.save(request, ido.get());
		
		validateTextDependences(model, request);
				
		repository.save(model);
		return mapper.response(model);
	}

	@Override
	@Transactional
	public AppearanceTextResponse update(Long id, AppearanceTextRequest request) {

		AppearanceText model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "AppearanceText" ,"api.error.appearance.text.not.found"));

		AppearanceText appearenceText = mapper.requestToModel(request);
		
		BeanUtils.copyProperties(appearenceText, model, "id", "dateModel", "ido");
		
		validateTextDependences(model, request);
				
		model = repository.save(model);

		return mapper.modelToResponse(model);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {

		AppearanceText model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"Text", "api.error.appearance.text.not.found"));
		try {
					
			repository.delete(model);
			repository.flush();

		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Text", "api.error.appearance.text.conflict");
		}

	}
	
	@Override
	@Transactional
	public AppearanceText save(AppearanceText model) {
	    return repository.save(model);
	}
	
	
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
			
	public void validateButtonIdo(Long idIdo) {
		Optional<AppearanceText> model = repository.findByIdo(idIdo);
		
		if(model.isPresent()) {
			throw new BaseFullException(HttpStatus.CONFLICT,"AppearanceText", "api.error.appearance.text.ido.conflict");
		}
	}
	
	private void validateTextDependences(AppearanceText model, AppearanceTextRequest request) {
						
		Optional<TextFont> font = fontRepository.findById(request.getTextFont().getId());
		validate(font, "Text Font", "api.error.appearance.text.text.font.not.found");
		
		model.setTextFont(font.get());
				
		model.setFontColor(request.getFontColor());
		model.setBackgroundColor(request.getBackgroundColor());
	}
	
	
	
}
