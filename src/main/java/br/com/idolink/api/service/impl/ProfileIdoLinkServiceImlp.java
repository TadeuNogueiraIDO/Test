package br.com.idolink.api.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.ProfileIdoLinkRequest;
import br.com.idolink.api.dto.response.ProfileIdoLinkResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.ProfileIdoLinkMapper;
import br.com.idolink.api.model.ProfileIdoLink;
import br.com.idolink.api.model.enums.ProfileIdoCodName;
import br.com.idolink.api.repository.ProfileIdoLinkRepository;
import br.com.idolink.api.service.ProfileIdoLinkService;

@Service
public class ProfileIdoLinkServiceImlp  implements  ProfileIdoLinkService{
	
	@Autowired
	private ProfileIdoLinkMapper mapper;
	
	@Autowired
	private ProfileIdoLinkRepository repository;
	
	
	  @Override public ProfileIdoLinkResponse findByCodName(ProfileIdoCodName codName) {
	  
	  ProfileIdoLink model = repository.findByCodName(codName);
	
	  if(model == null) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Profile", "api.error.profile.not.found");
		}
	  
	 return mapper.response(model); 
	  
	  }
	 
	@Override
	@Transactional
	public ProfileIdoLinkResponse create( ProfileIdoLinkRequest request) {
		validateProfile(request.getCodName()); 
		request.setUrl(validateLink(request.getUrl()));
		
		ProfileIdoLink model = mapper.requestToModel(request);
		
		try {
			model = repository.save(model);
		
		 }catch(DataIntegrityViolationException e) { 
			 throw new BaseFullException(HttpStatus.CONFLICT," Profile", "api.error.profile.name.conflict");
		 }
					
		return mapper.response(model);
		
	}	
	
	@Override
	@Transactional
	public ProfileIdoLinkResponse update(Long id, ProfileIdoLinkRequest request) {
		request.setUrl(validateLink(request.getUrl()));
		
		ProfileIdoLink model = repository.findById(id).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,
				"Profile", "api.error.profile.not.found"));

		BeanUtils.copyProperties(request,model, "id", "dateModel");
	
		return mapper.response(repository.save(model));
		
	}
	
	public void validateProfile(ProfileIdoCodName codName ) {
		  ProfileIdoLink model = repository.findByCodName(codName);
		
		if(Objects.nonNull(model)) {
			throw new BaseFullException(HttpStatus.CONFLICT, "ProfileIdoLink","api.error.profile.exist.conflict");
		}
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
			URL u = new URL(url);
		} catch (MalformedURLException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST,"url", "api.error.url.incorrect");
		}
		
		return url;
		
	}
	
	
	
}
