package br.com.idolink.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.DeliveryPaymentSetupRequest;
import br.com.idolink.api.dto.request.common.GenericRequest;
import br.com.idolink.api.dto.response.DeliveryPaymentSetupResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.DeliveryPaymentSetupMapper;
import br.com.idolink.api.model.DeliveryPaymentSetup;
import br.com.idolink.api.model.PaymentType;
import br.com.idolink.api.model.Shop;
import br.com.idolink.api.repository.DeliveryPaymentSetupRepository;
import br.com.idolink.api.repository.PaymentTypeRepository;
import br.com.idolink.api.repository.ShopRepository;
import br.com.idolink.api.service.DeliveryPaymentSetupService;

@Service
public class DeliveryPaymentSetupServiceImpl implements DeliveryPaymentSetupService{

	@Autowired
	private DeliveryPaymentSetupRepository repository;

	@Autowired
	private DeliveryPaymentSetupMapper mapper;
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private PaymentTypeRepository paymentRepository;

	@Override
	public DeliveryPaymentSetupResponse findByShop(Long id) {

		Optional<Shop> shop = shopRepository.findById(id);
		
		if (shop.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Shop", "api.error.shop.not.found");
		}

		List<DeliveryPaymentSetup> models = repository.findByShop(id);
		
		return mapper.response(models, id);
	}

	@Override
	@Transactional
	public DeliveryPaymentSetupResponse create(Long shopId, DeliveryPaymentSetupRequest request) {
		
		Optional<Shop> shop = shopRepository.findById(shopId);
		
		if (shop.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Shop", "api.error.shop.not.found");
		}
		
		validatePaymentSetupShop(shopId);
		
		List<DeliveryPaymentSetup> models = new ArrayList<>();
		
		for (GenericRequest typeId : request.getPaymentTypeIds()) {
			
			Optional<PaymentType> type = paymentRepository.findById(typeId.getId());
			
			if(type.isPresent()) {
				
				DeliveryPaymentSetup delivery =  DeliveryPaymentSetup.builder().paymentType(type.get()).shop(shop.get()).build();
				
				if(type.get().getId() == 13L) {
					delivery.setOtherPayment(request.getOtherPayment());
				}
				models.add(delivery);
				repository.save(delivery);
			}
		}
		
		return mapper.response(models, shopId);
	}

	@Override
	@Transactional
	public DeliveryPaymentSetupResponse update(Long shopId, DeliveryPaymentSetupRequest request) {

		
		Optional<Shop> shop = shopRepository.findById(shopId);
		
		if (shop.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Shop", "api.error.shop.not.found");
		}
		
		List<DeliveryPaymentSetup> model = repository.findByShop(shopId);

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND,"DeliveryPaymentSetup", "api.error.delivery.payment.setup.not.found");
		}
		
		repository.deleteAll(model);
		return this.create(shopId, request);
	}

	public void validatePaymentSetupShop(Long shopId) {
		
		List<DeliveryPaymentSetup> model = repository.findByShop(shopId);

		if (!model.isEmpty()) {
			throw new BaseFullException(HttpStatus.CONFLICT, "DeliveryPaymentSetup", "api.error.delivery.payment.setup.not.found");
		}
	}
	
}
