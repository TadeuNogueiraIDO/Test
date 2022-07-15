package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.NewCouponRequest;
import br.com.idolink.api.dto.response.DiscountCouponResponse;

public interface DiscountCouponService {

	DiscountCouponResponse create(NewCouponRequest request, Long ShopId);

	void delete(Long id);

	List<DiscountCouponResponse> findyByListShopCoupon(Long shopId, Long userId);

	DiscountCouponResponse update(NewCouponRequest request, Long idCoupon, Boolean status, Long shopId);

	DiscountCouponResponse findById(Long idCoupon, Long userId);

}
