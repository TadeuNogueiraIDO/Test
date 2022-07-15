package br.com.idolink.api.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.api.integration.response.BlobFileResponse;
import br.com.idolink.api.dto.request.ImageCarouselItemRequest;
import br.com.idolink.api.dto.request.ImageCarouselRequest;
import br.com.idolink.api.dto.response.ImageCarouselItemResponse;
import br.com.idolink.api.dto.response.ImageCarouselResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ImageCarouselMapper;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.ImageCarousel;
import br.com.idolink.api.model.ImageCarouselItem;
import br.com.idolink.api.model.enums.ImageBannerAction;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.ImageCarouselItemRepository;
import br.com.idolink.api.repository.ImageCarouselRepository;
import br.com.idolink.api.service.ImageCarouselService;

@Service
public class ImageCarouselServiceImpl extends GenericToolsServiceImpl implements ImageCarouselService {

	@Autowired
	private ImageCarouselRepository repository;

	@Autowired
	private ImageCarouselItemRepository itemRepository;

	@Autowired
	private ImageCarouselMapper mapper;

	@Autowired
	private IdoRepository idoRepository;

	@Autowired
	private StorageApi storageApi;
	
	@Override
	public List<ImageCarouselResponse> publicFindByIdo(Long idoId) {

		Optional<Ido> ido = idoRepository.findById(idoId);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido","api.error.ido.not.found");
		}

		List<ImageCarousel> model = repository.findAllByIdoId(idoId);

		List<ImageCarouselResponse> response = mapper.modelToResponse(model);

		response.forEach(resp -> {
			if (resp.getItens() != null && !resp.getItens().isEmpty()) {

				for (ImageCarouselItemResponse item : resp.getItens()) {

					BlobFileResponse img = storageApi.findFileById(item.getImageId().getId());
					item.setImageId(img);
				}
			}

		});
		
		return response;
	}

	@Override
	public List<ImageCarouselResponse> findAll() {
		List<ImageCarousel> model = repository.findAll();
		return mapper.modelToResponse(model);
	}

	@Override
	public ImageCarouselResponse findById(Long id) {
		ImageCarousel model = validate(id);

		ImageCarouselResponse response = mapper.modelToResponse(model);

		if (response.getItens() != null && !response.getItens().isEmpty()) {

			for (ImageCarouselItemResponse item : response.getItens()) {

				BlobFileResponse img = storageApi.findFileById(item.getImageId().getId());
				item.setImageId(img);
			}
		}
		return response;
	}

	@Override
	public List<ImageCarouselResponse> findByIdo(Long idoId) {

		Optional<Ido> ido = idoRepository.findByIdUserFilter(idoId);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido","api.error.ido.not.found");
		}

		List<ImageCarousel> model = repository.findAllByIdoId(idoId);

		List<ImageCarouselResponse> response = mapper.modelToResponse(model);

		response.forEach(resp -> {
			if (resp.getItens() != null && !resp.getItens().isEmpty()) {

				for (ImageCarouselItemResponse item : resp.getItens()) {

					BlobFileResponse img = storageApi.findFileById(item.getImageId().getId());
					item.setImageId(img);
				}
			}

		});
		
		return response;

	}

	@Override
	@Transactional
	public ImageCarouselResponse create(Long idIdo, ImageCarouselRequest request) {
		
		validateItens(request);
		
		Optional<Ido> ido = idoRepository.findByIdUserFilter(idIdo);

		if (ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido","api.error.ido.not.found");
		}

		request.getItens().forEach(item -> {
			if (item.getAddDetail().equals(false)) {
				item.setDetail(null);
			}
		});

		request.getItens().forEach(item -> {
			if (item.getAction().equals(ImageBannerAction.EXTERNAL_LINK)) {
				item.setActionField(validateLink(item.getActionField()));
			}
		});

		request.getItens().forEach(item -> {
			if (!item.getAction().equals(ImageBannerAction.WHATSAPP)) {
				item.setDialCode(null);
			}
		});

		ImageCarousel model = mapper.requestToModel(request, ido.get());

		for (ImageCarouselItem item : model.getItens()) {
			item.setImageCarousel(model);
		}

		model = repository.save(model);

		ImageCarouselResponse response = mapper.modelToResponse(model);

		if (response.getItens() != null && !response.getItens().isEmpty()) {

			for (ImageCarouselItemResponse item : response.getItens()) {

				BlobFileResponse img = storageApi.findFileById(item.getImageId().getId());
				item.setImageId(img);
			}
		}

		Long qtd = repository.countByIdo(idIdo);
		
		//valida os recursos
		super.validToolsUserResource(idIdo, ToolCodName.IMAGECAROUSEL, Long.valueOf(model.getItens().size()));
		
		super.createTools(ToolCodName.IMAGECAROUSEL, idIdo, model.getId(), qtd);

		return response;
	}

	@Override
	@Transactional
	public ImageCarouselResponse update(Long id, ImageCarouselRequest request) {

		ImageCarousel model = validate(id);
		
		if (!model.getItens().isEmpty()) {
			model.getItens().clear();
		}
		
		if (model.getItens() != null && !model.getItens().isEmpty()) {
			model.getItens().clear();
		}
		
		BeanUtils.copyProperties(request, model, "ido", "dateModel", "itens");
		
		List<ImageCarouselItem> itens = itemRepository.findByImageCarousel(model.getId());

		request.getItens().forEach(item -> {
			if (item.getAddDetail().equals(false)) {
				item.setDetail(null);
			}
		});

		request.getItens().forEach(item -> {
			if (item.getAction().equals(ImageBannerAction.EXTERNAL_LINK)) {
				item.setActionField(validateLink(item.getActionField()));
			}
		});

		request.getItens().forEach(item -> {
			if (!item.getAction().equals(ImageBannerAction.WHATSAPP)) {
				item.setDialCode(null);
			}
		});

		if (!itens.isEmpty()) {
			itemRepository.deleteAll(itens);
			itemRepository.flush();
		}

		List<ImageCarouselItem> itenss = mapper.itemRequestToModel(request.getItens());


		for (ImageCarouselItem item : itenss) {
			
			item.setImageCarousel(model);
			
		}

		//valida os recursos
		model.setItens(itenss);
		super.validToolsUserResource(model.getIdo().getId(), ToolCodName.IMAGECAROUSEL, Long.valueOf(model.getItens().size()));
		model = repository.save(model);

		ImageCarouselResponse response = mapper.modelToResponse(model);

		if (response.getItens() != null && !response.getItens().isEmpty()) {

			for (ImageCarouselItemResponse item : response.getItens()) {

				BlobFileResponse img = storageApi.findFileById(item.getImageId().getId());
				item.setImageId(img);
			}
		}
		return response;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ImageCarousel model = validate(id);

		try {
			Long idoId = model.getIdo().getId();
			repository.deleteById(id);
       		repository.flush();
			avaliableAssociateIdoTool(idoId, id);

		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "ImageCarousel", "api.error.image.carousel.conflict");
		}
	}

	private ImageCarousel validate(Long id) {
		Optional<ImageCarousel> model = repository.findById(id);
		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "ImageCarousel", "api.error.image.carousel.not.found");
		}
		return model.get();

	}

	private void validateItens(ImageCarouselRequest request) {
		request.getItens().forEach(i -> {
			validateAction(i);
		});
	}

	private void validateAction(ImageCarouselItemRequest request) {
		String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

		switch (request.getAction()) {
		case EMAIL:

			if (!Pattern.compile(emailRegex).matcher(request.getActionField()).matches()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND, "E-mail", "api.error.email.incorrect.format");
			}
			break;
		case EXTERNAL_LINK:
			request.setActionField(validateLink(request.getActionField()));
			break;
		case MAXIMAZE_IMAGE:
			request.setActionField(null);
			break;
		case WHATSAPP:
			break;
		case NONE:
			request.setActionField(null);
			break;
		default:
			break;
		}
	}

	private String validateLink(String link) {
		String[] linkSplit = link.split("//");

		if (linkSplit.length >= 2) {
			link = "https://" + linkSplit[1];
		} else {
			link = "https://" + linkSplit[0];
		}

		try {
			@SuppressWarnings("unused")
			URL u = new URL(link);
		} catch (MalformedURLException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "url", "api.error.url.incorrect");
		}

		return link;

	}

	@Transactional
	private void avaliableAssociateIdoTool(Long idIdoDeleted, Long id) {
		Long qtd = repository.countByIdo(idIdoDeleted);
		super.deleteTools(ToolCodName.IMAGECAROUSEL, idIdoDeleted, id, qtd);
	}

}
