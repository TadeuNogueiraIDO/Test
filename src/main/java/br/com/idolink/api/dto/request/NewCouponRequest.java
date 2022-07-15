package br.com.idolink.api.dto.request;


import java.time.LocalDate;

import br.com.idolink.api.model.enums.Discount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewCouponRequest {

	private String couponCode; 
	
	private String name;
	
	private LocalDate datexpirationDate;
	
	private Discount discountCoupon;
	
	private Double value;
	
	private Boolean minimumValueCondition;
	
	private Double minimumValue;
	
	
	
	
	
	
	
}
