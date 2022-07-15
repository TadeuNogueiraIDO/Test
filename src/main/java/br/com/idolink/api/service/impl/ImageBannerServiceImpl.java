package br.com.idolink.api.service.impl;

import static java.util.Objects.nonNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.ImageBannerRequest;
import br.com.idolink.api.dto.response.ImageBannerResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ImageBannerMapper;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.ImageBanner;
import br.com.idolink.api.model.ImageBannerContact;
import br.com.idolink.api.model.enums.ImageBannerAction;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.ImageBannerRepository;
import br.com.idolink.api.service.ImageBannerService;

@Service
public class ImageBannerServiceImpl extends GenericToolsServiceImpl implements ImageBannerService{
	
	@Autowired
	private ImageBannerRepository repository;
	
	@Autowired
	private ImageBannerMapper mapper;
	
	@Autowired
	private IdoRepository idoRepository;
			
	@Override
	public ImageBannerResponse findById(Long id) {
		ImageBanner model = validate(id);
		return mapper.response(model);
	}

	@Override
	public List<ImageBannerResponse> findByIdo(Long idoid) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoid);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		List<ImageBanner> model = repository.findByIdo(idoid);
		return mapper.response(model);
	}
	
	@Override
	public List<ImageBannerResponse> publicFindByIdo(Long idoid) {
		
		Optional<Ido> ido =  idoRepository.findById(idoid);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		List<ImageBanner> model = repository.findByIdo(idoid);
		return mapper.response(model);
	}

	@Override
	@Transactional
	public ImageBannerResponse save(Long idoId, ImageBannerRequest request) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idoId);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
			
		ImageBanner model = mapper.create(request, ido.get());
		if(!request.getAction().equals(ImageBannerAction.WHATSAPP)) {	
			model.setContact(null);
			validateBannerAction(request);
		} else {
			changeActionField(request, model);
		}
		
		if(request.getAction().equals(ImageBannerAction.EXTERNAL_LINK)) {
			model.setField(validateLink(request.getField()));
		}
		
		model = repository.save(model);
		
		Long qtd = repository.countByIdo(idoId);
		super.createTools(ToolCodName.IMAGEBANNER, idoId, model.getId(), qtd);
								
		return mapper.response(model);
	}

	@Override
	public ImageBannerResponse update(ImageBannerRequest request, Long id) {
		ImageBanner model = validate(id);
		
		if(!request.getAction().equals(ImageBannerAction.WHATSAPP)) {	
			model.setContact(null);
			validateBannerAction(request);
		} else {
			changeActionField(request, model);
		}
		
		BeanUtils.copyProperties(request, model, "id", "ido");
		
		model = repository.save(model);
		return mapper.response(model);
	}

	@Override
	public void delete(Long id) {
		
		ImageBanner model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"ImageBanner", "api.error.image.banner.not.found"));
			
		Long idoId = model.getIdo().getId();
		
		repository.deleteById(id);
		repository.flush();
		
		avaliableAssociateIdoTool(idoId, id);
			
	}
	
	private ImageBanner validate(Long id) {
		Optional<ImageBanner> model = repository.findById(id);
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "ImageBanner", "api.error.image.banner.not.found");
		}
		return model.get();
	}

	private void validateBannerAction(ImageBannerRequest request) {
		String emailRegex =  "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		
		request.setContact(null);
		
		switch (request.getAction()) {
		case EMAIL:
			if(!Pattern.compile(emailRegex).matcher(request.getField()).matches()) {
				throw new BaseFullException(HttpStatus.NOT_FOUND,"E-mail" ,"api.error.email.incorrect.format");
			}
			
			break; 
		case EXTERNAL_LINK:
		request.setField(validateLink(request.getField()));
			break;
		case MAXIMAZE_IMAGE:
			request.setField(null);
			break;
		case WHATSAPP:
			break;
		case NONE:
			request.setField(null);
			break;
		default:
			break;
		}
	}
	
	private void changeActionField(ImageBannerRequest request, ImageBanner model) {
		
		if(nonNull(request) && nonNull(request.getContact())) {
			request.setField(null);
			ImageBannerContact contact = ImageBannerContact.builder()
					.dialCode(request.getContact().getDialCode())
					.number(request.getContact().getNumber())
					.build();
			model.setContact(contact);
		}
	}
	
	private String validateLink(String link) {
		String[] linkSplit = link.split("//");
		
		if (linkSplit.length >= 2) {
			link = "https://" + linkSplit[1];
		}
		else {
			link = "https://" + linkSplit[0];
		}
		
		
		try {
			@SuppressWarnings("unused")
			URL u = new URL(link);
		} catch (MalformedURLException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST,"url", "api.error.url.incorrect");
		}
		
		return link;
		
	}
	
	@Transactional
	private void avaliableAssociateIdoTool(Long idIdoDeleted, Long id) {
		Long qtd = repository.countByIdo(idIdoDeleted);
		super.deleteTools(ToolCodName.IMAGEBANNER,idIdoDeleted, id, qtd);
	}
	
}
