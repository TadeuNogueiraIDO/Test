package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.PaymentSetupRequest;
import br.com.idolink.api.dto.response.PaymentSetupResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.PaymentSetupMapper;
import br.com.idolink.api.model.PaymentSetup;
import br.com.idolink.api.model.Shop;
import br.com.idolink.api.repository.PaymentSetupRepository;
import br.com.idolink.api.repository.ShopRepository;
import br.com.idolink.api.service.PaymentSetupService;

@Service
public class PaymentSetupServiceImpl implements PaymentSetupService {

	@Autowired
	private PaymentSetupRepository repository;

	@Autowired
	private PaymentSetupMapper mapper;

	@Autowired
	private ShopRepository shopRepository;

	@Override
	public PaymentSetupResponse findByShop(Long id) {
		return this.findByShop(id, true);
	}

	@Override
	public PaymentSetupResponse findByShop(Long id, boolean validation) {

		Optional<Shop> shop = shopRepository.findById(id);
		if (shop.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Shop", "api.error.shop.not.found");
		}

		Optional<PaymentSetup> model = repository.findByShop(id);

		if (validation) {
			validate(model, "PaymentSetup", "api.error.payment.setup.not.found");
		}

		return model.isPresent() ? mapper.response(model.get()) : null;
	}

	@Override
	public Optional<PaymentSetup> findByShop(Shop shop) {
		Optional<PaymentSetup> optional = repository.findByShop(shop.getId());
		return optional;
	}

	@Override
	public List<PaymentSetupResponse> list() {
		List<PaymentSetup> model = repository.findAll();
		return mapper.response(model);
	}

	@Override
	@Transactional
	public PaymentSetupResponse create(Long shopId, PaymentSetupRequest request) {
		

		Optional<Shop> shop = shopRepository.findById(shopId);
		
		if (shop.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Shop", "api.error.shop.not.found");
		}
		
		validatePaymentSetupShop(shopId);

		PaymentSetup model = mapper.model(request);
		model.setShop(shop.get());
		return mapper.response(repository.save(model));
	}

	@Override
	@Transactional
	public PaymentSetupResponse update(Long id, PaymentSetupRequest request) {

		PaymentSetup model = repository.findById(id).orElseThrow(() -> new BaseFullException(HttpStatus.NOT_FOUND,
				"PaymentSetup", "api.error.payment.setup.not.found"));
		
		PaymentSetup modelNew = mapper.model(request);
		BeanUtils.copyProperties(modelNew, model, "id", "dateModel", "shop");
		
		return mapper.response(repository.save(model));
	}

	
	public void validatePaymentSetupShop(Long shopId) {
		Optional<PaymentSetup> model = repository.findByShop(shopId);

		if (model.isPresent()) {
			throw new BaseFullException(HttpStatus.CONFLICT, "PaymentSetup", "api.error.payment.setup.conflict");
		}
	}

	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
}
