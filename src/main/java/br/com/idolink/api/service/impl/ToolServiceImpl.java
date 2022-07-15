package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.ToolActivatedRequest;
import br.com.idolink.api.dto.request.ToolRequest;
import br.com.idolink.api.dto.response.ToolActivatedResponse;
import br.com.idolink.api.dto.response.ToolResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.filter.ToolFilter;
import br.com.idolink.api.mapper.ToolMapper;
import br.com.idolink.api.model.AttachedPdf;
import br.com.idolink.api.model.BusinessHour;
import br.com.idolink.api.model.ConfigContactUs;
import br.com.idolink.api.model.ConfigFaq;
import br.com.idolink.api.model.ConfigNewsletterForm;
import br.com.idolink.api.model.EmbedHtml;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoContact;
import br.com.idolink.api.model.ImageBanner;
import br.com.idolink.api.model.ImageCarousel;
import br.com.idolink.api.model.Link;
import br.com.idolink.api.model.LogoBio;
import br.com.idolink.api.model.MenuFooter;
import br.com.idolink.api.model.Shop;
import br.com.idolink.api.model.Tool;
import br.com.idolink.api.model.VideoBanner;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.AttachedPdfRepository;
import br.com.idolink.api.repository.BusinessHourRepository;
import br.com.idolink.api.repository.ConfigContactUsRepository;
import br.com.idolink.api.repository.ConfigFaqRepository;
import br.com.idolink.api.repository.ConfigNewsletterFormRepository;
import br.com.idolink.api.repository.EmbedHtmlRepository;
import br.com.idolink.api.repository.IdoContactRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.ImageBannerRepository;
import br.com.idolink.api.repository.ImageCarouselRepository;
import br.com.idolink.api.repository.LinkRepository;
import br.com.idolink.api.repository.LogoBioRepository;
import br.com.idolink.api.repository.MenuFooterRepository;
import br.com.idolink.api.repository.ShopRepository;
import br.com.idolink.api.repository.ToolRepository;
import br.com.idolink.api.repository.VideoBannerRepository;
import br.com.idolink.api.service.ToolService;

@Service
public class ToolServiceImpl implements ToolService {

	@Autowired
	private ToolMapper mapper;
	
	@Autowired
	private ToolRepository repository;
	
	@Autowired
	private BusinessHourRepository businessHourRepository;
	
	@Autowired
	private MenuFooterRepository menuFooterRepository;
	
	@Autowired
	private IdoContactRepository idoContactRepository;
	
	@Autowired
	private ConfigFaqRepository configFaqRepository;
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private IdoRepository idoRepository;
	
	@Autowired
	private VideoBannerRepository videoBannerRepository;
		
	@Autowired
	private ImageBannerRepository imageBannerRepository;
	
	@Autowired
	private LogoBioRepository logoBioRepository;
	
	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private EmbedHtmlRepository  embedHtmlRepository;
	
	@Autowired
	private ImageCarouselRepository imageCarouselRepository;
	
	@Autowired
	private AttachedPdfRepository attachedPdfRepository;
	
	@Autowired
	private ConfigNewsletterFormRepository  configNewsletterFormRepository;
	
	@Autowired
	private ConfigContactUsRepository configContactUsRepository;

	@Override
	@Transactional
	public ToolResponse create(ToolRequest request) {
		
		validateAppVersion(request.getAppversion());
		Tool model = mapper.requestToModel(request);
		
		try {
			model = repository.save(model);
		
		 }catch(DataIntegrityViolationException e) { 
			 throw new BaseFullException(HttpStatus.CONFLICT,"Tool", "api.error.tool.not.found");
		 }			
		return mapper.modelToResponse(model);
	}

	@Override
	@Transactional
	public ToolResponse update(Long id, ToolRequest request) {
		
		Optional<Tool> toolBase = repository.findById(id);
		validate(toolBase, "tool", "api.error.tool.not.found");
		validateAppVersion(request.getAppversion());
		
		
		Tool tool = toolBase.get();
		Tool modelRequest = mapper.requestToModel(request);
		
		BeanUtils.copyProperties(modelRequest, tool, "id", "dateModel");
		tool = repository.save(tool);
		return mapper.modelToResponse(tool);
		
	}

	@Override
	public ToolResponse findById(Long id) {

		Optional<Tool> tool = repository.findById(id);
		validate(tool, "tool", "api.error.tool.not.found");
									
		return mapper.modelToResponse(tool.get());
	}
	
	
	@Override
	public Tool findByCodName(ToolCodName codName) {

		Tool tool = repository.findByCodName(codName);
		
		if(tool == null) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Tool", "api.error.tool.not.found");
		}
			
		return tool;
	}
	
	@Override
	public List<ToolResponse> list(ToolFilter filter) {
		List<Tool> model = repository.findAll(filter);
		return mapper.modelToResponse(model);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		Optional<Tool> tool = repository.findById(id);
		validate(tool, "tool", "api.error.tool.not.found");
		
		try {
			repository.delete(tool.get());
			repository.flush();
		} catch(DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "tool", "api.error.tool.conflict");
		}
		
	}
	
	/**
	 * Informa se a versao do app suporta a ferramenta, de acordo com as versoes de cada um
	 * @param appVersion
	 * @param toolsVersion
	 * @return
	 */
	@Override
	public boolean isSuportedTools(String currentVersion, String toolsVersion) {
		
		validateAppVersion(currentVersion);
		validateAppVersion(toolsVersion);
				
		String[]splitCurrentVersion = currentVersion.split("\\.");
		Integer currentVersionInteger =  Integer.valueOf(splitCurrentVersion[0]) * 100 +  Integer.valueOf(splitCurrentVersion[1])*10 +  Integer.valueOf(splitCurrentVersion[2]); 
		
		String[]splitToolsVersion = toolsVersion.split("\\.");
		Integer toolsVersionInteger =  Integer.valueOf(splitToolsVersion[0]) * 100 +  Integer.valueOf(splitToolsVersion[1])*10 +  Integer.valueOf(splitToolsVersion[2]);
		
		
		if(currentVersionInteger >= toolsVersionInteger) {
			//versao suportada pelo app
			return true;
		}
		
		//versao nao suportada
		return false;
			
	}
	
	@Transactional
    @Override
    public ToolActivatedResponse enableDisableTool(ToolCodName codName, Long id,ToolActivatedRequest activatedRequest) {
				
		ToolActivatedResponse response = new ToolActivatedResponse();
		
		Tool tool = repository.findByCodName(codName);
		if(tool == null) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Tool", "api.error.tool.not.found");
		}
		
		switch (codName) {
				
		case BUSINESSHOUR:
			
			BusinessHour businessHour = businessHourRepository.findById(id)
			.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND, "BusinessHour",  "api.error.bussiness.hour.not.found"));
			businessHour.setActivated(activatedRequest.getActivated());
			businessHourRepository.save(businessHour);
			response = mapper.responseToBusinessHour(businessHour);
			break;
					
		case CONTACT:
			
			Optional<Ido> ido = idoRepository.findById(id);
			if(ido.isEmpty()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
			}

			List<IdoContact> contacts = idoContactRepository.findIdoContactByIdo(ido.get());
			for(IdoContact contact : contacts) {
				contact.setActivated(activatedRequest.getActivated());
				idoContactRepository.save(contact);
			}

			IdoContact model = new IdoContact();
			model.setActivated(activatedRequest.getActivated());
			response = mapper.responseToContact(model);
			break;
		
		case CONTACTUS:
			
			ConfigContactUs configContactUs = configContactUsRepository.findById(id)
			.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"ConfigContactUs", "api.error.contact.us.not.found"));
			configContactUs.setActivated(activatedRequest.getActivated());
			configContactUsRepository.save(configContactUs);
			response = mapper.responseToConfigContactUs(configContactUs);
			
			break;
		
		case FAQ:
			
			ConfigFaq configFaq = configFaqRepository.findById(id)
			.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"ConfigFAQ", "api.error.config.faq.not.found"));
			configFaq.setActivated(activatedRequest.getActivated());
			configFaqRepository.save(configFaq);
			response = mapper.responseToConfigFaq(configFaq);
			
			break;
			
		case IMAGEBANNER:
			
			ImageBanner imageBanner = imageBannerRepository.findById(id)
			.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"ImageBanner", "api.error.image.banner.not.found"));
			imageBanner.setActivated(activatedRequest.getActivated());
			imageBannerRepository.save(imageBanner);
			response = mapper.responseToImageBanner(imageBanner);
			
			break;
			
		case IMAGECAROUSEL:
			
			ImageCarousel imageCarousel = imageCarouselRepository.findById(id)
			.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"ImageCarousel", "api.error.image.carousel.not.found "));
			imageCarousel.setActivated(activatedRequest.getActivated());
			imageCarouselRepository.save(imageCarousel);
			response = mapper.responseToImageCarousel(imageCarousel);
			
			break;
			
		case LINK:
			Link link =  linkRepository.findById(id)
			.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"Link", "api.error.link.not.found"));
			link.setActivated(activatedRequest.getActivated());
			linkRepository.save(link);
			response = mapper.responseToLink(link);
			break;
			
		case NEWSLETTER:
			
			ConfigNewsletterForm configNewsletterForm = configNewsletterFormRepository.findById(id)
			.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"NewsLetter", "api.error.config.newsletter.not.found"));
			configNewsletterForm.setActivated(activatedRequest.getActivated());
			configNewsletterFormRepository.save(configNewsletterForm);
			response = mapper.responseToConfigNewsletterForm(configNewsletterForm);
			break;
			
		case PDF:
			
			AttachedPdf attachedPdf = attachedPdfRepository.findById(id)
			.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"pdf", "api.error.pdf.not.found"));
			attachedPdf.setActivated(activatedRequest.getActivated());
			attachedPdfRepository.save(attachedPdf);
			response = mapper.responseToAttachedPdf(attachedPdf);
			break;
			
		case VIDEOBANNER:
		
			VideoBanner videoBanner = videoBannerRepository.findById(id)
			.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"BannerVideo", "api.error.video.banner.not.found"));
			videoBanner.setActivated(activatedRequest.getActivated());
			videoBannerRepository.save(videoBanner);
			response = mapper.responseToVideoBanner(videoBanner);
			break;
			
		case SHOP:
			Shop shoop = shopRepository.findById(id)
			.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"shop", "api.error.shop.not.found"));
			shoop.setActivated(activatedRequest.getActivated());
			shopRepository.save(shoop);
			response = mapper.responseToShop(shoop);
			break;
			
		case HTML:
			
			EmbedHtml embedHtml = embedHtmlRepository.findById(id)
			.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"EmbedHtml", "api.error.embed.html.not.found"));
			embedHtml.setActivated(activatedRequest.getActivated());
			embedHtmlRepository.save(embedHtml);
			response =	mapper.responseToEmbedHtml(embedHtml);
					
			break;
				
		case LOGOBIO:
			
			LogoBio logoBio = logoBioRepository.findById(id)
			.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"LogoBio", "api.error.logo.bio.not.found"));
			logoBio.setActivated(activatedRequest.getActivated());
			logoBioRepository.save(logoBio);
			response = mapper.responseToLogoBio(logoBio);
			
			break;
			
		case MENURODAPE:
			
			MenuFooter menuFooter = menuFooterRepository.findById(id)
			.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"MenuFooter", "api.error.menu.footer.not.found"));
			menuFooter.setActivated(activatedRequest.getActivated()); 
			menuFooterRepository.save(menuFooter);
			response = mapper.responseToMenuFooter(menuFooter);
			
			break;		
			
		default:
			break;
		}
		return response;
	}
	
	
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
	
	/**
	 * verifica se o formato da versao (ex: 0.0.1) está correto
	 * @param appVersion
	 */
	private void validateAppVersion(String appVersion) {
		
		String[]splitAppVersion = appVersion.split("\\.");
		
		if(splitAppVersion.length != 3) {
			throw new BaseFullException(HttpStatus.CONFLICT,"version", "api.error.tool.version");
		}
		
		for (String string : splitAppVersion) {
			if(!NumberUtils.isNumber(string) || Integer.valueOf(string) < 0){
				throw new BaseFullException(HttpStatus.CONFLICT,"version", "api.error.tool.version");
			}
		}
	}
	
	
		
}
