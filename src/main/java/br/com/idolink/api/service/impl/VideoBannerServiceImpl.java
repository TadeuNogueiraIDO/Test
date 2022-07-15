package br.com.idolink.api.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.VideoBannerRequest;
import br.com.idolink.api.dto.response.VideoBannerResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.VideoBannerMapper;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.VideoBanner;
import br.com.idolink.api.model.enums.ToolCodName;
import br.com.idolink.api.repository.IdoRepository;
import br.com.idolink.api.repository.VideoBannerRepository;
import br.com.idolink.api.service.VideoBannerService;

@Service
public class VideoBannerServiceImpl extends GenericToolsServiceImpl implements VideoBannerService{
	
	@Autowired
	private VideoBannerRepository repository;
	
	@Autowired
	private VideoBannerMapper mapper;
	
	@Autowired
	private IdoRepository idoRepository;
			
	@Override
	public VideoBannerResponse findById(Long id) {
		VideoBanner model = validate(id);
		return mapper.response(model);
	}
	
	@Override
	public List<VideoBannerResponse> publicFindByIdo(Long idIdo) {
		
		Optional<Ido> ido =  idoRepository.findById(idIdo);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		List<VideoBanner> model = repository.findByIdo(idIdo);
		return mapper.response(model);
	}

	@Override
	public List<VideoBannerResponse> findByIdo(Long idIdo) {
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idIdo);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		List<VideoBanner> model = repository.findByIdo(idIdo);
		return mapper.response(model);
	}

	@Override
	@Transactional
	public VideoBannerResponse save(Long idIdo, VideoBannerRequest request) {
		request.setLink(validateLink(request.getLink()));
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(idIdo);
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
		
		VideoBanner model = mapper.create(request, ido.get());
		model = repository.save(model);
		super.createTools(ToolCodName.VIDEOBANNER, idIdo, model.getId());
				
		return mapper.response(model);
	}

	@Override
	@Transactional
	public VideoBannerResponse update(VideoBannerRequest request, Long id) {
		request.setLink(validateLink(request.getLink()));
		
		VideoBanner model = validate(id);
		
		Optional<Ido> ido =  idoRepository.findByIdUserFilter(model.getIdo().getId());
		
		if(ido.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Ido", "api.error.ido.not.found");
		}
				
		BeanUtils.copyProperties(request, model, "id", "ido");
		model = repository.save(model);
		return mapper.response(model);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		VideoBanner model = repository.findById(id)
				.orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,"BannerVideo", "api.error.video.banner.not.found"));
		
		Long idIdo = model.getIdo().getId();
		repository.deleteById(id);
		
		avaliableAssociateIdoTool(idIdo, id);
	}
	
	private VideoBanner validate(Long id) {
		Optional<VideoBanner> model = repository.findById(id);
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND,"BannerVideo", "api.error.video.banner.not.found");
		}
		return model.get();
	}
	
	@Transactional
	private void avaliableAssociateIdoTool(Long idIdoDeleted, Long idTool) {
		Long qtd = repository.countByIdo(idIdoDeleted);
		super.deleteTools(ToolCodName.VIDEOBANNER,idIdoDeleted, idTool, qtd);	
	}
	
	private String validateLink(String url) {
		String[] linkSplit = url.split("//");		
		
		if (linkSplit.length >= 2) {
			url = "https://" + linkSplit[1];
		} else {
			url = "https://" + linkSplit[0];
		}
		
		try {
			@SuppressWarnings("unused")
			URL s = new URL(url);
		} catch (MalformedURLException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST,"url", "api.error.url.incorrect");
		}
		
		return url;
		
	}

}
