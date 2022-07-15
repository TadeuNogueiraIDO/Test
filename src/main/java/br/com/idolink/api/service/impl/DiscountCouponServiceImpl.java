package br.com.idolink.api.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.NewCouponRequest;
import br.com.idolink.api.dto.response.DiscountCouponResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.DiscountCouponMapper;
import br.com.idolink.api.model.DiscountCoupon;
import br.com.idolink.api.model.Shop;
import br.com.idolink.api.model.User;
import br.com.idolink.api.model.enums.Discount;
import br.com.idolink.api.repository.DiscountCouponRepository;
import br.com.idolink.api.repository.ShopRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.service.DiscountCouponService;

@Service
public class DiscountCouponServiceImpl implements DiscountCouponService {

	@Autowired
	private DiscountCouponMapper mapper;

	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DiscountCouponRepository repository;

	@Override
	public List<DiscountCouponResponse> findyByListShopCoupon(Long shopId, Long userId) {
		
		Optional<Shop> shop = shopRepository.findById(shopId);
		if (shop.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Shop", "api.error.shop.not.found");
		}
		
		validateshopByUser(userId, shopId);
		List<DiscountCoupon> modelSave = new ArrayList<>();
		
		List<DiscountCoupon> model = repository.findCouponByShop(shop.get().getId());
		
		for(DiscountCoupon coupon : model) {

			if(coupon.getStatus()) {
				modelSave.add(coupon);
			}
			
		}

		return mapper.response(modelSave);

	}
	
	@Override
	public DiscountCouponResponse findById(Long idCoupon, Long userId) {
		
		Optional<User> user = userRepository.findById(userId);
		if(user.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "User", "api.error.user.not.found");
		}
		
		Optional<DiscountCoupon> model = repository.findByIdByUser(idCoupon, userId);
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Coupon", "api.error.coupon.not.found");
		}
		
		return mapper.modelToResponse(model.get());
		
	}

	@Override
	@Transactional
	public DiscountCouponResponse create(NewCouponRequest request, Long shopId) {

		Optional<Shop> shop = shopRepository.findById(shopId);

		if (shop.isEmpty()) {

			throw new BaseFullException(HttpStatus.NOT_FOUND, "Shop", "api.error.shop.not.found");
		}

		validateExpiredDate(request.getDatexpirationDate());

		validateCodCouponExists(request.getCouponCode().toUpperCase(),shopId, false);

		validateQuantCaracteres(request.getCouponCode(), 20);

		DiscountCoupon model = mapper.requestToModel(request);

		discountCalculation(request.getDiscountCoupon(), model, request);

		model.setShop(shop.get());

		model.setCouponCode(request.getCouponCode().toUpperCase());

		if (request.getMinimumValueCondition()) {

			if (request.getMinimumValue() > 0) {

				model.setMinimumValue(request.getMinimumValue());

			} else {

				throw new BaseFullException(HttpStatus.CONFLICT, "Coupon",
						"api.error.coupon.min.value.smaller.conflict");

			}
		} else {
			model.setMinimumValue(null);
		}
		model.setStatus(true);
		repository.save(model);
		return mapper.modelToResponse(model);
	}

	@Transactional
	public void delete(Long id) {

		Optional<DiscountCoupon> model = repository.findById(id);
		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Coupon", "api.error.coupon.not.found");
		}
		try {

			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Coupon", "api.error.user.conflict");
		}

	}

	@Override
	@Transactional
	public DiscountCouponResponse update(NewCouponRequest request, Long id, Boolean status, Long shopId) {
		
		Optional<DiscountCoupon> discountC = repository.findById(id);
		if(discountC.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Coupon", "api.error.coupon.not.found");
		}
		if(discountC.get().getCouponCode().equals(request.getCouponCode().toUpperCase())) {
		
			validateCodCouponExists(request.getCouponCode().toUpperCase(), shopId, true);
		}else {
			validateCodCouponExists(request.getCouponCode().toUpperCase(), shopId, false);
		}
        
        Optional<DiscountCoupon> model = repository.findByCodByShop(discountC.get().getCouponCode(),shopId);
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Coupon", "api.error.coupon.not.found");
		}
		if(!model.get().getStatus()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "Coupon", "api.error.coupon.status.conflict");
		}

		validateExpiredDate(request.getDatexpirationDate());

		validateQuantCaracteres(request.getCouponCode(), 20);

		if (repository.findByCod(discountC.get().getCouponCode()) == null) {

			throw new BaseFullException(HttpStatus.NOT_FOUND, "Coupon", "api.error.coupon.not.found");

		}

		BeanUtils.copyProperties(request, model.get(), "id", "dateModel");

		discountCalculation(request.getDiscountCoupon(), model.get(), request);

		model.get().setCouponCode(request.getCouponCode().toUpperCase());

		if (request.getMinimumValueCondition()) {

			if (request.getMinimumValue() > 0) {

				model.get().setMinimumValue(request.getMinimumValue());

			} else {

				throw new BaseFullException(HttpStatus.CONFLICT, "Coupon",
						"api.error.coupon.min.value.smaller.conflict");

			}
		} else {
			model.get().setMinimumValue(null);
		}
		model.get().setStatus(status);
		if(status == null) {
			model.get().setStatus(true);
		}
		
		
		repository.save(model.get());
		return mapper.modelToResponse(model.get());

	}

	private void discountCalculation(Discount discount, DiscountCoupon newCoupn, NewCouponRequest request) {
		switch (discount) {
		case FIXEDVALUE:
			newCoupn.setValueDiscount(request.getValue());
		case PERCENTAGE:

			newCoupn.setValueDiscount(request.getValue());

			if (newCoupn.getValueDiscount() > 100) {
				throw new BaseFullException(HttpStatus.CONFLICT, "Coupon",
						"api.error.coupon.percentage.larger.conflict");
			}
			if (newCoupn.getValueDiscount() < 1 || newCoupn.getValueDiscount() == null) {
				throw new BaseFullException(HttpStatus.CONFLICT, "Coupon",
						"api.error.coupon.percentage.smaller.conflict");
			}

		default:
			break;

		}

	}

	private void validateCodCouponExists(String CouponCode,Long shopId, boolean put) {
		Optional<DiscountCoupon> model = repository.findByCodByShop(CouponCode,shopId);
		Boolean validateDate = false;

		if(!model.isEmpty()) {
		if ( model.get().getDatexpirationDate() != null || !model.get().getDatexpirationDate().toString().isBlank()) {
			LocalDate dateAtual = LocalDate.now();
			Period period = Period.between(dateAtual, model.get().getDatexpirationDate());
			int mes = period.getMonths();
			int dias = period.getDays();
			if (dias <= 0 && mes <= 0) {

				validateDate = true;

			}

		}
		}

		if(!put) {
		if (!validateDate) {

			if (model.isPresent()) {

				throw new BaseFullException(HttpStatus.CONFLICT, "Coupon",
						"api.error.coupon.cod.already.exists.conflict");

			}
		}
		}
	}

	private String validateQuantCaracteres(String codCoupon, int max) {

		if (codCoupon.length() > max) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Coupon",
					"api.error.coupon.cod.larger.string.limit.conflict");
		}

		return codCoupon;
	}

	private void validateExpiredDate(LocalDate dateCoupon) {

		if (dateCoupon != null) {
			LocalDate dateAtual = LocalDate.now();

			Period period = Period.between(dateAtual, dateCoupon);
			int mes = period.getMonths();
			int dias = period.getDays();

			if (dias <= 0 && mes <= 0) {
				throw new BaseFullException(HttpStatus.CONFLICT, "Coupon", "api.error.coupon.date.smaller.conflict");
			}
		}

	}

	private void validateshopByUser(Long userId, Long shopId) {
		
		List<Shop> shop = shopRepository.findByUser(userId);
		Optional<Shop> shopIdValidate = shopRepository.findById(shopId);
		int i = 0;
		for(Shop v : shop) {
			if(v.getId().equals(shopIdValidate.get().getId())) {
				i=+1;
			}
		}
		if(i==0) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Shop", "api.error.shop.not.found");
		}
	}

}