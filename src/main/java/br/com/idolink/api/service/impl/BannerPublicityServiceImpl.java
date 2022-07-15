package br.com.idolink.api.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.BannerPublicityRequest;
import br.com.idolink.api.dto.response.BannerPublicityResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.BannerPublicityMapper;
import br.com.idolink.api.model.BannerPublicity;
import br.com.idolink.api.repository.BannerPublicityRepository;
import br.com.idolink.api.service.BannerPublicityService;

@Service
public class BannerPublicityServiceImpl implements BannerPublicityService {

	@Autowired
	private BannerPublicityRepository repository;

	@Autowired
	private  BannerPublicityMapper mapper;

	@Override
	public BannerPublicityResponse findCarousel() {
		Optional<BannerPublicity> model = repository.findTop1ByOrderByTitle();
		validate(model, "BannerPublicity", "api.error.banner.publish.not.found");
		return mapper.response(model.get());
	}
	
	
	@Override
	public BannerPublicityResponse findById(Long id) {
		Optional<BannerPublicity> model = repository.findById(id);
		validate(model, "BannerPublicity", "api.error.banner.publish.not.found");
		return mapper.response(model.get());
	}

	@Override
	@Transactional
	public BannerPublicityResponse create(BannerPublicityRequest request) {
		
		Optional<BannerPublicity> modelOld = repository.findTop1ByOrderByTitle();
		
		if(modelOld.isPresent()) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "BannerPublicity", "api.error.banner.publish.ido.conflict");
		}
				
		BannerPublicity model = mapper.model(request);
		
		model = repository.save(model);
		return mapper.response(model);
	}

	@Transactional
	@Override
	public BannerPublicityResponse update(Long id, BannerPublicityRequest request) {

		BannerPublicity model = repository.findById(id).orElseThrow(
				() -> new BaseFullException(HttpStatus.NOT_FOUND, "BannerPublicity", "api.error.banner.publish.not.found"));

		BeanUtils.copyProperties(request, model, "id", "dateModel");
		mapper.convertImagesModel(request,model);
		model = repository.save(model);
		return mapper.response(model);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		Optional<BannerPublicity> model = repository.findById(id);
		validate(model, "BannerPublicity", "api.error.banner.publish.not.found");
		try {
			repository.deleteById(id);
			repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "BannerPublicity", "api.error.banner.publish.conflict");
		}


	}

	private void validate(Optional<?> model, String field, String message) {
		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
}
