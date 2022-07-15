
package br.com.idolink.api.service.impl;

import static br.com.idolink.api.service.impl.UsefulServiceImpl.isValidEmailAddress;
import static java.util.Objects.nonNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.request.IdoContactDialCodeRequest;
import br.com.idolink.api.dto.request.IdoContactRequest;
import br.com.idolink.api.dto.request.IdoContactValueRequest;
import br.com.idolink.api.dto.request.IdoSettingsRequest;
import br.com.idolink.api.dto.request.ImageBannerContactRequest;
import br.com.idolink.api.dto.request.ImageBannerRequest;
import br.com.idolink.api.dto.request.ImageCarouselItemDetailRequest;
import br.com.idolink.api.dto.request.ImageCarouselItemRequest;
import br.com.idolink.api.dto.request.ImageCarouselRequest;
import br.com.idolink.api.dto.request.LinkRequest;
import br.com.idolink.api.dto.request.LogoBioRequest;
import br.com.idolink.api.dto.request.VideoBannerRequest;
import br.com.idolink.api.dto.request.WallpaperIdoRequest;
import br.com.idolink.api.dto.request.ido.IdoProfileRequest;
import br.com.idolink.api.dto.request.ido.IdoRequest;
import br.com.idolink.api.dto.response.IdoContactDialCodeResponse;
import br.com.idolink.api.dto.response.IdoContactResponse;
import br.com.idolink.api.dto.response.IdoContactValueResponse;
import br.com.idolink.api.dto.response.IdoPlanPackageResponse;
import br.com.idolink.api.dto.response.IdoResponse;
import br.com.idolink.api.dto.response.WallpaperIdoResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ContactMapper;
import br.com.idolink.api.mapper.IdoContactMapper;
import br.com.idolink.api.mapper.IdoMapper;
import br.com.idolink.api.model.Business;
import br.com.idolink.api.model.Category;
import br.com.idolink.api.model.Contact;
import br.com.idolink.api.model.Country;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoContact;
import br.com.idolink.api.model.IdoToolPosition;
import br.com.idolink.api.model.PredefinedModel;
import br.com.idolink.api.model.Shop;
import br.com.idolink.api.model.Timezone;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.enums.ContactType;
import br.com.idolink.api.model.enums.IdoStatus;
import br.com.idolink.api.model.enums.ImageAspect;
import br.com.idolink.api.model.enums.ImageBannerAction;
import br.com.idolink.api.model.enums.PredefinedModelsTemplate;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.BusinessRepository;
import br.com.idolink.api.repository.CategoryRepository;
import br.com.idolink.api.repository.ContactRepository;
import br.com.idolink.api.repository.CountryRepository;
import br.com.idolink.api.repository.IdoContactRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.IdoToolPositionRepository;
import br.com.idolink.api.repository.PredefinedModelRepository;
import br.com.idolink.api.repository.ShopRepository;
import br.com.idolink.api.repository.TimezoneRepository;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.service.CategoryService;
import br.com.idolink.api.service.IdoService;
import br.com.idolink.api.service.IdoToolPositionService;
import br.com.idolink.api.service.ImageBannerService;
import br.com.idolink.api.service.ImageCarouselService;
import br.com.idolink.api.service.LinkService;
import br.com.idolink.api.service.LogoBioService;
import br.com.idolink.api.service.MessagePropertieService;
import br.com.idolink.api.service.PredefinedModelService;
import br.com.idolink.api.service.UserPlanPackageService;
import br.com.idolink.api.service.VideoBannerService;
import br.com.idolink.api.service.WallpaperService;
import br.com.idolink.api.utils.IdoStringUtils;

@Service
public class IdoServiceImpl extends GenericToolsServiceImpl implements IdoService {

	@Autowired
	private IdoMapper mapper;

	@Autowired
	private IdoContactMapper idoContactMapper;
	
	@Autowired
	private StorageApi api;
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private IdoRepository repository;
	
	@Autowired
	private LogoBioService logoBioService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private IdoContactRepository idoContactRepository;

	@Autowired
	private BusinessRepository businessRepository;

	@Autowired
	private ContactMapper contactMapper;

	@Autowired
	private PredefinedModelRepository predefinedModelRepository;
	
	@Autowired
	private PredefinedModelService predefinedModelService;
			
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private WallpaperService wallpaperService;
	
	@Autowired
	private TimezoneRepository timezoneRepository;
		
	@Autowired
	private IdoToolPositionService idoToolPositionService;
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private UserPlanPackageService userPlanPackageService;
	
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private ImageBannerService imageBannerService;
	
	@Autowired
	private StorageApi storageApi;
	
	@Autowired
	private VideoBannerService videoBannerService;
	
	@Autowired
	private ImageCarouselService imageCarouselService;
	
	@Autowired
	private IdoToolPositionRepository toolPositionRepository;
	
	@Override
	public List<Ido> findModelByUser(Long id){
		
		Optional<User> user = userRepository.findById(id);
		validate(user, "User", "Usuário não encontrado !");
				
		Business business = businessRepository.findByUser(user.get());  
		
		if(business == null) {
			return new ArrayList<>();
		}
				
		List<Ido> listIdo = repository.findByBusiness(business.getId());
				
		return listIdo;
	}
		
	
	@Override
	public List<IdoResponse> findIdoByUser(Long userId){
		
		Optional<User> user = userRepository.findById(userId);
		validate(user, "User", "api.error.user.not.found");
				
		Business business = businessRepository.findByUser(user.get());  
		
		if(business == null) {
			return new ArrayList<>();
		}
				
		List<Ido> listIdo = repository.findByBusiness(business.getId());
					
		List<IdoResponse> lists = mapper.response(listIdo);
		
		for (IdoResponse idoResponse : lists) {
			idoResponse.setContacts(this.getContact(idoResponse.getId()));
		}
				
		return lists;
	}
	
	
	@Override
	@Transactional
	public IdoResponse UpdatePhotoIdo(Long icon, Long idoId) {
		
		Optional<Ido> model = repository.findByIdUserFilter(idoId);
		validate(model, "Ido", "api.error.ido.not.found");
		
		Ido ido = model.get();
		ido.setIcon(icon);		
		repository.save(ido);
		return  mapper.response(ido, true);
	}

	@Override
	public IdoResponse findById(Long id) {
		Optional<Ido> model = repository.findByIdUserFilter(id);
		validate(model, "Ido", "api.error.ido.not.found");
		
		IdoResponse response = mapper.response(model.get(), true);
	
		response.setContacts(this.getContact(id));
		response.getContacts().setActivated(idoContactRepository.findByIdoByActived(id));
		response = addToolsPositions(response);	
		return response;
	}
	
	@Override
	public IdoResponse publicFindById(Long id) {
		Optional<Ido> model = repository.findById(id);
		validate(model, "Ido", "api.error.ido.not.found");
		
		IdoResponse response = mapper.modelResponse(model.get(), true);
		response.setContacts(this.getPublicContact(id));
		
		return response;
	}

	@Override
	@Transactional
	public IdoResponse create(IdoRequest request) {
		Ido model = mapper.model(request);
		return mapper.response(repository.save(model), true);
	}

	@Override
	@Transactional
	public IdoResponse update(Long id, IdoProfileRequest request) {

		@SuppressWarnings("serial")
		Ido model = repository.findByIdUserFilter(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found") {
				});

		BeanUtils.copyProperties(request, model, "id","dateModel", "business");
		return mapper.response(repository.save(model), true);

	}

	@Transactional
	public void delete(Long id) {

		Optional<Ido> model = repository.findByIdUserFilter(id);
		validate(model, "Ido", "api.error.ido.not.found");

		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Ido", "api.error.ido.conflict");
		}

	}
	
	@Override
	public Ido validate(Long id) {
		Optional<Ido> model = repository.findByIdUserFilter(id);
		
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND,"Ido", "api.error.ido.not.found");
		}
		return model.get();
	}
	
	public void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}

	@Override
	@Transactional
	public IdoResponse saveTemplateIdo(PredefinedModelsTemplate template, Long idoId, Boolean isNew) {
		
		Optional<Ido> ido = repository.findByIdUserFilter(idoId);
		
		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido" , "api.error.ido.not.found");
		}
				
		//validando o modelo pre-definido
		Optional<PredefinedModel> predefineModel =  predefinedModelRepository.findByName(template);
		
		if (predefineModel.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "predefineModel" , "api.error.predefined.model.not.found");
		}
		
		//configura o papel de parede
		predefinedModelService.setWallpaperTemplate(template, ido.get(),true, isNew);
		ido.get().setTemplate(template);
		
		repository.save(ido.get());
		
		if(isNew) {
			saveToolsDefault(template, ido.get());
		}
		
		IdoResponse response = mapper.response(ido.get(), true);
		response.setContacts(this.getContact(ido.get().getId()));
		
		return response;
	}
	
	@Override
	@Transactional
	public IdoResponse createProfile(IdoProfileRequest request, Long userId) {

		List<Category> categories = new ArrayList<>();

		Optional<User> user = userRepository.findById(userId);
		
    	Country country = new Country();

		if(user.get().getCountry().getId() != null) {
			Optional<Country> c  = countryRepository.findById(user.get().getCountry().getId());
			
			if(!c.isEmpty()) {
				country = c.get();
			}
		}
	
		validateProfile(request, userId, country); 
		categories = categoryService.findListById(request.getCategories());
				
    	// insert categories in new profile
		Ido model = mapper.modelProfile(request);
		model.setCategories(categories);
		
		
		Business business = businessRepository.findByUser(user.get());
		
		if(business == null) {
			business = new Business();
			business.setUser(user.get());
			businessRepository.save(business);
		}
		
		model.setStatus(IdoStatus.DRAFT);
		model.setBusiness(business);	
		model.setCountry(country);
		
		List<Timezone> timezones = timezoneRepository.findByCodName("America/Bahia");		
		
		if(!timezones.isEmpty()) {
			model.setTimezone(timezones.get(0));
		}
		
		repository.save(model);
		
		LogoBioRequest logoRequest= LogoBioRequest.builder().bio(request.getDescription())
				.bioActivated(true)
				.name(request.getName())
				.nameActivated(true)
				.logo(true).build();
		
		logoBioService.createByIdo(model.getId(),logoRequest);
		
		//salva um template padrao par ao ido
		saveTemplateIdo(PredefinedModelsTemplate.NEWWHITE, model.getId(), false);
				
		//adiciona um relacionamento entre a ferramenta e o ido
	    //Tool tool = toolService.findByCodName(ToolCodName.CONTACT);
		//idoToolService.create(model.getId(), IdoToolRequest.builder().toolId(tool.getId()).status(IdoToolStatus.INSTALLED).build());
		
		return mapper.response(model, true);
	}
	
	@Override
	public void validateDomain(String name) {
		 validateDomain(name,null);
	}
		
	@Override
	@Transactional
	public IdoContactResponse saveContact(Long idIdo, IdoContactRequest request, boolean isUpdate) {
		Ido ido = repository.findByIdUserFilter(idIdo)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found"));

		IdoContactResponse response = buildContactAndSave(ido, request, isUpdate);
		IdoToolPosition idoToolPosition = idoToolPositionService.findByIdoToolAndGenericId(idIdo, ToolCodName.CONTACT, idIdo); 
		
		if(Objects.isNull(idoToolPosition)){
			super.createTools(ToolCodName.CONTACT, idIdo, idIdo);
		}
		return response;
	}

	@Override
	public IdoContactResponse getContact(Long id) {

		Ido ido = repository.findByIdUserFilter(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found"));

		List<IdoContact> contacts = idoContactRepository.findIdoContactByIdo(ido);
		contacts = nonNull(contacts) ? contacts : new ArrayList<>(); 
				
		IdoContactResponse response = buildContacts(contacts);

		return response;
	}
	
	@Override
	public IdoContactResponse getPublicContact(Long id) {

		Ido ido = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found"));

		List<IdoContact> contacts = idoContactRepository.findIdoContactByIdo(ido);
		contacts = nonNull(contacts) ? contacts : new ArrayList<>(); 
				
		IdoContactResponse response = buildContacts(contacts);

		return response;
	}

	@Override
	@Transactional
	public void deleteContact(Long id) {
		
		Ido ido = repository.findByIdUserFilter(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found"));
		alterDataIdo(ido);
		idoContactRepository.deleteIdoContactByIdo(ido);
		
		super.deleteTools(ToolCodName.CONTACT, id, id, null);
			
	}
	
	@Override
	@Transactional
	public WallpaperIdoResponse setWallpaperIdo(Long idoId, WallpaperIdoRequest request) {
		
		Optional<Ido> ido = repository.findByIdUserFilter(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		Ido model = ido.get();		
		model.setWallpaper(wallpaperService.validateAndSetWallpaper(request.getWallpaperType(), request.getWallpaper()));
		model.setWallpaperType(request.getWallpaperType());
		
		repository.save(model);		
		
		return WallpaperIdoResponse.builder()
				.idoId(idoId)
				.wallpaper(request.getWallpaper())
				.wallpaperType(request.getWallpaperType())
				.build();
			
	}		
	
	@Override
	@Transactional
	public IdoResponse changeSettings(Long idoId, IdoSettingsRequest request, Long usuarioId) {
		
		Optional<Ido> ido = repository.findByIdUserFilter(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		if(Objects.nonNull(request.getHideIdolink())) {
			
			List<IdoPlanPackageResponse> idoTools = userPlanPackageService.findAll(usuarioId, String.valueOf(idoId));
			
			if(idoTools.isEmpty() && request.getHideIdolink()) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "HideIdolink", "api.error.ido.hide.ido.link.conflict");
			}
		}
		
		Ido model = ido.get();	
		
     	/*
     	 * caso o ido editado seja o rascunho de um ido publicado, é preciso realizar as mudanças tb no ido publicado
     	 */
		if(Objects.nonNull(model.getIdoPublished()) && model.getIdoPublished() != 0){
			
			Optional<Ido> idoPublished = repository.findByIdUserFilter(model.getIdoPublished());
		
			Ido modelPublished = idoPublished.get();
		
			//verifica se o dominio pode ser alterado
			validateChangeDomain(modelPublished);
			
			changeIndividualSettings(modelPublished,request);
		
			/*
			 * Caso o usuario queira modificar o rascunho, e ele é um rascunho, o ido antigo é excluido
			 */
			if(Objects.nonNull(request.getStatus())) {
		
				if(request.getStatus().equals(IdoStatus.DRAFT)) {
					modelPublished.setStatus(IdoStatus.PUBLISHED);
					repository.save(modelPublished);
				}
				
			}
		
		}
		
		changeIndividualSettings(model,request);
		
		IdoResponse response = mapper.response(model, true);
		response.setContacts(this.getContact(ido.get().getId()));
		return response;
	}
	
	@Override
	public void validateChangeDomain(Long idoId) {
		Optional<Ido> ido = repository.findByIdUserFilter(idoId);
		validate(ido, "Ido", "api.error.ido.not.found");
		
		if(Objects.nonNull(ido.get().getIdoPublished())){
			validateChangeDomain(ido.get());
		}	
				
		
	}
	
	@Override
	public IdoResponse findByDomain(String domain) {
		
		Optional<Ido> model = repository.findByDomain(domain);
		validate(model, "Ido", "api.error.ido.not.found");
		
		IdoResponse response = mapper.modelResponse(model.get(), true);
		response.setContacts(this.getPublicContact(model.get().getId()));
	
		return response;
	}
	
	@Override
	public IdoResponse findByDomainPublic(String domain) {
		IdoResponse response = findByDomain(domain);
		response = addToolsPositions(response);
		return response;
	}
	
	@Override
	public IdoResponse addToolsPositions(IdoResponse response) {
		List<IdoToolPosition> toolPositions = toolPositionRepository.findByIdoIdOrderByPosition(response.getId());
		
		HashMap<ImmutablePair<ToolCodName, Long>, IdoToolPosition> toolPositionsMap = new HashMap<>();
		toolPositions.forEach(t ->{
			ImmutablePair<ToolCodName, Long> key = new ImmutablePair<>(t.getToolCodName(), t.getToolCodName().equals(ToolCodName.CONTACT) ? null : t.getGenericToolId());
			toolPositionsMap.put(key, t);
		});

		if(nonNull(response.getBusinessHour())) {
			if(Objects.nonNull(toolPositionsMap.get(new ImmutablePair<>(ToolCodName.BUSINESSHOUR, response.getBusinessHour().getId())))){
				Long position = toolPositionsMap.get(new ImmutablePair<>(ToolCodName.BUSINESSHOUR, response.getBusinessHour().getId())).getPosition();

				response.getBusinessHour().setPosition(position);
				response.getBusinessHour().setCodName(ToolCodName.BUSINESSHOUR.toString());
			}
		}
		if(nonNull(response.getConfigContactUs())) {
			if(Objects.nonNull(toolPositionsMap.get(new ImmutablePair<>(ToolCodName.CONTACTUS, response.getConfigContactUs().getId())))){
				Long position = toolPositionsMap.get(new ImmutablePair<>(ToolCodName.CONTACTUS, response.getConfigContactUs().getId())).getPosition();
				response.getConfigContactUs().setPosition(position);
				response.getConfigContactUs().setCodName(ToolCodName.CONTACTUS.toString());
			}
		}
		if(nonNull(response.getConfigFaq())) {
			if(Objects.nonNull(toolPositionsMap.get(new ImmutablePair<>(ToolCodName.FAQ, response.getConfigFaq().getId())))){
				Long position = toolPositionsMap.get(new ImmutablePair<>(ToolCodName.FAQ, response.getConfigFaq().getId())).getPosition();
				response.getConfigFaq().setPosition(position);
				response.getConfigFaq().setCodName(ToolCodName.FAQ.toString());
			}
		}
		if(nonNull(response.getConfigNewsletterForm())) {
			if(Objects.nonNull(toolPositionsMap.get(new ImmutablePair<>(ToolCodName.NEWSLETTER, response.getConfigNewsletterForm().getId())))){
				Long position = toolPositionsMap.get(new ImmutablePair<>(ToolCodName.NEWSLETTER, response.getConfigNewsletterForm().getId())).getPosition();
				response.getConfigNewsletterForm().setPosition(position);
				response.getConfigNewsletterForm().setCodName(ToolCodName.NEWSLETTER.toString());
			}
		}
		if(nonNull(response.getShop())) {
			if(Objects.nonNull(toolPositionsMap.get(new ImmutablePair<>(ToolCodName.SHOP, response.getShop().getId())))){
				Long position = toolPositionsMap.get(new ImmutablePair<>(ToolCodName.SHOP, response.getShop().getId())).getPosition();
				response.getShop().setPosition(position);
				response.getShop().setCodName(ToolCodName.SHOP.toString());
			}
		}
		if(nonNull(response.getLogoBio())) {
			if(Objects.nonNull(toolPositionsMap.get(new ImmutablePair<>(ToolCodName.LOGOBIO, response.getLogoBio().getId())))){
				Long position = toolPositionsMap.get(new ImmutablePair<>(ToolCodName.LOGOBIO, response.getLogoBio().getId())).getPosition();
				response.getLogoBio().setPosition(position);
				response.getLogoBio().setCodName(ToolCodName.LOGOBIO.toString());
			}
		}
		if(nonNull(response.getMenuFooter())) {
			if(Objects.nonNull(toolPositionsMap.get(new ImmutablePair<>(ToolCodName.MENURODAPE, response.getMenuFooter().getId())))){
				Long position = toolPositionsMap.get(new ImmutablePair<>(ToolCodName.MENURODAPE, response.getMenuFooter().getId())).getPosition();
				response.getMenuFooter().setPosition(position);
				response.getMenuFooter().setCodName(ToolCodName.MENURODAPE.toString());
			}
		}
		if(nonNull(response.getContacts())) {
			if(Objects.nonNull(toolPositionsMap.get(new ImmutablePair<>(ToolCodName.CONTACT, null)))){			
				Long position = toolPositionsMap.get(new ImmutablePair<>(ToolCodName.CONTACT, null)).getPosition();
				response.getContacts().setPosition(position);
				response.getContacts().setCodName(ToolCodName.CONTACT.toString());
			}
		}
		
		if(nonNull(response.getImageBanners())) {
			response.getImageBanners().forEach(b ->{
				if(Objects.nonNull(toolPositionsMap.get(new ImmutablePair<>(ToolCodName.IMAGEBANNER, b.getId())))){
					Long position = toolPositionsMap.get(new ImmutablePair<>(ToolCodName.IMAGEBANNER, b.getId())).getPosition();
					b.setPosition(position);
					b.setCodName(ToolCodName.IMAGEBANNER.toString());
				}
				
			});
		}
		if(nonNull(response.getVideoBanners())) {
			response.getVideoBanners().forEach(b ->{
				if(Objects.nonNull(toolPositionsMap.get(new ImmutablePair<>(ToolCodName.VIDEOBANNER, b.getId())))){
					Long position = toolPositionsMap.get(new ImmutablePair<>(ToolCodName.VIDEOBANNER, b.getId())).getPosition();
					b.setPosition(position);
					b.setCodName(ToolCodName.VIDEOBANNER.toString());
				}
				
			});
		}
		if(nonNull(response.getLinks())) {
			response.getLinks().forEach(b ->{
				if(Objects.nonNull(toolPositionsMap.get(new ImmutablePair<>(ToolCodName.LINK, b.getId())))){
					Long position = toolPositionsMap.get(new ImmutablePair<>(ToolCodName.LINK, b.getId())).getPosition();
					b.setPosition(position);
					b.setCodName(ToolCodName.LINK.toString());
				}
				
			});
		}
		if(nonNull(response.getAttachedPdfs())) {
			response.getAttachedPdfs().forEach(b ->{
				if(Objects.nonNull(toolPositionsMap.get(new ImmutablePair<>(ToolCodName.PDF, b.getId())))){
					Long position = toolPositionsMap.get(new ImmutablePair<>(ToolCodName.PDF, b.getId())).getPosition();
					b.setPosition(position);
					b.setCodName(ToolCodName.PDF.toString());
				}
				
			});
		}
		if(nonNull(response.getImagesCarousel())) {
			response.getImagesCarousel().forEach(b ->{
				if(Objects.nonNull(toolPositionsMap.get(new ImmutablePair<>(ToolCodName.IMAGECAROUSEL, b.getId())))){
					Long position = toolPositionsMap.get(new ImmutablePair<>(ToolCodName.IMAGECAROUSEL, b.getId())).getPosition();
					b.setPosition(position);
					b.setCodName(ToolCodName.IMAGECAROUSEL.toString());
				}
				
			});
		}
		if(nonNull(response.getEmbedHtmls())) {
			response.getEmbedHtmls().forEach(b ->{
				if(Objects.nonNull(toolPositionsMap.get(new ImmutablePair<>(ToolCodName.HTML, b.getId())))){
					Long position = toolPositionsMap.get(new ImmutablePair<>(ToolCodName.HTML, b.getId())).getPosition();
					b.setPosition(position);
					b.setCodName(ToolCodName.HTML.toString());
				}
				
			});
		}

		return response;
	}
	
	//METODOS PRIVADOS
	private void validateChangeDomain(Ido ido) {

		// para permitir a alteracao do dominio do ido eh necessario verificar se o ido
		// tem loja
		// caso tenha loja , so eh permitido alterar se a ultima alteracao tenha
		// ocorrido em mais de 15 dias
		if (ido.getStatus() == IdoStatus.PUBLISHED) {

			Optional<Shop> shop = shopRepository.findByIdo(ido);

			if (shop.isPresent()) {

				if (Objects.nonNull(ido.getUpdateLastDomain())) {

					Long numberDay = Duration.between(ido.getUpdateLastDomain(), LocalDateTime.now()).toDays();

					if (numberDay <= 15) {
						String [] params = {String.valueOf(numberDay)};
						throw new BaseFullException(HttpStatus.BAD_REQUEST, "Domain", "api.error.domain.bad.request", params);
					}

				}
			}
		}
	}
		
	@Transactional
	private void changeIndividualSettings(Ido ido,  IdoSettingsRequest request) {
		
		if(Objects.nonNull(request.getName())) {
			ido.setName(request.getName());
		}
				
		if(Objects.nonNull(request.getDomain())) {
					
			if(Objects.nonNull(ido.getIdoPublished())) {
				validateDomain(request.getDomain(), ido.getIdoPublished());	
			}else {
				validateDomain(request.getDomain(), ido.getId());	
			}
			
			ido.setUpdateLastDomain(LocalDateTime.now());	
			ido.setDomain(request.getDomain());
		}
		
		if(Objects.nonNull(request.getCategories())) {
			List<Category> categories = new ArrayList<>();
			request.getCategories().forEach(c -> {
				Optional<Category> model = categoryRepository.findById(c.getId());
				if(model.isPresent()) {
					categories.add(model.get());
				}
			});
			ido.setCategories(categories);
		}
		
		if(Objects.nonNull(request.getHideIdolink())) {
			ido.setHideIdolink(request.getHideIdolink());	
		}
		
				
		if(Objects.nonNull(request.getTimezone()) && Objects.nonNull(request.getTimezone().getId())) {
			Optional<Timezone> timezone = timezoneRepository.findById(request.getTimezone().getId());
			
			if(timezone.isPresent()) {
				ido.setTimezone(timezone.get());
			}
		}
				
		if(Objects.nonNull(request.getSensitiveContent())) {
			ido.setSensitiveContent(request.getSensitiveContent());
		}
						
		if(Objects.nonNull(request.getSharingThumb()) && Objects.nonNull(request.getSharingThumb().getFileId())) {
			mapper.convertRequestInSharingThumb( request.getSharingThumb(),ido);
		}
				
		if(Objects.nonNull(request.getStatus())) {
			if(request.getStatus().equals(IdoStatus.DRAFT) || request.getStatus().equals(IdoStatus.INACTIVE)) {
				ido.setStatus(request.getStatus());
			}
		}
					
	}

	private void validateEmail(IdoContactValueRequest c, Contact contact) {

		if ("EMAIL".contentEquals(contact.getName())) {
			if (!isValidEmailAddress(c.getValue())) {
				throw new BaseFullException(HttpStatus.BAD_REQUEST, "E-mail", "api.error.email.incorrect.format");
			}
		}

	}

	private IdoContactResponse buildContacts (List<IdoContact> contacts)
	{

		List<IdoContactValueResponse> adresses = new ArrayList<>();

		IdoContactResponse contact = new IdoContactResponse();
		Tool tool = toolRepository.findByCodName(ToolCodName.CONTACT);         
		contact.setNameTool(messagePropertieService.getMessageAttribute(tool.getName()));        
		contact.setIconTool(api.findByUuid(tool.getIcon()));
		contact.setActivated(false);
		
		contacts.forEach(c -> {
			
			if(c.getActivated()) {
				contact.setActivated(true);
			}
			
			IdoContactValueResponse v = idocontactSetVelues(c);
			if (c.getContact().getName().contentEquals(ContactType.EMAIL.getDescription()))
			{   
				contact.setMail(v);
			}

			else if (c.getContact().getName().contentEquals(ContactType.INSTAGRAM.getDescription()))
			{
				contact.setInstagram(v);
			}

			else if (c.getContact().getName().contentEquals(ContactType.FACEBOOK.getDescription()))
			{
				contact.setFacebook(v);
			}
			
			else if (c.getContact().getName().contentEquals(ContactType.LINKEDIN.getDescription()))
			{
				contact.setLinkedin(v);
			}
			
			else if (c.getContact().getName().contentEquals(ContactType.YOUTUBE.getDescription()))
			{
				contact.setYoutube(v);
			}
			
			else if (c.getContact().getName().contentEquals(ContactType.TIKTOK.getDescription()))
			{
				contact.setTiktok(v);
				
			}
			
			else if (c.getContact().getName().contentEquals(ContactType.SITE.getDescription()))
			{
				contact.setSite(v);
			}

			else if (c.getContact().getName().contentEquals(ContactType.FONE.getDescription()))
			{
				IdoContactDialCodeResponse fone = new IdoContactDialCodeResponse();

				fone.setContact(contactMapper.modelToResponse(c.getContact()));
				fone.setEnable(c.getEnable());
				fone.setId(c.getId());

				String[] splitNumber = c.getValue().split(";");

				fone.setDialCode(splitNumber.length > 0 ? splitNumber[0] : null);
				fone.setValue(splitNumber.length > 1 ? splitNumber[1] : null);
				contact.setPhone(fone);

			}
			else if (c.getContact().getName().contentEquals(ContactType.SMS.getDescription()))
			{
				IdoContactDialCodeResponse sms = new IdoContactDialCodeResponse();

				sms.setContact(contactMapper.modelToResponse(c.getContact()));
				sms.setEnable(c.getEnable());
				sms.setId(c.getId());

				String[] splitNumber = c.getValue().split(";");

				sms.setDialCode(splitNumber.length > 0 ? splitNumber[0] : null);
				sms.setValue(splitNumber.length > 1 ? splitNumber[1] : null);
				contact.setSms(sms);

			}
			else if (c.getContact().getName().contentEquals(ContactType.WHATSAPP.getDescription()))
			{
				IdoContactDialCodeResponse whatsapp = new IdoContactDialCodeResponse();

				whatsapp.setContact(contactMapper.modelToResponse(c.getContact()));
				whatsapp.setEnable(c.getEnable());
				whatsapp.setId(c.getId());

				String[] splitNumber = c.getValue().split(";");

				whatsapp.setDialCode(splitNumber.length > 0 ? splitNumber[0] : null);
				whatsapp.setValue(splitNumber.length > 1 ? splitNumber[1] : null);
				contact.setWhatsapp(whatsapp);

			}
			else if (c.getContact().getName().contentEquals(ContactType.TELEGRAM.getDescription()))
			{
				IdoContactDialCodeResponse telegram = new IdoContactDialCodeResponse();
				telegram.setContact(contactMapper.modelToResponse(c.getContact()));
				telegram.setEnable(c.getEnable());
				telegram.setId(c.getId());

				String[] splitNumber = c.getValue().split(";");

				telegram.setDialCode(splitNumber.length > 0 ? splitNumber[0] : null);
				telegram.setValue(splitNumber.length > 1 ? splitNumber[1] : null);
				contact.setTelegram(telegram);

			}
			else if (c.getContact().getName().contentEquals(ContactType.ENDERECO.getDescription()))
			{
				IdoContactValueResponse adress = new IdoContactValueResponse();

				adress.setContact(contactMapper.modelToResponse(c.getContact()));
				adress.setEnable(c.getEnable());
				adress.setValue(c.getValue());
				adress.setId(c.getId());
				adresses.add(adress);
				contact.setAddresses(adresses);
			}

		});
		
		return contact;

	}
	
	@Transactional
	private IdoContactResponse buildContactAndSave(Ido ido, IdoContactRequest request, boolean isUpdate) {
		
		IdoContactResponse response = new IdoContactResponse();
		List<IdoContactValueResponse> adresses = new ArrayList<>();
		boolean isActivate = true;
				
		List<IdoContact> idoContacts = idoContactRepository.findIdoContactByIdo(ido);
		
		if(isUpdate) {
			for(IdoContact c : idoContacts) {
				if(!c.getActivated()) {
					isActivate = false;
				}
			}
		}
		
		if(!idoContacts.isEmpty() && !isUpdate) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Contact", "api.error.contact.ido.conflict"); 
		}
		if(idoContacts.isEmpty() && isUpdate) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Contact", "api.error.contact.ido.conflict.not.found");
		}
			
		if (nonNull(request.getFacebook()) && nonNull(request.getFacebook().getValue()))
		{

			Contact contact = contactRepository.findById(ContactType.FACEBOOK.getId()).orElseThrow(
			    () -> new BaseFullException(HttpStatus.NOT_FOUND, "Contato", "api.error.contact.ido.facebook.not.found"));

			IdoContact saveContact = IdoContact.builder().enable(request.getFacebook().getEnable()).value(IdoStringUtils.validateLink(request.getFacebook().getValue())).contact(
			    contact).activated(isActivate).ido(ido).build();

			IdoContact savedModel = new IdoContact();
			
			if (isUpdate)
			{	
				
				IdoContact contactBase = idoContacts.stream().filter(
				    idoContact -> ContactType.FACEBOOK.getId().equals(idoContact.getContact().getId())).findAny().orElse(
				        IdoContact.builder().contact(contact).activated(false).ido(ido).build());

				saveContact.setId(contactBase.getId());
				BeanUtils.copyProperties(saveContact, contactBase, "id", "dateModel", "ido", "contact");
				savedModel = idoContactRepository.save(contactBase);
			}
			else
			{
				savedModel = idoContactRepository.save(saveContact);
			}

			IdoContactValueResponse r = idoContactMapper.valueResponse(savedModel);
			response.setFacebook(r);

		}

		if (nonNull(request.getInstagram()) && nonNull(request.getInstagram().getValue()))
		{

			Contact contact = contactRepository.findById(ContactType.INSTAGRAM.getId()).orElseThrow(
			    () -> new BaseFullException(HttpStatus.NOT_FOUND, "Contato", "api.error.contact.ido.instagram.not.found"));

			IdoContact saveContact = IdoContact.builder().enable(request.getInstagram().getEnable()).value(IdoStringUtils.validateLink(request.getInstagram().getValue())).contact(
			    contact).activated(isActivate).ido(ido).build();

			IdoContact savedModel = new IdoContact();
			
			if (isUpdate)
			{
				IdoContact contactBase = idoContacts.stream().filter(
				    idoContact -> ContactType.INSTAGRAM.getId().equals(idoContact.getContact().getId())).findAny().orElse(
				        IdoContact.builder().contact(contact).activated(false).ido(ido).build());
				saveContact.setId(contactBase.getId());
				BeanUtils.copyProperties(saveContact, contactBase, "id", "dateModel", "ido", "contact");
				savedModel = idoContactRepository.save(contactBase);
			}
			else
			{
				savedModel = idoContactRepository.save(saveContact);
			}

			IdoContactValueResponse r = idoContactMapper.valueResponse(savedModel);
			response.setInstagram(r);

		}
		
		if (nonNull(request.getLinkedin()) && nonNull(request.getLinkedin().getValue()))
		{

			Contact contact = contactRepository.findById(ContactType.LINKEDIN.getId()).orElseThrow(
			    () -> new BaseFullException(HttpStatus.NOT_FOUND, "Contato", "api.error.contact.ido.linkedin.not.found"));

			IdoContact saveContact = IdoContact.builder().enable(request.getLinkedin().getEnable()).value(IdoStringUtils.validateLink(request.getLinkedin().getValue())).contact(
			    contact).activated(isActivate).ido(ido).build();

			IdoContact savedModel = new IdoContact();

			if (isUpdate)
			{
				IdoContact contactBase = idoContacts.stream().filter(
				    idoContact -> ContactType.LINKEDIN.getId().equals(idoContact.getContact().getId())).findAny().orElse(
				        IdoContact.builder().contact(contact).activated(false).ido(ido).build());

				saveContact.setId(contactBase.getId());
				BeanUtils.copyProperties(saveContact, contactBase, "id", "dateModel", "ido", "contact");
				savedModel = idoContactRepository.save(contactBase);
			}
			else
			{
				savedModel = idoContactRepository.save(saveContact);
			}

			IdoContactValueResponse r = idoContactMapper.valueResponse(savedModel);
			response.setLinkedin(r);

		}
		
		if (nonNull(request.getYoutube()) && nonNull(request.getYoutube().getValue()))
		{

			Contact contact = contactRepository.findById(ContactType.YOUTUBE.getId()).orElseThrow(
			    () -> new BaseFullException(HttpStatus.NOT_FOUND, "Contato", "api.error.contact.ido.youtube.not.found"));

			IdoContact saveContact = IdoContact.builder().enable(request.getYoutube().getEnable()).value(IdoStringUtils.validateLink(request.getYoutube().getValue())).contact(
			    contact).activated(isActivate).ido(ido).build();

			IdoContact savedModel = new IdoContact();
			
			if (isUpdate)
			{
				IdoContact contactBase = idoContacts.stream().filter(
				    idoContact -> ContactType.YOUTUBE.getId().equals(idoContact.getContact().getId())).findAny().orElse(
				        IdoContact.builder().contact(contact).activated(false).ido(ido).build());
				
				saveContact.setId(contactBase.getId());
				BeanUtils.copyProperties(saveContact, contactBase, "id", "dateModel", "ido", "contact");
				savedModel = idoContactRepository.save(contactBase);
			}
			else
			{
				savedModel = idoContactRepository.save(saveContact);
			}

			IdoContactValueResponse r = idoContactMapper.valueResponse(savedModel);
			response.setYoutube(r);

		}
		
		if (nonNull(request.getTiktok()) && nonNull(request.getTiktok().getValue()))
		{

			Contact contact = contactRepository.findById(ContactType.TIKTOK.getId()).orElseThrow(
			    () -> new BaseFullException(HttpStatus.NOT_FOUND, "Contato", "api.error.contact.ido.tiktok.not.found"));

			IdoContact saveContact = IdoContact.builder().enable(request.getTiktok().getEnable()).value(IdoStringUtils.validateLink(request.getTiktok().getValue())).contact(
			    contact).activated(isActivate).ido(ido).build();
			
			IdoContact savedModel = new IdoContact();

			if (isUpdate)
			{
				IdoContact contactBase = idoContacts.stream().filter(
				    idoContact -> ContactType.TIKTOK.getId().equals(idoContact.getContact().getId())).findAny().orElse(
				        IdoContact.builder().contact(contact).activated(false).ido(ido).build());
				
				saveContact.setId(contactBase.getId());
				BeanUtils.copyProperties(saveContact, contactBase, "id", "dateModel", "ido", "contact");
				savedModel = idoContactRepository.save(contactBase);
			}
			else
			{
				savedModel = idoContactRepository.save(saveContact);
			}

			IdoContactValueResponse r = idoContactMapper.valueResponse(savedModel);
			response.setTiktok(r);

		}
		
		if (nonNull(request.getSite()) && nonNull(request.getSite().getValue()))
		{

			Contact contact = contactRepository.findById(ContactType.SITE.getId()).orElseThrow(
			    () -> new BaseFullException(HttpStatus.NOT_FOUND, "Contato", "api.error.contact.ido.site.not.found"));

			IdoContact saveContact = IdoContact.builder().enable(request.getSite().getEnable()).value(IdoStringUtils.validateLink(request.getSite().getValue())).contact(
			    contact).activated(isActivate).ido(ido).build();
			
			IdoContact savedModel = new IdoContact();

			if (isUpdate)
			{
				IdoContact contactBase = idoContacts.stream().filter(
				    idoContact -> ContactType.SITE.getId().equals(idoContact.getContact().getId())).findAny().orElse(
				        IdoContact.builder().contact(contact).activated(false).ido(ido).build());
				
				saveContact.setId(contactBase.getId());
				BeanUtils.copyProperties(saveContact, contactBase, "id", "dateModel", "ido", "contact");
				savedModel = idoContactRepository.save(contactBase);
			}
			else
			{
				savedModel = idoContactRepository.save(saveContact);
			}

			IdoContactValueResponse r = idoContactMapper.valueResponse(savedModel);
			response.setSite(r);

		}		
		
		if(nonNull(request.getMail()) && nonNull(request.getMail().getValue())) {
			
			Contact contact = contactRepository.findById(ContactType.EMAIL.getId()).orElseThrow(
					() -> new BaseFullException(HttpStatus.NOT_FOUND, "Contact", "api.error.contact.ido.email.not.found"));
			
			validateEmail(request.getMail(), contact);
			IdoContact saveContact = IdoContact.builder()
					.enable(request.getMail().getEnable())
					.value(request.getMail().getValue())
					.activated(isActivate)
					.contact(contact)
					.ido(ido).build();
			
			IdoContact savedModel = new IdoContact();
			
			if(isUpdate) {
				IdoContact contactBase = idoContacts.stream()
						  .filter(idoContact -> ContactType.EMAIL.getId().equals(idoContact.getContact().getId()))
						  .findAny()
						  .orElse(IdoContact.builder().contact(contact).activated(false).ido(ido).build());
				
				saveContact.setId(contactBase.getId());
				BeanUtils.copyProperties(saveContact, contactBase, "id","dateModel", "ido", "contact");
				savedModel = idoContactRepository.save(contactBase);
			}else {
				savedModel = idoContactRepository.save(saveContact);
			}		
			
			IdoContactValueResponse r = idoContactMapper.valueResponse(savedModel);
			response.setMail(r);
			
		}
		
		if(nonNull(request.getSms()) && nonNull(request.getSms().getValue())) {
			
			Contact contact = contactRepository.findById(ContactType.SMS.getId()).orElseThrow(
					() -> new BaseFullException(HttpStatus.NOT_FOUND, "Contact", "api.error.contact.ido.sms.not.found"));
			
			String value = request.getSms().getDialCode() +";"+ request.getSms().getValue();
			
			IdoContact saveContact = IdoContact.builder()
					.enable(request.getSms().getEnable())
					.value(value)
					.activated(isActivate)
					.contact(contact)
					.ido(ido).build();
			
			IdoContact savedModel = new IdoContact();
			
			if(isUpdate) {
				IdoContact contactBase = idoContacts.stream()
						  .filter(idoContact -> ContactType.SMS.getId().equals(idoContact.getContact().getId()))
						  .findAny()
						  .orElse(IdoContact.builder().contact(contact).activated(false).ido(ido).build());
				
				saveContact.setId(contactBase.getId());BeanUtils.copyProperties(saveContact, contactBase, "id","dateModel", "ido", "contact");
				savedModel = idoContactRepository.save(contactBase);
			}else {
				savedModel = idoContactRepository.save(saveContact);
			}		
						
			IdoContactDialCodeResponse r = idoContactMapper.valueResponseDialCode(savedModel);
			response.setSms(r);
			response.getSms().setDialCode(request.getSms().getDialCode());
			response.getSms().setValue(request.getSms().getValue());
		}
		
		if(nonNull(request.getWhatsapp()) && nonNull(request.getWhatsapp().getValue())) {
			
			Contact contact = contactRepository.findById(ContactType.WHATSAPP.getId()).orElseThrow(
					() -> new BaseFullException(HttpStatus.NOT_FOUND, "Contact", "api.error.contact.ido.whatsapp.not.found"));
			
			String value = request.getWhatsapp().getDialCode() +";"+ request.getWhatsapp().getValue();
			
			IdoContact saveContact = IdoContact.builder()
					.enable(request.getWhatsapp().getEnable())
					.value(value)
					.activated(isActivate)
					.contact(contact)
					.ido(ido).build();
			
			IdoContact savedModel = new IdoContact();
			
			if(isUpdate) {
				IdoContact contactBase = idoContacts.stream()
						  .filter(idoContact -> ContactType.WHATSAPP.getId().equals(idoContact.getContact().getId()))
						  .findAny()
						  .orElse(IdoContact.builder().contact(contact).activated(false).ido(ido).build());
				
				saveContact.setId(contactBase.getId());
				BeanUtils.copyProperties(saveContact, contactBase, "id","dateModel", "ido", "contact");
				savedModel = idoContactRepository.save(contactBase);
			}else {
				savedModel = idoContactRepository.save(saveContact);
			}			
						
			IdoContactDialCodeResponse r = idoContactMapper.valueResponseDialCode(savedModel);
			response.setWhatsapp(r);
			response.getWhatsapp().setDialCode(request.getWhatsapp().getDialCode());
			response.getWhatsapp().setValue(request.getWhatsapp().getValue());
		}
		
		if(nonNull(request.getTelegram()) && nonNull(request.getTelegram().getValue())) {
			
			Contact contact = contactRepository.findById(ContactType.TELEGRAM.getId()).orElseThrow(
					() -> new BaseFullException(HttpStatus.NOT_FOUND, "Contact", "api.error.contact.ido.telegram.not.found"));
			
			String value = request.getTelegram().getDialCode() +";"+ request.getTelegram().getValue();
			
			IdoContact saveContact = IdoContact.builder()
					.enable(request.getTelegram().getEnable())
					.value(value)
					.activated(isActivate)
					.contact(contact)
					.ido(ido).build();
			
			IdoContact savedModel = new IdoContact();
			
			if(isUpdate) {
				IdoContact contactBase = idoContacts.stream()
						  .filter(idoContact -> ContactType.TELEGRAM.getId().equals(idoContact.getContact().getId()))
						  .findAny()
						  .orElse(IdoContact.builder().contact(contact).activated(false).ido(ido).build());
				
				saveContact.setId(contactBase.getId());BeanUtils.copyProperties(saveContact, contactBase, "id","dateModel", "ido", "contact");
				savedModel = idoContactRepository.save(contactBase);
			}else {
				savedModel = idoContactRepository.save(saveContact);
			}		
						
			IdoContactDialCodeResponse r = idoContactMapper.valueResponseDialCode(savedModel);
			response.setTelegram(r);
			response.getTelegram().setDialCode(request.getTelegram().getDialCode());
			response.getTelegram().setValue(request.getTelegram().getValue());
		}
		
		if(nonNull(request.getPhone()) && nonNull(request.getPhone().getValue())) {
			
			Contact contact = contactRepository.findById(ContactType.FONE.getId()).orElseThrow(
					() -> new BaseFullException(HttpStatus.NOT_FOUND, "Contact", "api.error.contact.ido.phone.not.found"));
			
			String value = request.getPhone().getDialCode() +";"+ request.getPhone().getValue();
			
			IdoContact saveContact = IdoContact.builder()
					.enable(request.getPhone().getEnable())
					.value(value)
					.activated(isActivate)
					.contact(contact)
					.ido(ido).build();
			
			IdoContact savedModel = new IdoContact();
			
			if(isUpdate) {
				IdoContact contactBase = idoContacts.stream()
						  .filter(idoContact -> ContactType.FONE.getId().equals(idoContact.getContact().getId()))
						  .findAny()
						  .orElse(IdoContact.builder().contact(contact).activated(false).ido(ido).build());
				
				saveContact.setId(contactBase.getId());
				BeanUtils.copyProperties(saveContact, contactBase, "id","dateModel", "ido", "contact");
				savedModel = idoContactRepository.save(contactBase);
			}else {
				savedModel = idoContactRepository.save(saveContact);
			}			
						
			IdoContactDialCodeResponse r = idoContactMapper.valueResponseDialCode(savedModel);
			response.setPhone(r);
			response.getPhone().setDialCode(request.getPhone().getDialCode());
			response.getPhone().setValue(request.getPhone().getValue());
		}
		
		if (nonNull(request.getAddresses()) && !request.getAddresses().isEmpty()) {
			
			Contact contact = contactRepository.findById(ContactType.ENDERECO.getId()).orElseThrow(
					() -> new BaseFullException(HttpStatus.NOT_FOUND, "Contact", "api.error.contact.ido.address.not.found"));
			
			
			if(isUpdate) {
				idoContactRepository.deleteIdoContactByIdoAndContact(ido,contact);
			}
			
			for (IdoContactValueRequest c : request.getAddresses()) {
				IdoContact saveContact = IdoContact.builder()
						.enable(c.getEnable())
						.value(c.getValue())
						.activated(isActivate)
						.contact(contact)
						.ido(ido).build();
								
				IdoContact savedModel = idoContactRepository.save(saveContact);
				IdoContactValueResponse responseContact = idoContactMapper.valueResponse(savedModel);
				adresses.add(responseContact);
			}
			
		}
		response.setAddresses(adresses);
		response.setActivated(isActivate);
		return response;
	}
	
	private IdoContactValueResponse idocontactSetVelues(IdoContact contact) {
		IdoContactValueResponse object = new IdoContactValueResponse();
		object.setContact(contactMapper.modelToResponse(contact.getContact()));
		object.setEnable(contact.getEnable());
		object.setValue(contact.getValue());
		object.setId(contact.getId());
		return object;
	}
		
	private void validateDomain(String name, Long idoId) {
		// check if there are special character
		if (IdoStringUtils.haveSpecialCharacter(name)) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Domain","api.error.domain.bad.request.special.character");
		}

		Optional<Ido> model = Optional.empty();
		
		if(idoId != null) {
			model = repository.findByDomain(name, idoId);
		}else {
			model = repository.findByDomain(name);
		}
		
		if (model.isPresent()) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Domain", "api.error.domain.bad.request.used");
		}
	}
	
	private void validateProfile(IdoProfileRequest request, Long userId, Country country) {
		
		//check if there are 3 or more category
		if(request.getCategories().isEmpty() || request.getCategories().size() > 3 ) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Category", "api.error.ido.hide.category.limited.conflict");
		}
		
		if(country.getId() == null) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Country", "api.error.country.not.found");
		}
		
		validateDomain(request.getDomain());

	}

	private void saveToolsDefault(PredefinedModelsTemplate template, Ido ido) {
		
		//a ordem que os metodos de adicao de tools default sao chamados vai influenciar na posicao que as ferramentas aparecem na pagina do ido
		if(template.equals(PredefinedModelsTemplate.NEWWHITE) ||
				template.equals(PredefinedModelsTemplate.AURORA) || 
				template.equals(PredefinedModelsTemplate.SUMMER) || 
				template.equals(PredefinedModelsTemplate.SCRIBBLE) || 
				template.equals(PredefinedModelsTemplate.NEWMARINE) || 
				template.equals(PredefinedModelsTemplate.NEWCITRIC) || 
				template.equals(PredefinedModelsTemplate.STELLAR) ||
				template.equals(PredefinedModelsTemplate.NEWREFLECTION)){
			
			saveContactDefault(ido);
			saveLinksDefault(ido); 
			
		}
		
		switch (template) {
		case MURAL:
			saveBannerImageDefault(ido); 
			saveLinksDefault(ido); 
			break;
		case LOLLIPOP:
			saveContactDefault(ido);
			saveLinksDefault(ido); 
			saveBannerVideoDefault(ido);
			break;
		case MYMUSIC:
			saveCarouselDefaultLandscape(ido);
			saveLinksDefault(ido); 
			saveBannerVideoDefault(ido);
			break;
		case HEALTH:
			saveContactDefault(ido);
			saveCarouselDefaultPortrait(ido);
			break;	
		case RAINBOW:
			saveLinksDefault(ido); 
			saveCarouselDefaultLandscape(ido);
			break;
		case URBAN:
			saveBannerImageDefault(ido); 
			saveContactDefault(ido);
			saveCarouselDefaultLandscape(ido);
			saveLinksDefault(ido); 
			break;
		case PORTFOLIO:
			saveCarouselDefaultPortrait(ido);
			saveLinksDefault(ido); 
			break;
		case LINENEEDLE:
			saveContactDefault(ido);
			saveBannerVideoDefault(ido);
			saveCarouselDefaultPortrait(ido);
			break;		
		case INFLUENCER:
			saveBannerImageDefault(ido); 
			saveLinksDefault(ido); 
			saveCarouselDefaultPortrait(ido);
			break;	
		default :
			break;
		}
		
				
	}
	
	/**
	 * Adiciona os links padroes do template 
	 * @param ido
	 */
	@Transactional
	private void saveLinksDefault( Ido ido) {
		
		//facebook
		LinkRequest facebook = LinkRequest.builder()
				.title("Facebook")
				.url("https://www.facebook.com").build();
		
		linkService.create(ido.getId(), facebook);
		
		//instagram
		LinkRequest instagram = LinkRequest.builder()
				.title("Instagram")
				.url("https://www.instagram.com").build();
		
		linkService.create(ido.getId(), instagram);
		
		LinkRequest youtube = LinkRequest.builder()
				.title("Youtube")
				.url("https://www.youtube.com").build();
		
		linkService.create(ido.getId(), youtube);
				
		LinkRequest linkedin = LinkRequest.builder()
				.title("Linkedin")
				.url("https://www.linkedin.com").build();
		
		linkService.create(ido.getId(), linkedin);
		
	}
	
	/**
	 * adiciona os contatos padroes
	 * @param ido
	 */
	@Transactional
	private void saveContactDefault(Ido ido) {
		
		IdoContactDialCodeRequest contactRequest = new IdoContactDialCodeRequest();
		contactRequest.setDialCode("+00");
		contactRequest.setEnable(true);
		contactRequest.setValue("(00)0000-0000");
		
		IdoContactValueRequest email = new IdoContactValueRequest();
				email.setEnable(true);
				email.setValue("teste@gmail.com");
		
		IdoContactRequest request = IdoContactRequest.builder()
				.whatsapp(contactRequest)
				.sms(contactRequest)
				.phone(contactRequest)
				.mail(email).build();
		
		this.saveContact(ido.getId(), request, false);
		
	}
	
	/**
	 * adiciona o banner de imagem padrao
	 * @param ido
	 */
	@Transactional
	private void saveBannerImageDefault(Ido ido) {
		
		BlobFileResponse image =   storageApi.findByUuid("c71cc00a-0443-4d3a-b566-7884fed3d9fa");
		
		ImageBannerContactRequest contactRequest = new ImageBannerContactRequest();
		contactRequest.setDialCode("+00");
		contactRequest.setNumber("(00)0000-0000");		
		
		ImageBannerRequest request = ImageBannerRequest.builder()
				.action(ImageBannerAction.NONE)
				.contact(contactRequest)
				.field("None")
				.fileId(image.getId())
				.title("None").build();
		
		imageBannerService.save(ido.getId(), request);
		
	}
	
	/**
	 * adiciona o banner de video padrao
	 * @param ido
	 */
	@Transactional
	private void saveBannerVideoDefault(Ido ido) {
		
		VideoBannerRequest request = VideoBannerRequest.builder()
				.link("https://www.youtube.com/watch?v=JrMeo2Hd8hA")
				.thumbnail("https://img.youtube.com/vi/JrMeo2Hd8hA/0.jpg").title("None").build();
		
		videoBannerService.save(ido.getId(), request);
	}
	
	/**
	 * adiciona o carrossel de imagem padrao
	 * @param ido
	 */
	private void saveCarouselDefaultLandscape(Ido ido) {
		saveCarouselDefault(ido, ImageAspect.LANDSCAPE, "c71cc00a-0443-4d3a-b566-7884fed3d9fa");
	}
	
	/**
	 * adiciona o carrossel de imagem padrao
	 * @param ido
	 */
	private void saveCarouselDefaultPortrait(Ido ido) {
		saveCarouselDefault(ido, ImageAspect.PORTRAIT, "6cd68b7f-c7f7-40a3-b456-81d58827c01e");
	}
	
	@Transactional
	private void saveCarouselDefault(Ido ido, ImageAspect aspect, String uiid) {
		
		BlobFileResponse image =   storageApi.findByUuid(uiid);
		
		ImageCarouselItemDetailRequest detail = new ImageCarouselItemDetailRequest();
		detail.setTitle("none");
		detail.setPrice(0.0);
		detail.setDescription("none");		
		
		ImageCarouselItemRequest itemRequest = ImageCarouselItemRequest.builder()
				.imageId(image.getId())
				.action(ImageBannerAction.NONE)
				.dialCode("+00")
				.detail(detail)
				.addDetail(true).build();

		List<ImageCarouselItemRequest> items = new ArrayList<>();
		items.add(itemRequest);
		items.add(itemRequest);
		items.add(itemRequest);
		
		ImageCarouselRequest request = ImageCarouselRequest.builder()
				.imageformat(aspect)
				.itens(items)
				.showTitle(false)
				.title("None").build();
		
		imageCarouselService.create(ido.getId(), request);
	} 
	
	@SuppressWarnings("unused")
	private void alterDataIdo(Ido ido) {
		
		List<IdoContact> contacts = idoContactRepository.findIdoContactByIdo(ido);
		
		for(IdoContact contact : contacts) {
			
			contact.setValue(null);
			idoContactRepository.save(contact);
			
		}
	}
	
	
}
