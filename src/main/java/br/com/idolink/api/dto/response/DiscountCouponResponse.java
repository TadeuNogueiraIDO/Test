package br.com.idolink.api.dto.response;



import java.time.LocalDate;

import br.com.idolink.api.model.enums.Discount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountCouponResponse {
	
	private Long id;

	private String name;
	
	private Boolean status;
	
	private Discount discountCoupon; 
	
	private LocalDate datexpirationDate;
	
	private Boolean minimumValueCondition; 
	
	private String couponCode;
	
	private Double minimumValue;
	
	private Double value;
	
}
