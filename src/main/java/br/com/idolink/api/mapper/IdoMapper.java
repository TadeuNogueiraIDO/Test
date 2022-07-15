package br.com.idolink.api.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.IdoSharingThumbRequest;
import br.com.idolink.api.dto.request.ido.IdoProfileRequest;
import br.com.idolink.api.dto.request.ido.IdoRequest;
import br.com.idolink.api.dto.response.IdoContactResponse;
import br.com.idolink.api.dto.response.IdoResponse;
import br.com.idolink.api.dto.response.IdoSharingThumbResponse;
import br.com.idolink.api.dto.response.LanguageResponse;
import br.com.idolink.api.dto.response.ido.IdoModelResponse;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoContact;
import br.com.idolink.api.model.Language;
import br.com.idolink.api.repository.IdoContactRepository;
import br.com.idolink.api.service.AppearanceButtonService;
import br.com.idolink.api.service.AppearanceCardsService;
import br.com.idolink.api.service.AppearanceTextService;
import br.com.idolink.api.service.AttachedPdfService;
import br.com.idolink.api.service.BusinessHourService;
import br.com.idolink.api.service.ConfigContactUsService;
import br.com.idolink.api.service.ConfigFaqService;
import br.com.idolink.api.service.ConfigNewsletterFormService;
import br.com.idolink.api.service.EmbedHtmlService;
import br.com.idolink.api.service.ImageBannerService;
import br.com.idolink.api.service.ImageCarouselService;
import br.com.idolink.api.service.LinkService;
import br.com.idolink.api.service.LogoBioService;
import br.com.idolink.api.service.MenuFooterService;
import br.com.idolink.api.service.MessagePropertieService;
import br.com.idolink.api.service.ShopService;
import br.com.idolink.api.service.VideoBannerService;
import br.com.idolink.api.service.WallpaperService;

@Service
public class IdoMapper {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private WallpaperService wallpaperService;

	@Autowired
	private StorageApi storageApi;

	@Autowired
	private BusinessHourService businessHourService;

	@Autowired
	private ConfigContactUsService configContactUsService;

	@Autowired
	private ConfigFaqService configFaqService;

	@Autowired
	private ImageBannerService imageBannerService;

	@Autowired
	private VideoBannerService videoBannerService;

	@Autowired
	private LinkService linkService;

	@Autowired
	private ConfigNewsletterFormService configNewsletterFormService;

	@Autowired
	private AttachedPdfService attachedPdfService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private AppearanceCardsService appearanceCardsService;

	@Autowired
	private AppearanceButtonService appearanceButtonService;

	@Autowired
	private AppearanceTextService appearanceTextService;

	@Autowired
	private ImageCarouselService imageCarouselService;
	
	@Autowired
	private EmbedHtmlService embedHtmlService;
	
	@Autowired
	private LogoBioService logoBioService;	
	
	@Autowired
	private IdoContactMapper idoContactMapper;
	
	@SuppressWarnings("unused")
	@Autowired
	private MenuFooterService menuFooterService;
	
	@Autowired
	private IdoContactRepository idoContactRepository;
	
	@Autowired
	private MessagePropertieService messagePropertieService;

	public IdoResponse response(Ido model, boolean isIdoComplete) {
		IdoResponse response = mapper.map(model, IdoResponse.class);
		setIdoWallpaper(response, model);
		
		List<IdoContact> contacts = idoContactRepository.findByIdo(model.getId());
		
		for(IdoContact contact   : contacts) {
			
			@SuppressWarnings("unused")
			IdoContactResponse idoContactResponse =   idoContactMapper.modelToResponse(contact);
			
		}
	
		convertsharingThumbInResponse(response, model.getSharingThumb());

		if (model.getIcon() != null && model.getIcon() != 0) {
			response.setIcon(storageApi.findFileById(model.getIcon()));
		}
		initializeList(response);

		if (isIdoComplete) {
			loadTools(response);
		}

		return translateIdoMessages(response);
	}
	
	public IdoResponse modelResponse(Ido model, boolean isIdoComplete) {
		IdoResponse response = mapper.map(model, IdoResponse.class);
		setIdoWallpaper(response, model);
		convertsharingThumbInResponse(response, model.getSharingThumb());

		if (model.getIcon() != null && model.getIcon() != 0) {
			response.setIcon(storageApi.findFileById(model.getIcon()));
		}
		publicInitializeList(response);

		if (isIdoComplete) {
			publicLoadTools(response);
		}

		return translateIdoMessages(response);
	}

	public List<IdoResponse> response(List<Ido> model) {
		return model.stream().map(m -> this.response(m, false)).collect(Collectors.toList());
	}

	public Ido model(IdoRequest request) {
		return mapper.map(request, Ido.class);
	}

	public Ido modelProfile(IdoProfileRequest request) {
		return mapper.map(request, Ido.class);
	}

	public IdoResponse profileResponse(Ido model) {
		IdoResponse response = mapper.map(model, IdoResponse.class);
		setIdoWallpaper(response, model);
		initializeList(response);
		return translateIdoMessages(response);
	}

	public IdoModelResponse modelResponse(IdoResponse model) {
		return mapper.map(model, IdoModelResponse.class);
	}

	private void setIdoWallpaper(IdoResponse response, Ido model) {
		if (Objects.nonNull(model.getWallpaper()) && Objects.nonNull(model.getWallpaperType())) {
			switch (model.getWallpaperType()) {
			case COLOR:
				Long idColor = Long.valueOf(model.getWallpaper());
				response.setWallpaper(wallpaperService.findColorById(idColor));
				break;
			case GALLERY:
				Long idGallery = Long.valueOf(model.getWallpaper());
				response.setWallpaper(wallpaperService.findWallpaperById(idGallery));
				break;
			case GRADIENT:
				Long idGradient = Long.valueOf(model.getWallpaper());
				response.setWallpaper(wallpaperService.findGradientById(idGradient));
				break;
			case UPLOAD:
				Long idUpload = Long.valueOf(model.getWallpaper());
				response.setWallpaper(storageApi.findFileById(idUpload));
				break;
			default:
				break;
			}
		}
	}

	private void initializeList(IdoResponse response) {
		response.setBusinessHour(businessHourService.findByIdo(response.getId(), false));
		response.setImageBanners(response.getImageBanners() == null ? new ArrayList<>() : response.getImageBanners());
		response.setVideoBanners(response.getVideoBanners() == null ? new ArrayList<>() : response.getVideoBanners());
		response.setLinks(response.getLinks() == null ? new ArrayList<>() : response.getLinks());
		response.setAttachedPdfs(response.getAttachedPdfs() == null ? new ArrayList<>() : response.getAttachedPdfs());
		response.setImagesCarousel(response.getImagesCarousel() == null ? new ArrayList<>() : response.getImagesCarousel());
		response.setEmbedHtmls(response.getEmbedHtmls() == null ? new ArrayList<>() : response.getEmbedHtmls());
		response.setCategories(response.getAttachedPdfs() == null ? new ArrayList<>() : response.getCategories());
		response.setModelParams(response.getModelParams() == null ? new ArrayList<>() : response.getModelParams());
		
	}
	
	private void publicInitializeList(IdoResponse response) {
		response.setBusinessHour(businessHourService.publicFindByIdo(response.getId(), false));
		response.setImageBanners(response.getImageBanners() == null ? new ArrayList<>() : response.getImageBanners());
		response.setVideoBanners(response.getVideoBanners() == null ? new ArrayList<>() : response.getVideoBanners());
		response.setLinks(response.getLinks() == null ? new ArrayList<>() : response.getLinks());
		response.setAttachedPdfs(response.getAttachedPdfs() == null ? new ArrayList<>() : response.getAttachedPdfs());
		response.setImagesCarousel(response.getImagesCarousel() == null ? new ArrayList<>() : response.getImagesCarousel());
		response.setEmbedHtmls(response.getEmbedHtmls() == null ? new ArrayList<>() : response.getEmbedHtmls());
		response.setCategories(response.getAttachedPdfs() == null ? new ArrayList<>() : response.getCategories());
		response.setModelParams(response.getModelParams() == null ? new ArrayList<>() : response.getModelParams());
		
	}

	private void loadTools(IdoResponse response) {
		response.setBusinessHour(businessHourService.findByIdo(response.getId(), false));
		response.setImageBanners(imageBannerService.findByIdo(response.getId()));
		response.setVideoBanners(videoBannerService.findByIdo(response.getId()));
		response.setLinks(linkService.listByIdo(response.getId()));
		response.setAttachedPdfs(attachedPdfService.findByIdo(response.getId()));
		response.setImagesCarousel(imageCarouselService.findByIdo(response.getId()));
		response.setEmbedHtmls(embedHtmlService.listByIdo(response.getId()));
	    response.setLogoBio(logoBioService.findByIdo(response.getId()));
		response.setAppearanceText(appearanceTextService.findByIdo(response.getId(), false));
		response.setAppearanceCard(appearanceCardsService.findByIdo(response.getId(), false));
		response.setAppearanceButtons(appearanceButtonService.findByIdo(response.getId(), false));
		response.setShop(shopService.findByIdo(response.getId(), false));
		response.setConfigNewsletterForm(configNewsletterFormService.findByIdo(response.getId(), false));
		response.setConfigFaq(configFaqService.findByIdo(response.getId(), false));
		response.setConfigContactUs(configContactUsService.findByIdo(response.getId(), false));
	    response.setMenuFooter(menuFooterService.findByIdo(response.getId(), false));
	    
	    //carrega a loja caso seja um ido rascunho
	    if(Objects.nonNull(response.getIdoPublished())){
	    	response.setShop(shopService.findByIdo(response.getIdoPublished(), false));
	    }
	}
	
	private void publicLoadTools(IdoResponse response) {
		response.setBusinessHour(businessHourService.publicFindByIdo(response.getId(), false));
		response.setConfigContactUs(configContactUsService.publicFindByIdo(response.getId(), false));
		response.setConfigFaq(configFaqService.publicFindByIdo(response.getId(), false));
		response.setImageBanners(imageBannerService.publicFindByIdo(response.getId()));
		response.setVideoBanners(videoBannerService.publicFindByIdo(response.getId()));
		response.setLinks(linkService.publicListByIdo(response.getId()));
		response.setAttachedPdfs(attachedPdfService.publicFindByIdo(response.getId()));
		response.setConfigNewsletterForm(configNewsletterFormService.publicFindByIdo(response.getId(), false));
		response.setShop(shopService.publicFindByIdo(response.getId(), false));
		response.setAppearanceButtons(appearanceButtonService.publicFindByIdo(response.getId(), false));
		response.setAppearanceCard(appearanceCardsService.publicFindByIdo(response.getId(), false));
		response.setAppearanceText(appearanceTextService.publicFindByIdo(response.getId(), false));
		response.setImagesCarousel(imageCarouselService.publicFindByIdo(response.getId()));
		response.setEmbedHtmls(embedHtmlService.publicListByIdo(response.getId()));
		response.setLogoBio(logoBioService.publicFindByIdo(response.getId()));
		response.setMenuFooter(menuFooterService.publicFindByIdo(response.getId(), false));
		
		//carrega a loja caso seja um ido rascunho
	    if(Objects.nonNull(response.getIdoPublished())){
	    	response.setShop(shopService.publicFindByIdo(response.getIdoPublished(), false));
	    }

	}

	private void convertsharingThumbInResponse(IdoResponse response, String sharingThumb) {

		if (Objects.nonNull(sharingThumb)) {

			IdoSharingThumbResponse thumb = new IdoSharingThumbResponse();

			String[] splitDataUser = sharingThumb.split(";");

			Long fileId = Long.valueOf(splitDataUser[0]);

			if (fileId != 0) {
				thumb.setFileId(storageApi.findFileById(fileId));
			}

			if (splitDataUser.length == 2 && !splitDataUser[1].isEmpty()) {
				thumb.setMessage(splitDataUser[1]);
			}

			response.setSharingThumb(thumb);
		}

	}

	public void convertRequestInSharingThumb(IdoSharingThumbRequest request, Ido ido) {

		StringBuffer dataUser = new StringBuffer();
		
		if(Objects.nonNull(request.getFileId())) {

			dataUser.append(request.getFileId()).append(";");
		}
		if (Objects.nonNull(request.getMessage())) {
			dataUser.append(request.getMessage());
		}

		ido.setSharingThumb(dataUser.toString());

	}

	public LanguageResponse modelToResponse(Language model) {
		return mapper.map(model, LanguageResponse.class);
	}
	
	private IdoResponse translateIdoMessages(IdoResponse response) {
		
		if(Objects.nonNull(response.getCountry())) {
			response.getCountry().setCountry(messagePropertieService.getMessageAttribute(response.getCountry().getCountry()));
		}
		
		if(Objects.nonNull(response.getCategories())) {
			response.getCategories().stream().forEach(n -> n.setName(messagePropertieService.getMessageAttribute(n.getName())));
		}
		return response;
	}
}
