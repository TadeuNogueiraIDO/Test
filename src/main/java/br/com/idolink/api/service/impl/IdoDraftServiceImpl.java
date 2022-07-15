package br.com.idolink.api.service.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.BusinessHourAlternativeScheduleRequest;
import br.com.idolink.api.dto.request.BusinessHourDayRequest;
import br.com.idolink.api.dto.request.BusinessHourRequest;
import br.com.idolink.api.dto.request.BusinessHourScheduleRequest;
import br.com.idolink.api.dto.request.IdoToolPositionRequest;
import br.com.idolink.api.dto.request.LinkRequest;
import br.com.idolink.api.dto.response.BusinessHourAlternateScheduleResponse;
import br.com.idolink.api.dto.response.BusinessHourDayResponse;
import br.com.idolink.api.dto.response.BusinessHourResponse;
import br.com.idolink.api.dto.response.BusinessHourScheduleResponse;
import br.com.idolink.api.dto.response.IdoResponse;
import br.com.idolink.api.dto.response.LinkResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.IdoMapper;
import br.com.idolink.api.model.AppearanceButton;
import br.com.idolink.api.model.AppearanceCards;
import br.com.idolink.api.model.AppearanceText;
import br.com.idolink.api.model.AttachedPdf;
import br.com.idolink.api.model.AttachedPdfLeads;
import br.com.idolink.api.model.Category;
import br.com.idolink.api.model.ConfigContactUs;
import br.com.idolink.api.model.ConfigFaq;
import br.com.idolink.api.model.ConfigNewsletterForm;
import br.com.idolink.api.model.EmbedHtml;
import br.com.idolink.api.model.Faq;
import br.com.idolink.api.model.FormContactUs;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoContact;
import br.com.idolink.api.model.IdoToolPosition;
import br.com.idolink.api.model.ImageBanner;
import br.com.idolink.api.model.ImageCarousel;
import br.com.idolink.api.model.ImageCarouselItem;
import br.com.idolink.api.model.ImageCarouselItemDetail;
import br.com.idolink.api.model.LogoBio;
import br.com.idolink.api.model.MenuFooter;
import br.com.idolink.api.model.NewsletterForm;
import br.com.idolink.api.model.Shop;
import br.com.idolink.api.model.VideoBanner;
import br.com.idolink.api.model.enums.IdoStatus;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.AppearanceButtonRepository;
import br.com.idolink.api.repository.AppearanceCardsRepository;
import br.com.idolink.api.repository.AppearanceTextRepository;
import br.com.idolink.api.repository.AttachedPdfLeadsRepository;
import br.com.idolink.api.repository.AttachedPdfRepository;
import br.com.idolink.api.repository.CategoryRepository;
import br.com.idolink.api.repository.ConfigContactUsRepository;
import br.com.idolink.api.repository.ConfigFaqRepository;
import br.com.idolink.api.repository.ConfigNewsletterFormRepository;
import br.com.idolink.api.repository.EmbedHtmlRepository;
import br.com.idolink.api.repository.FaqRepository;
import br.com.idolink.api.repository.FormContactUsRepository;
import br.com.idolink.api.repository.IdoContactRepository;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.ImageBannerRepository;
import br.com.idolink.api.repository.ImageCarouselRepository;
import br.com.idolink.api.repository.LogoBioRepository;
import br.com.idolink.api.repository.MenuFooterRepository;
import br.com.idolink.api.repository.NewsletterFormRepository;
import br.com.idolink.api.repository.ShopRepository;
import br.com.idolink.api.repository.VideoBannerRepository;
import br.com.idolink.api.service.BusinessHourService;
import br.com.idolink.api.service.IdoDraftService;
import br.com.idolink.api.service.IdoService;
import br.com.idolink.api.service.IdoToolPositionService;
import br.com.idolink.api.service.LinkService;

@Service
public class IdoDraftServiceImpl extends GenericToolsServiceImpl implements IdoDraftService {

	@Autowired
	private IdoMapper mapper;

	@Autowired
	private IdoRepository idoRepository;
	
	@Autowired
	private BusinessHourService businessHourService;
	
	@Autowired
	private ConfigContactUsRepository configContactUsRepository;
	
	@Autowired
	private FormContactUsRepository formContactUsRepository;
	
	@Autowired
	private IdoContactRepository idoContactRepository;
	
	@Autowired
	private ConfigFaqRepository configFaqRepository;
	
	@Autowired
	private FaqRepository faqRepository;
	
	@Autowired
	private ImageBannerRepository imageBannerRepository;
	
	@Autowired
	private VideoBannerRepository videoBannerRepository;
	
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private ConfigNewsletterFormRepository configNewsletterFormRepository;
	
	@Autowired
	private NewsletterFormRepository newsletterFormRepository;
	
	@Autowired
	private AttachedPdfRepository attachedPdfRepository;
	
	@Autowired
	private AttachedPdfLeadsRepository attachedPdfLeadsRepository;
	
	@Autowired
	private AppearanceCardsRepository appearanceCardsRepository;
	
	@Autowired
	private AppearanceButtonRepository appearanceButtonRepository;
	
	@Autowired
	private AppearanceTextRepository appearanceTextRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ImageCarouselRepository imageCarouselRepository;
	
	@Autowired
	private EmbedHtmlRepository embedHtmlRepository;
	
	@Autowired
	private MenuFooterRepository menuFooterRepository;
	
	@Autowired
	private LogoBioRepository logoBioRepository;
	
	@Autowired
	private IdoToolPositionService idoToolPositionService;
	
	@Autowired
	private IdoService idoService;
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Override
	@Transactional
	public IdoResponse loadDraftIdo(Long idoId) {
		
		Optional<Ido> idoOp =  idoRepository.findByIdUserFilter(idoId);
		validate(idoOp, "Ido","api.error.ido.not.found");
		
		Ido ido = idoOp.get();
		
		//se o ido buscado ainda esta em rascunho ou inative retorna o proprio ido
		if(!ido.getStatus().equals(IdoStatus.PUBLISHED)) {
			
			Optional<Ido> idoDraft = idoRepository.findByIdoPublished(idoId);
			
			if(idoDraft.isPresent()) {
				return mapper.response(idoDraft.get(), true);
			}
			
			return mapper.response(ido, true);
		}
		
		//Se eh um ido publicado, verifica se o ido tem algum Ido Rascunho 
		Optional<Ido> idoDraftOld = idoRepository.findByIdoPublished(idoId);
		if(idoDraftOld.isPresent()) {
			return mapper.response(idoDraftOld.get(),true);
		}
				
		// Se nao tiver rascunho, cria um novo ido,que eh uma copia do ido publicado, mas com status rascunho
		Ido idoDraftNew = cloneIdo(ido);
		idoDraftNew.setStatus(IdoStatus.DRAFT);
		idoDraftNew.setIdoPublished(idoId);
		idoDraftNew = idoRepository.save(idoDraftNew);
		return mapper.response(idoDraftNew, true);
				
	}
	
	@Override
	@Transactional
	public IdoResponse loadDraftByIdoPublic(String domain) {
		
		Optional<Ido> ido =  idoRepository.findDraftByDomain(domain);
		validate(ido, "Ido","api.error.ido.not.found");

		IdoResponse response = mapper.modelResponse(ido.get(), true);
		response.setContacts(idoService.getPublicContact(response.getId())); 
		response = idoService.addToolsPositions(response);
		return response;
				
	}
	
	@Override
	@Transactional
	public IdoResponse publishIdo(Long idoPublishedId) {
		
		Optional<Ido> idoOp =  idoRepository.findByIdUserFilter(idoPublishedId);
		validate(idoOp, "Ido","api.error.ido.not.found");
		
		Ido ido = idoOp.get();
		ido.setStatus(IdoStatus.PUBLISHED);		
		
		//Se o ido publicado eh um rascunho de algum outro ido, o ido original eh excluido
		if(Objects.nonNull(ido.getIdoPublished()) && ido.getIdoPublished() != 0) {
			Optional<Ido> idoOld =  idoRepository.findByIdUserFilter(ido.getIdoPublished());
			validate(idoOld, "Ido", "api.error.ido.not.found");
			
			//verifica se o ido antigo tem loja.Caso positivo, a loja precisa ser enviada para o ido que sera publicado
			Optional<Shop> shop = shopRepository.findByIdo(idoOld.get());
			if(shop.isPresent()) {
				idoOld.get().setShop(null);
				shop.get().setIdo(ido);
				shopRepository.save(shop.get());
			}
						
			idoRepository.deleteById(ido.getIdoPublished());	
		}
		
		ido.setIdoPublished(null);
		ido = idoRepository.save(ido);
				
		return mapper.response(ido, true);
	}
	
	private Ido cloneIdo(Ido idoOrigem) {
		
		Ido idoDraft = new Ido();
		BeanUtils.copyProperties(idoOrigem, idoDraft, "id","dateModel", "idoPublished");
			
		List<Category> categories = new ArrayList<>();
		
		idoOrigem.getCategories().stream().forEach(
				category -> categories.add(categoryRepository.findById(category.getId()).get()));				
		
		idoDraft.setCategories(categories);
		idoDraft.setIdoPublished(idoOrigem.getId());
		
		//salva o rascunho no banco de dados
		idoDraft = idoRepository.save(idoDraft);
				
		cloneBusinessHour(idoOrigem,idoDraft);
		cloneConfigContactUs(idoOrigem,idoDraft);
		cloneConfigFaq(idoOrigem, idoDraft);
		cloneImageBanner(idoOrigem, idoDraft); 
		cloneVideoBanner(idoOrigem, idoDraft); 
		cloneLink(idoOrigem, idoDraft);
		cloneConfigNewsLetter(idoOrigem, idoDraft);
		cloneAppearanceButton(idoOrigem, idoDraft);
		cloneAppearanceCard(idoOrigem, idoDraft);
		cloneAppearanceText(idoOrigem, idoDraft);
		cloneAttachedPdf(idoOrigem, idoDraft);
		cloneImageCarousel(idoOrigem, idoDraft);
		cloneIdoContact(idoOrigem, idoDraft);
		cloneEmbedHtml(idoOrigem, idoDraft);
		cloneMenuFooter(idoOrigem, idoDraft);
		cloneLogoBio(idoOrigem, idoDraft);
		return idoDraft;
		
	}
	
	//START CLONE TOOLS
	
	//copia o horario de funcionamento para o rascunho
	@Transactional
	private void cloneBusinessHour(Ido idoOrigem, Ido idoDraft) {
				
		BusinessHourResponse businessHourResponse = businessHourService.findByIdo(idoOrigem.getId(), false);
		
		if(Objects.nonNull(businessHourResponse)) {
				
				BusinessHourRequest bHNews = BusinessHourRequest.builder()
						.title(businessHourResponse.getTitle())
						.type(businessHourResponse.getType())
						.timeFormat(businessHourResponse.getTimeFormat())
						.icon(Objects.nonNull(businessHourResponse.getIcon()) ? businessHourResponse.getIcon().getId(): null)
						.typeicon(businessHourResponse.getTypeicon()).build();
				
				List<BusinessHourDayRequest> days = new ArrayList<>();
								
				for (BusinessHourDayResponse dayOld : businessHourResponse.getDays()) {
					
					BusinessHourDayRequest dayNew = BusinessHourDayRequest.builder()
							.day(dayOld.getDay())
							.enabled(dayOld.getEnabled()).build();
					
					List<BusinessHourScheduleResponse> schedulesDayOlds = dayOld.getSchedules();
					List<BusinessHourScheduleRequest> schedulesDayNew = new ArrayList<>();
					
					for (BusinessHourScheduleResponse schOld : schedulesDayOlds) {
			
						schedulesDayNew.add(BusinessHourScheduleRequest.builder()
								.openTime(schOld.getOpenTime())
								.closeTime(schOld.getCloseTime()).build());
					}
					
					dayNew.setSchedules(schedulesDayNew);
					days.add(dayNew);

				List<BusinessHourAlternativeScheduleRequest> alternatives = new ArrayList<>();
				
				for (BusinessHourAlternateScheduleResponse alternativeOld : businessHourResponse.getAlternativeSchedules()) {
					
					BusinessHourAlternativeScheduleRequest alternativeNew = BusinessHourAlternativeScheduleRequest.builder()
							.day(alternativeOld.getDay())
							.isAlert(alternativeOld.getIsAlert())
							.messageAlert(alternativeOld.getMessageAlert())
							.closed(alternativeOld.getClosed()).build();
					
					List<BusinessHourScheduleResponse> schedulesAltOlds = alternativeOld.getSchedules();
					List<BusinessHourScheduleRequest> schedulesAltNew = new ArrayList<>();
					
					for (BusinessHourScheduleResponse schOld : schedulesAltOlds) {
						
						schedulesAltNew.add(BusinessHourScheduleRequest.builder()
								.openTime(schOld.getOpenTime())
								.closeTime(schOld.getCloseTime())
								.build());
					}
					
					alternativeNew.setSchedules(schedulesAltNew);
									
					alternatives.add(alternativeNew);
				}
				
				bHNews.setDays(days);				
				bHNews.setAlternativeSchedules(alternatives);
				BusinessHourResponse newResponse =  businessHourService.create(idoDraft.getId(), bHNews);	
				
				updatePositionTool(idoOrigem.getId(), idoDraft.getId(), businessHourResponse.getId(), newResponse.getId(), ToolCodName.BUSINESSHOUR);
				
			}
		}
	}
	
	//copia as configuracoes de fale conosco
	@Transactional
	private void cloneConfigContactUs(Ido idoOrigem, Ido idoDraft) {
	
		Optional<ConfigContactUs> configContactUs = configContactUsRepository.findByIdo(idoOrigem);
		
		if(configContactUs.isPresent()) {
			
			ConfigContactUs contactNews = new ConfigContactUs();
			BeanUtils.copyProperties(configContactUs.get(), contactNews, "id","dateModel", "ido");
			contactNews.setIdo(idoDraft);
			contactNews = configContactUsRepository.save(contactNews);			
						
			List<FormContactUs> formContacts = formContactUsRepository.findByConfigContactUs(configContactUs.get());
			
			if(Objects.nonNull(formContacts) && !formContacts.isEmpty()) {
				//copia o formulario de fale conosco ja recebindo para o rascunho
				for (FormContactUs formContactUs : formContacts) {
					FormContactUs formNew = new FormContactUs();
					BeanUtils.copyProperties(formContactUs, formNew, "id","dateModel", "configContactUs");
					formNew.setConfigContactUs(contactNews);
					formContactUsRepository.save(formNew);				
				}
			}
			super.createTools(ToolCodName.CONTACTUS, idoDraft.getId(),contactNews.getId());	
			updatePositionTool(idoOrigem.getId(), idoDraft.getId(), configContactUs.get().getId(), contactNews.getId(), ToolCodName.CONTACTUS);
			
			
		}
	}
	
	//copia os contatos do ido para o rascunho
	@Transactional
	private void cloneIdoContact(Ido idoOrigem, Ido idoDraft) {
				
		List<IdoContact> idoContacts = idoContactRepository.findIdoContactByIdo(idoOrigem);
		
		if(Objects.nonNull(idoContacts) && !idoContacts.isEmpty()) {
			
			for (IdoContact idoContact : idoContacts) {
				
				IdoContact newContact = new IdoContact();
				BeanUtils.copyProperties(idoContact, newContact, "id","dateModel", "ido");
				newContact.setIdo(idoDraft);
				idoContactRepository.save(newContact);
			}
			
			super.createTools(ToolCodName.CONTACT, idoDraft.getId(), idoDraft.getId());
			updatePositionTool(idoOrigem.getId(), idoDraft.getId(), idoOrigem.getId(), idoDraft.getId(), ToolCodName.CONTACT);
			
		}
		
		
		
	}
	
	//copia as configuracoes de fale conosco
	@Transactional
	private void cloneConfigFaq(Ido idoOrigem, Ido idoDraft) {
	
		Optional<ConfigFaq> configFaq = configFaqRepository.findByIdo(idoOrigem);
		
		if(configFaq.isPresent()) {
			
			ConfigFaq configNew = new ConfigFaq();
			BeanUtils.copyProperties(configFaq.get(), configNew, "id","dateModel", "ido");
			configNew.setIdo(idoDraft);
			configNew = configFaqRepository.save(configNew);			
						
			List<Faq> faqs = faqRepository.findByConfigFaq(configFaq.get());
			
			if(Objects.nonNull(faqs) && !faqs.isEmpty()) {
				//copia o formulario de fale conosco ja recebindo para o rascunho
				for (Faq faq : faqs) {
					Faq faqNew = new Faq();
					BeanUtils.copyProperties(faq, faqNew, "id","dateModel", "configFaq");
					faqNew.setConfigFaq(configNew);
					faqRepository.save(faqNew);				
				}
			}
			
			super.createTools(ToolCodName.FAQ, idoDraft.getId(), configNew.getId());
			updatePositionTool(idoOrigem.getId(), idoDraft.getId(), configFaq.get().getId(), configNew.getId(), ToolCodName.FAQ);
		}
		
		
	}
	
	//copia os banners de imagem para o rascunho
	@Transactional
	private void cloneImageBanner(Ido idoOrigem, Ido idoDraft) {
				
		List<ImageBanner> images = imageBannerRepository.findByIdo(idoOrigem.getId());
		
		if(Objects.nonNull(images) && !images.isEmpty()) {
			
			for (ImageBanner imageBanner : images) {
				
				ImageBanner newImage = new ImageBanner();
				BeanUtils.copyProperties(imageBanner, newImage, "id","dateModel", "ido");
				newImage.setIdo(idoDraft);
				newImage= imageBannerRepository.save(newImage);
				super.createTools(ToolCodName.IMAGEBANNER, idoDraft.getId(), newImage.getId());
				updatePositionTool(idoOrigem.getId(), idoDraft.getId(), imageBanner.getId(), newImage.getId(), ToolCodName.IMAGEBANNER);
			}
		}
	}
	
	//copia os banners de video para o rascunho
	@Transactional
	private void cloneVideoBanner(Ido idoOrigem, Ido idoDraft) {
				
		List<VideoBanner> videos = videoBannerRepository.findByIdo(idoOrigem.getId());
		
		if(Objects.nonNull(videos) && !videos.isEmpty()) {
			
			for (VideoBanner videoBanner : videos) {
				
				VideoBanner newVideo = new VideoBanner();
				BeanUtils.copyProperties(videoBanner, newVideo, "id","dateModel", "ido");
				newVideo.setIdo(idoDraft);
				newVideo = videoBannerRepository.save(newVideo);	
				super.createTools(ToolCodName.VIDEOBANNER, idoDraft.getId(), newVideo.getId());
				updatePositionTool(idoOrigem.getId(), idoDraft.getId(), videoBanner.getId(), newVideo.getId(), ToolCodName.VIDEOBANNER);
			}
		}
	}
	
	//copia os links para o rascunho
	@Transactional
	private void cloneLink(Ido idoOrigem, Ido idoDraft) {
				
		List<LinkResponse> links = linkService.listByIdo(idoOrigem.getId());
		
		if(Objects.nonNull(links) && !links.isEmpty()) {
			
			for (LinkResponse link : links) {
				
				LinkRequest newLink = LinkRequest.builder()
						.subtitle(link.getSubtitle())
						.title(link.getTitle())
						.url(link.getUrl())
						.typeicon(link.getTypeicon()).build();
				
				if(Objects.nonNull(link.getIcon())){
					newLink.setIcon(link.getIcon().getId());
				}
				
				LinkResponse newResponse = linkService.create(idoDraft.getId(), newLink);	
				updatePositionTool(idoOrigem.getId(), idoDraft.getId(), link.getId(), newResponse.getId(), ToolCodName.LINK);
			}
		}
	}
	
	
	//copia as configuracoes de pdf
	@Transactional
	private void cloneAttachedPdf(Ido idoOrigem, Ido idoDraft) {
	
		List<AttachedPdf> pdfs = attachedPdfRepository.findByIdo(idoOrigem);
		
		if(Objects.nonNull(pdfs) && !pdfs.isEmpty()) {
			
			for (AttachedPdf attachedPdf : pdfs) {
				
				AttachedPdf pdfNews = new AttachedPdf();
				BeanUtils.copyProperties(attachedPdf, pdfNews, "id","dateModel", "ido");
				pdfNews.setIdo(idoDraft);
				pdfNews = attachedPdfRepository.save(pdfNews);	
				
				List<AttachedPdfLeads> leads = attachedPdfLeadsRepository.findByAttachedPdf(attachedPdf);
				
				if(Objects.nonNull(leads) && !leads.isEmpty()) {
					
					for (AttachedPdfLeads lead : leads) {
						AttachedPdfLeads leadNew = new AttachedPdfLeads();
						BeanUtils.copyProperties(lead, leadNew, "id","dateModel", "attachedPdf");
						leadNew.setAttachedPdf(pdfNews);
						attachedPdfLeadsRepository.save(leadNew);
					}
				}
				super.createTools(ToolCodName.PDF, idoDraft.getId(), pdfNews.getId());
				updatePositionTool(idoOrigem.getId(), idoDraft.getId(), attachedPdf.getId(), pdfNews.getId(), ToolCodName.PDF);
			}
		}
	}
	
	//copia as configuracoes de menu e rodapé
	@Transactional
	private void cloneMenuFooter(Ido idoOrigem, Ido idoDraft) {
		
		Optional<MenuFooter> menuFooter = menuFooterRepository.findByIdo(idoOrigem);
		
		if(Objects.nonNull(menuFooter) && !menuFooter.isEmpty()) {
				
			MenuFooter menu = new MenuFooter();
			BeanUtils.copyProperties(menuFooter.get(), menu, "id","dateModel", "ido");
			menu.setIdo(idoDraft);
			menu = menuFooterRepository.save(menu);	
		
			super.createTools(ToolCodName.MENURODAPE, idoDraft.getId(), menu.getId());
			updatePositionTool(idoOrigem.getId(), idoDraft.getId(), menuFooter.get().getId(), menu.getId(), ToolCodName.MENURODAPE);
		}
	}
	
	//copia as configuracoes de fale conosco
	@Transactional
	private void cloneConfigNewsLetter(Ido idoOrigem, Ido idoDraft) {
	
		Optional<ConfigNewsletterForm> config = configNewsletterFormRepository.findByIdo(idoOrigem);
		
		if(config.isPresent()) {
			
			ConfigNewsletterForm configNew = new ConfigNewsletterForm();
			BeanUtils.copyProperties(config.get(), configNew, "id","dateModel", "ido");
			configNew.setIdo(idoDraft);
			configNew = configNewsletterFormRepository.save(configNew);			
						
			List<NewsletterForm> newsletters = newsletterFormRepository.findByConfigNewsletterForm(config.get());
			
			if(Objects.nonNull(newsletters) && !newsletters.isEmpty()) {
				
				for (NewsletterForm letter : newsletters) {
					
					NewsletterForm letterNew = new NewsletterForm();
					BeanUtils.copyProperties(letter, letterNew, "id","dateModel", "configNewsletterForm");
					letterNew.setConfigNewsletterForm(configNew);
					newsletterFormRepository.save(letterNew);				
				}
			}
			
			super.createTools(ToolCodName.NEWSLETTER, idoDraft.getId(), configNew.getId());
			updatePositionTool(idoOrigem.getId(), idoDraft.getId(), config.get().getId(), configNew.getId(), ToolCodName.NEWSLETTER);
			
		}
	}
	
	@Transactional
	private void cloneAppearanceButton(Ido idoOrigem, Ido idoDraft) {
	
		Optional<AppearanceButton> button = appearanceButtonRepository.findByIdo(idoOrigem.getId());
		
		if(button.isPresent()) {
			
			AppearanceButton buttonNew = new AppearanceButton();
			BeanUtils.copyProperties(button.get(), buttonNew, "id","dateModel", "ido");
			buttonNew.setIdo(idoDraft);
			buttonNew = appearanceButtonRepository.save(buttonNew);			
					
		}
	}
	
	@Transactional
	private void cloneAppearanceCard(Ido idoOrigem, Ido idoDraft) {
	
		Optional<AppearanceCards> cards = appearanceCardsRepository.findByIdo(idoOrigem.getId());
		
		if(cards.isPresent()) {
			
			AppearanceCards cardNew = new AppearanceCards();
			BeanUtils.copyProperties(cards.get(), cardNew, "id","dateModel", "ido");
			cardNew.setIdo(idoDraft);
			cardNew = appearanceCardsRepository.save(cardNew);			
		}
	}
		
	@Transactional
	private void cloneAppearanceText(Ido idoOrigem, Ido idoDraft) {
	
		Optional<AppearanceText> text = appearanceTextRepository.findByIdo(idoOrigem.getId());
		
		if(text.isPresent()) {
			AppearanceText textNew = new AppearanceText();
			BeanUtils.copyProperties(text.get(), textNew, "id","dateModel", "ido");
			textNew.setIdo(idoDraft);
			textNew = appearanceTextRepository.save(textNew);			
		}
	}
	
	
	//copia as imagens carrossel para o rascunho
	@Transactional
	private void cloneImageCarousel(Ido idoOrigem, Ido idoDraft) {
				
		List<ImageCarousel> imageCarousels = imageCarouselRepository.findByIdo(idoOrigem.getId());
		
		if(Objects.nonNull(imageCarousels) && !imageCarousels.isEmpty()) {
			
			for (ImageCarousel carousel : imageCarousels) {
				
				ImageCarousel carouselNew = new ImageCarousel();
				BeanUtils.copyProperties(carousel, carouselNew, "id","dateModel", "ido");
				carouselNew.setIdo(idoDraft);
				
				List<ImageCarouselItem> itensNew = new ArrayList<>();
								
				for (ImageCarouselItem item : carousel.getItens()) {
					
					ImageCarouselItem itemNew = new ImageCarouselItem();
					
					BeanUtils.copyProperties(item, itemNew, "id","dateModel", "detail", "imageCarousel");
					itemNew.setImageCarousel(carouselNew);
					ImageCarouselItemDetail itemDetail = new ImageCarouselItemDetail();
					
					BeanUtils.copyProperties(item.getDetail(), itemDetail, "id","dateModel");
					itemNew.setDetail(itemDetail);		
					itensNew.add(itemNew);
				}
				
				carouselNew.setItens(itensNew);
								
				carouselNew = imageCarouselRepository.save(carouselNew);	
				
				super.createTools(ToolCodName.IMAGECAROUSEL, idoDraft.getId(), carouselNew.getId());
				updatePositionTool(idoOrigem.getId(), idoDraft.getId(), carousel.getId(), carouselNew.getId(), ToolCodName.IMAGECAROUSEL);
			}
		}
	}
	
	//copia os Html embutidos para o rascunho
	@Transactional
	private void cloneEmbedHtml(Ido idoOrigem, Ido idoDraft) {
				
		List<EmbedHtml> htmls = embedHtmlRepository.findByIdo(idoOrigem);
		
		if(Objects.nonNull(htmls) && !htmls.isEmpty()) {
			
			for (EmbedHtml html : htmls) {
				
				EmbedHtml newHtml = new EmbedHtml();
				BeanUtils.copyProperties(html, newHtml, "id","dateModel", "ido");
				newHtml.setIdo(idoDraft);
				newHtml = embedHtmlRepository.save(newHtml);	
				super.createTools(ToolCodName.HTML, idoDraft.getId(), newHtml.getId());
				updatePositionTool(idoOrigem.getId(), idoDraft.getId(), html.getId(), newHtml.getId(), ToolCodName.HTML);
			}
		}
	}
	
	//copia os logo bio para o rascunho
	@Transactional
	private void cloneLogoBio(Ido idoOrigem, Ido idoDraft) {
				
		Optional<LogoBio> logoBio = logoBioRepository.findByIdo(idoOrigem.getId());
		
		if(logoBio.isPresent()) {
			LogoBio newLogoBio = new LogoBio();
			BeanUtils.copyProperties(logoBio.get(), newLogoBio, "id","dateModel", "ido");
			newLogoBio.setIdo(idoDraft);
			newLogoBio = logoBioRepository.save(newLogoBio);	
			super.createTools(ToolCodName.LOGOBIO, idoDraft.getId(), newLogoBio.getId());
			updatePositionTool(idoOrigem.getId(), idoDraft.getId(), logoBio.get().getId(), newLogoBio.getId(), ToolCodName.LOGOBIO);
		}
	}
	
				
	//END CLONE TOOLS
		
	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
	
	/*
	 * Atualiza a posicao da ferramenta clonada conforme original
	 */
	@Transactional
	private void updatePositionTool(Long idoOrigem, Long idoDraft, Long idOld, Long idNew, ToolCodName tool) {
		
		IdoToolPosition positionOld = idoToolPositionService.findByIdoToolAndGenericId(idoOrigem, 
				tool, idOld);
					
		IdoToolPositionRequest positionRequest = IdoToolPositionRequest.builder()
				.ToolCodName(tool)
				.position(positionOld.getPosition())
				.genericToolId(idNew)
				.build();
		
		idoToolPositionService.updatePosition(positionRequest, idoDraft);
	}
}
