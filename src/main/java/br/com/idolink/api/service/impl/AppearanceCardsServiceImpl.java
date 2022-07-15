package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.idolink.api.dto.request.AppearanceCardsRequest;
import br.com.idolink.api.dto.response.AppearanceCardsResponse;
import br.com.idolink.api.dto.response.ButtonShapeResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.AppearanceCardsMapper;
import br.com.idolink.api.mapper.ButtonShapeMapper;
import br.com.idolink.api.model.AppearanceCards;
import br.com.idolink.api.model.ButtonShape;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.TextFont;
import br.com.idolink.api.repository.AppearanceCardsRepository;
import br.com.idolink.api.repository.ButtonShapeRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.TextFontRepository;
import br.com.idolink.api.service.AppearanceCardsService;

@Service
public class AppearanceCardsServiceImpl implements AppearanceCardsService {

	@Autowired
	private AppearanceCardsRepository repository;
	
	@Autowired
	private ButtonShapeRepository buttonRepository;
		
	@Autowired
	private TextFontRepository fontRepository;
	
	@Autowired
	private IdoRepository idoRepository;
	
	@Autowired
	private AppearanceCardsMapper mapper;
	
	@Autowired
	private ButtonShapeMapper shapeMapper;
	
	@Override
	public AppearanceCardsResponse publicFindByIdo(Long id) {
		return this.publicFindByIdo(id, true);
	}
		
	@Override
	public AppearanceCardsResponse publicFindByIdo(Long id, boolean validation) {
		
		Optional<Ido> ido =  idoRepository.findById(id);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		
		Optional<AppearanceCards> model = repository.findByIdo(id);
		
		if(validation) {
			validate(model, "AppearanceCards", "api.error.appearance.card.not.found");
		}
		
		return model.isPresent()? mapper.response(model.get()) : null;
				
	}
	
	@Override
	public AppearanceCardsResponse findByIdo(Long id) {
		return this.findByIdo(id, true);
	}
		
	@Override
	public AppearanceCardsResponse findByIdo(Long id, boolean validation) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(id);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		
		Optional<AppearanceCards> model = repository.findByIdo(id);
		
		if(validation) {
			validate(model, "AppearanceCards", "api.error.appearance.card.not.found");
		}
		
		return model.isPresent()? mapper.response(model.get()) : null;
				
	}
	

	@Override
	public Optional<AppearanceCards> findByIdo(Ido ido) {
		Optional<AppearanceCards> optional = repository.findByIdo(ido.getId());
		return optional;
	}
	
	@Override
	@GetMapping
	public ResponseEntity<List<ButtonShapeResponse>> findAll() {
		List<ButtonShape> response = buttonRepository.findAll();
		return ResponseEntity.ok(shapeMapper.response(response));
	}
	
	@Override
	public List<AppearanceCardsResponse> list() {

		List<AppearanceCards> model = repository.findAll();

		return mapper.response(model);
	}
	
	@Override
	public AppearanceCardsResponse findById(Long id) {
		Optional<AppearanceCards> model = repository.findById(id);
		validate(model, "AppearanceCards", "api.error.appearance.card.not.found");

		return mapper.response(model.get());
	}
	
	@Override
	@Transactional
	public AppearanceCardsResponse create(Long idIdo, AppearanceCardsRequest request) {
		validateButtonIdo(idIdo);
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idIdo);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		AppearanceCards model = mapper.save(request, ido.get());
		
		validateCardDependences(model, request);
				
		repository.save(model);
		return mapper.response(model);
	}
	
	
	@Override
	@Transactional
	public AppearanceCards save(AppearanceCards model) {
	    return repository.save(model);
	}

	@Override
	@Transactional
	public AppearanceCardsResponse update(Long id, AppearanceCardsRequest request) {

		AppearanceCards model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Card",  "api.error.appearance.card.not.found"));

		AppearanceCards appearenceCard = mapper.requestToModel(request);
		
		BeanUtils.copyProperties(appearenceCard, model, "id", "dateModel", "ido");
		
		validateCardDependences(model, request);
				
		model = repository.save(model);

		return mapper.modelToResponse(model);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {

		AppearanceCards model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"Card", "api.error.appearance.card.not.found"));
		try {
					
			repository.delete(model);
			repository.flush();

		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Card", "api.error.appearance.card.conflict");
		}

	} 
	
	public void validateButtonIdo(Long idIdo) {
		Optional<AppearanceCards> model = repository.findByIdo(idIdo);
		
		if(model.isPresent()) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Card", "api.error.appearance.card.ido.conflict");
		}
	}
	
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
			
	private void validateCardDependences(AppearanceCards model, AppearanceCardsRequest request) {
		Optional<ButtonShape> shape = buttonRepository.findById(request.getButtonShape().getId());
		validate(shape, "CardShape", "api.error.appearance.card.shape.not.found");
		
		model.setButtonShape(shape.get());
				
		Optional<TextFont> font = fontRepository.findById(request.getTextFont().getId());
		validate(font, "Text Font", "api.error.appearance.card.text.font.not.found");
		
		model.setTextFont(font.get());
		
		
		model.setCardColor(request.getCardColor());
		model.setBorderColor(request.getBorderColor());
		model.setFontColor(request.getFontColor());
	}
	
	
	
}
