package br.com.idolink.api.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.config.security.IdolinkSecurity;
import br.com.idolink.api.dto.request.AccessReportRequest;
import br.com.idolink.api.dto.response.AccessReportResponse;
import br.com.idolink.api.dto.response.ClicksToolsResponse;
import br.com.idolink.api.dto.response.VisitorsClicksResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.AccessReportMapper;
import br.com.idolink.api.model.AccessReport;
import br.com.idolink.api.model.AttachedPdf;
import br.com.idolink.api.model.Business;
import br.com.idolink.api.model.BusinessHour;
import br.com.idolink.api.model.ConfigContactUs;
import br.com.idolink.api.model.ConfigFaq;
import br.com.idolink.api.model.GeneralSettings;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.ImageBanner;
import br.com.idolink.api.model.ImageCarousel;
import br.com.idolink.api.model.Link;
import br.com.idolink.api.model.NewsletterForm;
import br.com.idolink.api.model.Shop;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.VideoBanner;
import br.com.idolink.api.model.enums.IdoCodLanguage;
import br.com.idolink.api.model.enums.ReportPeriod;
import br.com.idolink.api.repository.AccessReportRepository;
import br.com.idolink.api.repository.AttachedPdfRepository;
import br.com.idolink.api.repository.BusinessHourRepository;
import br.com.idolink.api.repository.BusinessRepository;
import br.com.idolink.api.repository.ConfigContactUsRepository;
import br.com.idolink.api.repository.ConfigFaqRepository;
import br.com.idolink.api.repository.GeneralSettingsRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.ImageBannerRepository;
import br.com.idolink.api.repository.ImageCarouselRepository;
import br.com.idolink.api.repository.LinkRepository;
import br.com.idolink.api.repository.NewsletterFormRepository;
import br.com.idolink.api.repository.ShopRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.repository.VideoBannerRepository;
import br.com.idolink.api.service.AccessReportService;
import br.com.idolink.api.utils.IdoStringUtils;

@Service
public class AccessReportServiceImpl implements AccessReportService {

	@Autowired
	private AccessReportMapper mapper;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private IdolinkSecurity idoLinkSecurity;

	@Autowired
	private AccessReportRepository repository;

	@Autowired
	private IdoRepository idoRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BusinessRepository businessRepository;

	@Autowired
	private BusinessHourRepository businessHourRepository;

	@Autowired
	private ConfigContactUsRepository configContactUsRepository;

	@Autowired
	private ConfigFaqRepository faqRepository;

	@Autowired
	private ImageBannerRepository imageRepository;

	@Autowired
	private VideoBannerRepository videoRepository;

	@Autowired
	private ImageCarouselRepository imageCarouselRepository;

	@Autowired
	private LinkRepository linkRepository;

	@Autowired
	private NewsletterFormRepository newsletterFormRepository;

	@Autowired
	private AttachedPdfRepository attachedPdfRepository;

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private GeneralSettingsRepository repositoryGeneral;
	
	@Autowired
	private IdoRepository repositoryIdo;
	
	
	
	@Override
	public VisitorsClicksResponse canSendReportEmailWeek(Long id, Boolean ReportEmail) {

		GeneralSettings model = repositoryGeneral.findByUserId(id);
      
		if (model != null) {
			model.setReceiveReportEmailWeek(ReportEmail);

			model = repositoryGeneral.save(model);
			
			List<Long> ido = repositoryIdo.findIdByUser(id);
			

			return calculateVisitorsAndCliscks(id, ido , ReportPeriod.WEEK);
		}

		throw new BaseFullException(HttpStatus.NOT_FOUND, "GeneralSettings",
				"api.error.general.settings.not.found");

	}
	
	@Override
	@Transactional
	public AccessReportResponse create(AccessReportRequest request) {
		
		Optional<Ido> ido = idoRepository.findByIdUserFilter(request.getIdo());	
		validate(ido, "Ido", "api.error.ido.not.found");
				
		AccessReport model = mapper.model(request);
		model.setIdo(ido.get());
		model.setGenericToolId(request.getGenericToolsId());
		model.setAccessDate(LocalDate.now());
		model = repository.save(model);
		return mapper.response(model);
	}
	
	public VisitorsClicksResponse getVisitorsAndCliscks(Long userId, String idos, ReportPeriod period) {
		
	
		if(idos.isBlank() || idos == null) {
			
			List<Long> idoIds = null;
			return calculateVisitorsAndCliscks(userId,idoIds, period);
			
		} else {
			
			List<Long> idoIds = IdoStringUtils.convertStringLong(idos);
			return calculateVisitorsAndCliscks(userId,idoIds, period);
		}
		

		}
	
	
	private VisitorsClicksResponse calculateVisitorsAndCliscks(Long userId, List<Long> idoIds, ReportPeriod period) {
			
		Optional<User> user = userRepository.findById(userId);	
		validate(user, "Usuario", "api.error.user.not.found");
		
		List<ClicksToolsResponse> clickToolsDefault = new ArrayList<>();
		
	   Business business = businessRepository.findByUser(user.get());  
	   
	   if(business == null) {
		   
		   return  VisitorsClicksResponse.builder()
					.totalVisitors(0L)
					.distinctTotalVisitors(0L)
					.clicks(0L)
					.convertedClicks(0F)
					.reportEmailWeek(false)
					.clickTools(clickToolsDefault)
					.conversionTax(0F).build();
		   
	   }
		
		GeneralSettings GeneralUser = repositoryGeneral.findByUserId(userId);
		validate(GeneralUser,"UsuarioGeneral","api.error.general.settings.not.found");
				
		if(Objects.isNull(idoIds)) {
			idoIds = idoRepository.findIdByBusiness(business.getId()); 
		}

		LocalDate  startDate = LocalDate.now();
		LocalDate  endDate =  LocalDate.now();
										
		if(Objects.nonNull(period)){
			
			if(period.equals(ReportPeriod.YEAR)) {
				startDate = startDate.withMonth(1).withDayOfMonth(1);
				endDate = endDate.withMonth(12).withDayOfMonth(31);
			}else if(period.equals(ReportPeriod.WEEK)) {
				
				   startDate = startDate.with(ChronoField.DAY_OF_WEEK, 1);
				   endDate = endDate.with(ChronoField.DAY_OF_WEEK, 7);
				
				}else {
				startDate = startDate.withDayOfMonth(1);
				endDate = endDate.withDayOfMonth(endDate.lengthOfMonth());
			}
			
		}else {
			startDate = startDate.minusYears(2);
			
		}
				
		Long visitors = repository.getTotalVisitors(idoIds, startDate, endDate);
		Long distinctVisitors = repository.getDistinctTotalVisitors(idoIds, startDate, endDate);
		Long clicks = repository.getTotalClicks(idoIds, startDate, endDate);
		Float convertedClicks = 0f;
		Float conversionTax = 0f;
				
		if(clicks != 0) {
			convertedClicks = (float)(visitors/clicks);
		}
		
		if(visitors != 0) {
			conversionTax  = (float) ((clicks * 100) / visitors);
		}
		
		List<ClicksToolsResponse> reports = repository.findClicksToolsGroupBy(idoIds, startDate, endDate);
		
		clicksTools(reports);
		
		if(idoIds.isEmpty()){
			
			return  VisitorsClicksResponse.builder()
					.totalVisitors(0L)
					.distinctTotalVisitors(0L)
					.clicks(0L)
					.convertedClicks(0F)
					.reportEmailWeek(false)
					.clickTools(clickToolsDefault)
					.conversionTax(0F).build();

			
		}
		
		return VisitorsClicksResponse.builder().totalVisitors(visitors)
				.distinctTotalVisitors(distinctVisitors)
				.clicks(clicks)
				.convertedClicks(convertedClicks)
				.reportEmailWeek(GeneralUser.getReceiveReportEmailWeek())
				.conversionTax(conversionTax).clickTools(reports).build();
		
		 
		
			

			
		
						
	}
	@Override
	@Transactional
	public void delete(Long id) {
		
		Optional<AccessReport> report = repository.findById(id);
		validate(report, "report", "api.error.report.not.found");
		
		try {
			repository.delete(report.get());
			repository.flush();
		} catch(DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Ido", "api.error.report.conflict");
		}
		
	}
			
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
	private void validate(GeneralSettings generalUser, String field, String message) {

		if (generalUser == null) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
	private void clicksTools(List<ClicksToolsResponse> reports){
		
			
		Long sum = reports.stream().mapToLong(x -> x.getTotalVisitors()).sum();
				
		for (ClicksToolsResponse accessReport : reports) {
		
			accessReport.setPercentsVisitors(accessReport.getTotalVisitors()*100/sum);
			
			switch(accessReport.getToolCodName()) {
			
				case BUSINESSHOUR:
					
					Optional<BusinessHour> businessHour = businessHourRepository.findById(accessReport.getGenericToolsId());
					
					if(businessHour.isPresent()) {
						accessReport.setDescription(getMessageProperties("api.model.business.hour") + "-" + businessHour.get().getTitle());
					}
					
					
															
				break;
				
				case CONTACT:
					
					Optional<Ido> ido = idoRepository.findById(accessReport.getGenericToolsId());
					
					if(ido.isPresent()) {
						accessReport.setDescription(getMessageProperties("api.model.contact") + "-" + ido.get().getName());
					}
					
					
				break;
				
				case CONTACTUS:
					
					Optional<ConfigContactUs> configCOptional = configContactUsRepository.findById(accessReport.getGenericToolsId());
					
					if(configCOptional.isPresent()) {
						accessReport.setDescription(getMessageProperties("api.model.contact.us") + "-" + configCOptional.get().getTitle());
					}
										
				break;
				
				case FAQ:
					
					Optional<ConfigFaq> faqOptional = faqRepository.findById(accessReport.getGenericToolsId());
					
					if(faqOptional.isPresent()) {
						accessReport.setDescription(getMessageProperties("api.model.faq") + "-" + faqOptional.get().getTitle());
					}
					
				break;
				
				case IMAGEBANNER:
					
					Optional<ImageBanner> imageOptional = imageRepository.findById(accessReport.getGenericToolsId());
					
					if(imageOptional.isPresent()) {
						accessReport.setDescription(getMessageProperties("api.model.image.banner") + "-"  + imageOptional.get().getTitle());
					}					
					
				break;
				
				case IMAGECAROUSEL:
					Optional<ImageCarousel> caroOptional = imageCarouselRepository.findById(accessReport.getGenericToolsId());
					
					if(caroOptional.isPresent()) {
						accessReport.setDescription(getMessageProperties("api.model.image.carousel") + "-"  + caroOptional.get().getTitle());
					}
				break;
				
				case LINK:
					Optional<Link> linkOptional = linkRepository.findById(accessReport.getGenericToolsId());
					
					if(linkOptional.isPresent()) {
						accessReport.setDescription(getMessageProperties("api.model.link") + "-" + linkOptional.get().getTitle());
					}
				break;
				
				case NEWSLETTER:
					Optional<NewsletterForm> newsLeOptional = newsletterFormRepository.findById(accessReport.getGenericToolsId());
					
					if(newsLeOptional.isPresent()) {
						accessReport.setDescription(getMessageProperties("api.model.newsletter") + "-"  + newsLeOptional.get().getName());
					}
				break;
				
				case PDF:
					Optional<AttachedPdf> attaOptional = attachedPdfRepository.findById(accessReport.getGenericToolsId());
					
					if(attaOptional.isPresent()) {
						accessReport.setDescription(getMessageProperties("api.model.pdf") + "-"  + attaOptional.get().getTitle());
					}
				break;
				
				case VIDEOBANNER:
					Optional<VideoBanner> videoOptional = videoRepository.findById(accessReport.getGenericToolsId());
					
					if(videoOptional.isPresent()) {
						accessReport.setDescription(getMessageProperties("api.model.video.banner") + "-"  + videoOptional.get().getTitle());
					}
				break;
											
				case SHOP:
					Optional<Shop> shopOptional = shopRepository.findById(accessReport.getGenericToolsId());
					
					if(shopOptional.isPresent()) {
						accessReport.setDescription(getMessageProperties("api.model.shop") + "-"  + shopOptional.get().getName());
					}
				break;
				default:
				break;
					
			}
												
		}
							
	}

	private String getMessageProperties(String propertie) {
		String languagem = Objects.nonNull(idoLinkSecurity) && Objects.nonNull(idoLinkSecurity.getLanguage()) ? IdoCodLanguage.getCod(idoLinkSecurity.getLanguage()) : "pt";
		return messageSource.getMessage(propertie, null, Locale.forLanguageTag(languagem));
	}
	
	@Override
	public String validateIsNull(String ido) {
		
		if(ido == null ) {
			
			ido = "";
			
		} 
		
		return ido;
		
	}
		
}
