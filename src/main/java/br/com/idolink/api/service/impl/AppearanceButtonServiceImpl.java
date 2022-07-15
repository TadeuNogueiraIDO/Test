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

import br.com.idolink.api.dto.request.AppearanceButtonRequest;
import br.com.idolink.api.dto.response.AppearanceButtonResponse;
import br.com.idolink.api.dto.response.ButtonShapeResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.AppearanceButtonMapper;
import br.com.idolink.api.mapper.ButtonShapeMapper;
import br.com.idolink.api.model.AppearanceButton;
import br.com.idolink.api.model.ButtonShape;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.TextFont;
import br.com.idolink.api.repository.AppearanceButtonRepository;
import br.com.idolink.api.repository.ButtonShapeRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.TextFontRepository;
import br.com.idolink.api.service.AppearanceButtonService;

@Service
public class AppearanceButtonServiceImpl implements AppearanceButtonService {

	@Autowired
	private AppearanceButtonRepository repository;

	@Autowired
	private ButtonShapeRepository buttonRepository;

	@Autowired
	private TextFontRepository fontRepository;

	@Autowired
	private IdoRepository idoRepository;

	@Autowired
	private AppearanceButtonMapper mapper;

	@Autowired
	private ButtonShapeMapper shapeMapper;

	@Override
	public AppearanceButtonResponse publicFindByIdo(Long id) {
		return this.publicFindByIdo(id, true);
	}

	@Override
	public AppearanceButtonResponse publicFindByIdo(Long id, boolean validation) {

		Optional<Ido> ido = idoRepository.findById(id);
		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}

		Optional<AppearanceButton> model = repository.findByIdo(id);

		if (validation) {
			validateButton(model, "AppearanceButton", "api.error.appearance.button.not.found ");
		}

		return model.isPresent() ? mapper.response(model.get()) : null;
	}

	@Override
	public AppearanceButtonResponse findByIdo(Long id) {
		return this.findByIdo(id, true);
	}

	@Override
	public AppearanceButtonResponse findByIdo(Long id, boolean validation) {

		Optional<Ido> ido = idoRepository.findByIdUserFilter(id);
		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}

		Optional<AppearanceButton> model = repository.findByIdo(id);

		if (validation) {
			validateButton(model, "AppearanceButton", "api.error.appearance.button.not.found ");
		}

		return model.isPresent() ? mapper.response(model.get()) : null;
	}

	@Override
	public Optional<AppearanceButton> findByIdo(Ido ido) {
		Optional<AppearanceButton> optional = repository.findByIdo(ido.getId());
		return optional;
	}

	@Override
	@GetMapping
	public ResponseEntity<List<ButtonShapeResponse>> findAll() {
		List<ButtonShape> model = buttonRepository.findAll();
		return ResponseEntity.ok(shapeMapper.response(model));
	}

	@Override
	public List<AppearanceButtonResponse> list() {

		List<AppearanceButton> model = repository.findAll();

		return mapper.response(model);
	}

	@Override
	public AppearanceButtonResponse findById(Long id) {
		Optional<AppearanceButton> model = repository.findById(id);
		validate(model, "AppearanceButton", "api.error.appearance.button.not.found");

		return mapper.response(model.get());
	}

	@Override
	@Transactional
	public AppearanceButtonResponse create(Long idIdo, AppearanceButtonRequest request) {
		validateButtonIdo(idIdo);

		Optional<Ido> ido = idoRepository.findByIdUserFilter(idIdo);
		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}

		AppearanceButton model = mapper.save(request, ido.get());

		validateButtonDependences(model, request);

		repository.save(model);
		return mapper.response(model);
	}

	@Override
	@Transactional
	public AppearanceButton save(AppearanceButton model) {
		model = repository.save(model);
		return model;
	}

	@Override
	@Transactional
	public AppearanceButtonResponse update(Long id, AppearanceButtonRequest request) {

		AppearanceButton model = repository.findById(id).orElseThrow(
				() -> new BaseFullException(HttpStatus.NOT_FOUND, "button", "api.error.appearance.button.not.found"));

		AppearanceButton appearenceButton = mapper.requestToModel(request);

		BeanUtils.copyProperties(appearenceButton, model, "id", "dateModel", "ido");

		validateButtonDependences(model, request);

		model = repository.save(model);

		return mapper.modelToResponse(model);
	}

	@Override
	@Transactional
	public void delete(Long id) {

		AppearanceButton model = repository.findById(id).orElseThrow(
				() -> new BaseFullException(HttpStatus.NOT_FOUND, "button", "api.error.appearance.button.not.found"));
		try {

			repository.delete(model);
			repository.flush();

		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "button", "api.error.appearance.button.conflict");
		}

	}

	public void validateButtonIdo(Long idIdo) {
		Optional<AppearanceButton> model = repository.findByIdo(idIdo);

		if (model.isPresent()) {
			throw new BaseFullException(HttpStatus.CONFLICT, "button", "api.error.appearance.button.ido.conflict");
		}
	}

	
	/*
	 * @Transactional
	 * 
	 * @Override public AppearanceButtonResponse setTemplateAppearanceButton(Long
	 * idoId) {
	 * 
	 * Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId); validate(ido,
	 * "Ido", "api.error.ido.not.found");
	 * 
	 * Optional<AppearanceButton> buttonOld = this.findByIdo(ido.get());
	 * 
	 * if (buttonOld.isPresent()) { this.delete(buttonOld.get().getId()); }
	 * 
	 * AppearanceButton button = findTemplateAppearanceButton(ido.get(),
	 * ido.get().getTemplate().getId()); button.setIdo(ido.get());
	 * this.save(button); return mapper.modelToResponse(button); }
	 */
	 

	/*
	 * @Override public AppearanceButtonResponse getTemplateAppearanceButton(Long
	 * idoId) {
	 * 
	 * Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId); validate(ido,
	 * "Ido", "api.error.ido.not.found");
	 * 
	 * 
	 * AppearanceButton button = findTemplateAppearanceButton(ido.get(),
	 * ido.get().getTemplate().getId()); return mapper.modelToResponse(button); }
	 */

	/*
	 * @Transactional private AppearanceButton findTemplateAppearanceButton(Ido ido,
	 * Integer preDefinedModel) {
	 * 
	 * String black = "0XFF000000"; String white = "0XFFFFFFFF"; String citric =
	 * "0XFF7FFF00"; TextFont font = fontRepository.findByName("Poppins");
	 * ButtonShape shapeRound = buttonRepository.findByName("Round");
	 * 
	 * AppearanceButton button = AppearanceButton.builder() .borderColor(black)
	 * .buttonColor(white) .buttonShape(shapeRound) .fontColor(black)
	 * .textFont(font).build();
	 * 
	 * if(preDefinedModel == PredefinedModelsTemplate.CITRIC.getId() ||
	 * preDefinedModel == PredefinedModelsTemplate.CITRIC_VITRINE.getId()) {
	 * button.setBorderColor(citric); }
	 * 
	 * return button;
	 * 
	 * 
	 * }
	 */

	private void validateButtonDependences(AppearanceButton model, AppearanceButtonRequest request) {

		Optional<ButtonShape> shape = buttonRepository.findById(request.getButtonShape().getId());
		validate(shape, "ButtonShape", "api.error.appearance.button.shape.not.found");
		model.setButtonShape(shape.get());

		Optional<TextFont> font = fontRepository.findById(request.getTextFont().getId());
		validate(font, "TextFont", "api.error.appearance.button.text.font.not.found");
		model.setTextFont(font.get());

	}

	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}

	private void validateButton(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}

}
