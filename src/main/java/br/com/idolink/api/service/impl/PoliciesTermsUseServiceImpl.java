package br.com.idolink.api.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.PoliciesTermsUseRequest;
import br.com.idolink.api.dto.response.PoliciesTermsUseResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.model.PoliciesTermsUse;
import br.com.idolink.api.model.Shop;
import br.com.idolink.api.repository.PoliciesTermsUseRepository;
import br.com.idolink.api.repository.ShopRepository;
import br.com.idolink.api.service.PoliciesTermsUseService;

@Service
public class PoliciesTermsUseServiceImpl implements PoliciesTermsUseService {

	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private PoliciesTermsUseRepository policiesTermsUseRepository;
	

	@Override
	@Transactional
	public PoliciesTermsUseResponse update (PoliciesTermsUseRequest request, Long shopId) {

		Optional<Shop> shop = shopRepository.findById(shopId);

		if (shop.isEmpty()) {

			throw new BaseFullException(HttpStatus.NOT_FOUND, "Shop", "api.error.shop.not.found");
			
		}
		
		shop.get().setReimbursement(request.getAdditionalTextReimbursement());
		
		shop.get().setUse(request.getAdditionalTextUse());
		
		shop.get().setPrivacy(request.getAdditionalTextPrivacy());
		
		Shop shopSave = shop.get();
		
		shopSave = shopRepository.save(shopSave);

		PoliciesTermsUseResponse response = new PoliciesTermsUseResponse();
		
		Optional<PoliciesTermsUse> terms = policiesTermsUseRepository.findById((long) 1);
		
		response.setReimbursement(request.getAdditionalTextReimbursement());
		
		response.setPrivacy(request.getAdditionalTextPrivacy());
		
		response.setUse(request.getAdditionalTextUse());
		
		response.setShopId(shopId);
		
		response.setBaseUse(terms.get().getUse());
		
		response.setBaseReimbursement(terms.get().getReimbursement());
		
		response.setBasePrivacy(terms.get().getPrivacy());
		
		return response;

	}

	@Override
	public PoliciesTermsUseResponse findById(Long shopId) {

		Optional<Shop> shop = shopRepository.findById(shopId);

		if (shop.isEmpty()) {

			throw new BaseFullException(HttpStatus.NOT_FOUND, "Shop", "api.error.shop.not.found");

		}
		Optional<PoliciesTermsUse> terms = policiesTermsUseRepository.findById((long) 1);
		
		PoliciesTermsUseResponse response = new PoliciesTermsUseResponse();
		
        response.setBaseUse(terms.get().getUse());
		
		response.setBaseReimbursement(terms.get().getReimbursement());
		
		response.setBasePrivacy(terms.get().getPrivacy());
		
		response.setPrivacy(shop.get().getPrivacy());

		response.setReimbursement(shop.get().getReimbursement());
		
		response.setUse(shop.get().getUse());
		
		response.setShopId(shopId);
		
		return response;
	}

	@Override
	@Transactional
	public void deleteById(Long shopId) {

		Optional<Shop> shop = shopRepository.findById(shopId);

		if (shop.isEmpty()) {

			throw new BaseFullException(HttpStatus.NOT_FOUND, "Shop", "api.error.shop.not.found");

		}
		
		shop.get().setPrivacy(null);
		shop.get().setUse(null);
		shop.get().setReimbursement(null);
		
		Shop shopSave = shop.get();
		
		shopSave = shopRepository.save(shopSave);
	}


		
	}


