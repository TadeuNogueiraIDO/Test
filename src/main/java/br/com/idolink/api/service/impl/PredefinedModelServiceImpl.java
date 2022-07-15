package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.PredefinedModelRequest;
import br.com.idolink.api.dto.response.AppearanceButtonResponse;
import br.com.idolink.api.dto.response.AppearanceCardsResponse;
import br.com.idolink.api.dto.response.AppearanceTextResponse;
import br.com.idolink.api.dto.response.PredefinedModelResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.AppearanceButtonMapper;
import br.com.idolink.api.mapper.AppearanceCardsMapper;
import br.com.idolink.api.mapper.AppearanceTextMapper;
import br.com.idolink.api.mapper.PredefinedModelMapper;
import br.com.idolink.api.model.AppearanceButton;
import br.com.idolink.api.model.AppearanceCards;
import br.com.idolink.api.model.AppearanceText;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.PredefinedModel;
import br.com.idolink.api.model.enums.PredefinedModelsTemplate;
import br.com.idolink.api.model.enums.WallpaperType;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.PredefinedModelRepository;
import br.com.idolink.api.repository.WallpaperRepository;
import br.com.idolink.api.service.AppearanceButtonService;
import br.com.idolink.api.service.AppearanceCardsService;
import br.com.idolink.api.service.AppearanceTextService;
import br.com.idolink.api.service.PredefinedModelService;


@Service
public class PredefinedModelServiceImpl implements PredefinedModelService {

	@Autowired
	private PredefinedModelRepository repository;

	@Autowired
	private PredefinedModelMapper mapper;
	
	@Autowired
	private AppearanceButtonService appearanceButtonService;
	
	@Autowired
	private AppearanceCardsService appearanceCardsService;
	
	@Autowired
	private AppearanceTextService appearanceTextService;
		
	@Autowired
	private IdoRepository idoRepository;
	
	@Autowired
	private AppearanceButtonMapper appearanceButtonMapper;
	
	@Autowired
	private AppearanceCardsMapper appearanceCardsMapper;
	
	@Autowired
	private AppearanceTextMapper appearanceTextMapper;
	
	@Autowired
	private WallpaperRepository wallpaperRepository;
		
	@Override
	public List<PredefinedModelResponse> list() {

		List<PredefinedModel> model = repository.findAllUiidNotNull();
		return mapper.response(model);
	}

	@Override
	public PredefinedModelResponse findById(Long id) {
		Optional<PredefinedModel> model = repository.findById(id);
		validate(model, "PredefinedModel", "api.error.predefined.model.not.found");

		return mapper.response(model.get());
	}
	
	@Override
	@Transactional
	public PredefinedModelResponse create(PredefinedModelRequest request) {
		PredefinedModel model = mapper.model(request);
		return mapper.response(repository.save(model));
	}

	@Override
	@Transactional
	public PredefinedModelResponse update(Long id, PredefinedModelRequest request) {

		@SuppressWarnings("serial")
		PredefinedModel model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "PredefinedModel", "api.error.predefined.model.not.found") {
				});

		BeanUtils.copyProperties(request, model, "id","dateModel");
		return mapper.response(repository.save(model));

	}

	@Transactional
	@Override
	public void delete(Long id) {

		Optional<PredefinedModel> model = repository.findById(id);
		validate(model,"PredefinedModel", "api.error.predefined.model.not.found");

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST,"PredefinedModel", "api.error.predefined.model.conflict");
		}

	}
	
	@Override
	public AppearanceButtonResponse setTemplateAppearanceButton(Long idoId) {
		
		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);
		
		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido" , "api.error.ido.not.found");
		}
		
		AppearanceButton button = configAppearanceButtonTemplate(ido.get().getTemplate().getId(),ido.get(), true); 
		return appearanceButtonMapper.modelToResponse(button);
	}
	
	@Override
	public AppearanceButtonResponse getTemplateAppearanceButton(Long idoId) {
		
		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);
		
		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido" , "api.error.ido.not.found");
		}
		
		AppearanceButton button = configAppearanceButtonTemplate(ido.get().getTemplate().getId(),ido.get(), false); 
		return appearanceButtonMapper.modelToResponse(button);
	}
	
	
	
	
	@Override
	public AppearanceCardsResponse setTemplateAppearanceCard(Long idoId) {
		
		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);
		
		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido" , "api.error.ido.not.found");
		}
		
		AppearanceCards card = configAppearanceCardTemplate(ido.get().getTemplate().getId(),ido.get(), true); 
		return appearanceCardsMapper.modelToResponse(card);
	}
	
	@Override
	public AppearanceCardsResponse getTemplateAppearanceCard(Long idoId) {
		
		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);
		
		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido" , "api.error.ido.not.found");
		}
		
		AppearanceCards card = configAppearanceCardTemplate(ido.get().getTemplate().getId(),ido.get(), false); 
		return appearanceCardsMapper.modelToResponse(card);
	}
	
	@Override
	public AppearanceTextResponse setTemplateAppearanceText(Long idoId) {
		
		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);
		
		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido" , "api.error.ido.not.found");
		}
		
		AppearanceText card = configAppearanceTextTemplate(ido.get().getTemplate().getId(),ido.get(), true); 
		return appearanceTextMapper.modelToResponse(card);
	}
	
	@Override
	public AppearanceTextResponse getTemplateAppearanceText(Long idoId) {
		
		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);
		
		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido" , "api.error.ido.not.found");
		}
		
		AppearanceText card = configAppearanceTextTemplate(ido.get().getTemplate().getId(),ido.get(), false); 
		return appearanceTextMapper.modelToResponse(card);
	}
	
	@Override
	@Transactional
	public void setWallpaperTemplate(PredefinedModelsTemplate predefModel, Ido ido, boolean save, boolean isNew) {
		
		
		Optional<PredefinedModel> model = repository.findByName(predefModel);
		validate(model, "PredefinedModel", "api.error.predefined.model.not.found");
		
		ido.setWallpaperType(WallpaperType.findByType(model.get().getPredefModelParam().getModelParam().getName()));
		
		if(ido.getWallpaperType().equals(WallpaperType.GALLERY)) {
			Long wallpaperId = wallpaperRepository.findIdByFileUiid(model.get().getPredefModelParam().getValue());
			ido.setWallpaper( String.valueOf(wallpaperId));
		}else {
			ido.setWallpaper(model.get().getPredefModelParam().getValue());
		}
		
		configAppearanceButton(ido, model.get(), save);
		configAppearanceText(ido, model.get(), save);
		configAppearanceCard(ido, model.get(), save);
		
	}
	
	private AppearanceText configAppearanceTextTemplate(Integer predefModelId, Ido ido, boolean save) {
		
		PredefinedModelsTemplate predefModel = PredefinedModelsTemplate.getById(predefModelId);
		Optional<PredefinedModel> model = repository.findByName(predefModel);
		validate(model, "PredefinedModel", "api.error.predefined.model.not.found");
		return configAppearanceText(ido, model.get(), save);
	}
	
	private AppearanceCards configAppearanceCardTemplate(Integer predefModelId, Ido ido, boolean save) {
		
		PredefinedModelsTemplate predefModel = PredefinedModelsTemplate.getById(predefModelId);
		Optional<PredefinedModel> model = repository.findByName(predefModel);
		validate(model, "PredefinedModel", "api.error.predefined.model.not.found");
		return configAppearanceCard(ido, model.get(), save);
	}
	
	@Transactional
	private AppearanceButton configAppearanceButton(Ido ido, PredefinedModel model, Boolean save) {
		
		if(save) {
			Optional<AppearanceButton> buttonOld = appearanceButtonService.findByIdo(ido);
			
			if(buttonOld.isPresent()) {
				appearanceButtonService.delete(buttonOld.get().getId());
			}
		}
		
		AppearanceButton appearanceTemplate = model.getAppearanceButton();
				
		AppearanceButton button = AppearanceButton.builder()
				.borderColor(appearanceTemplate.getBorderColor())
				.buttonColor(appearanceTemplate.getButtonColor())
				.buttonShape(appearanceTemplate.getButtonShape())
				.fontColor(appearanceTemplate.getFontColor())
				.textFont(appearanceTemplate.getTextFont()).build();
		if(save) {
			button.setIdo(ido);			
			appearanceButtonService.save(button);
		}
		
		return button;
		
	}
	
	@Transactional
	private AppearanceText configAppearanceText(Ido ido, PredefinedModel model, Boolean save) {
		
		if(save) {
			Optional<AppearanceText> textOld = appearanceTextService.findByIdo(ido);
			
			if(textOld.isPresent()) {
				appearanceTextService.delete(textOld.get().getId());
			}
		}
		
		AppearanceText appearanceTemplate = model.getAppearanceText();
				
		AppearanceText text = AppearanceText.builder()
				.backgroundColor(appearanceTemplate.getBackgroundColor())
				.fontColor(appearanceTemplate.getFontColor())
				.textFont(appearanceTemplate.getTextFont())
				.build();
			
		if(save) {
			text.setIdo(ido);			
			appearanceTextService.save(text);
		}
		
		return text;
	}
	
	@Transactional
	private AppearanceCards configAppearanceCard(Ido ido, PredefinedModel model, Boolean save) {
		
		if(save) {
			Optional<AppearanceCards> cardOld = appearanceCardsService.findByIdo(ido);
			
			if(cardOld.isPresent()) {
				appearanceCardsService.delete(cardOld.get().getId());
			}
		}
		
		AppearanceCards appearanceTemplate = model.getAppearanceCards();

		AppearanceCards card = AppearanceCards.builder()
				.borderColor(appearanceTemplate.getBorderColor())
				.cardColor(appearanceTemplate.getCardColor())
				.buttonShape(appearanceTemplate.getButtonShape())
				.fontColor(appearanceTemplate.getFontColor())
				.textFont(appearanceTemplate.getTextFont()).build();
		
		if(save) {
			card.setIdo(ido);			
			appearanceCardsService.save(card);
		}
		
		return card;
			
	}
	
	private void validate(Optional<PredefinedModel> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
	
	private AppearanceButton configAppearanceButtonTemplate(Integer predefModelId, Ido ido, boolean save) {
		
		PredefinedModelsTemplate predefModel = PredefinedModelsTemplate.getById(predefModelId);
		Optional<PredefinedModel> model = repository.findByName(predefModel);
		validate(model, "PredefinedModel", "api.error.predefined.model.not.found");
		return configAppearanceButton(ido, model.get(), save);
	}
	
}
