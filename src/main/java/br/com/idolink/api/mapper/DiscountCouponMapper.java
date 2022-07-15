package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.NewCouponRequest;
import br.com.idolink.api.dto.response.DiscountCouponResponse;
import br.com.idolink.api.model.DiscountCoupon;

@Service
public class DiscountCouponMapper {
	
	@Autowired
	private ModelMapper mapper;

	public DiscountCoupon requestToModel(NewCouponRequest request) {
		return mapper.map(request, DiscountCoupon.class);
	
	}
	public DiscountCouponResponse modelToResponse(DiscountCoupon model) {
		return mapper.map(model, DiscountCouponResponse.class);
		
	}
	
	public List<DiscountCouponResponse> response(List<DiscountCoupon> model){
		
		return model.stream().map(m ->this.modelToResponse(m)).collect(Collectors.toList());
	}
	
	
	
}
