package br.com.idolink.api.dto.response;

import java.math.BigDecimal;
import java.util.List;

import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.model.enums.QuickPayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SingleQuickPayCheckoutResponse {
	
	private Long singleQuickPayId;
	private BigDecimal singlePrice;
	private Integer singleQuantity;
	private QuickPayType type;
	private List<SingleQuickPayProductResponse> products;
	private String observation;
	private BigDecimal discountValue;
	private BigDecimal additionalValue;
	
	private QuickPayPaymentStatus statusPayment;
	
	private QuickPaySedingStatus statusSeding;
	
	public BigDecimal getSubTotalValue() {
		
		BigDecimal subtotal = new BigDecimal(0);
		
		if(getType().equals(QuickPayType.SINGLE)) {
			subtotal = getSinglePrice().multiply(new BigDecimal(getSingleQuantity()));
		}else {
			for (SingleQuickPayProductResponse product : getProducts()) {
				BigDecimal subTotalProduct = product.getPrice().multiply(new BigDecimal(product.getQuantity()));
				subtotal = subTotalProduct.add(subtotal);
			}
		}
		return subtotal;
	}
	
	public BigDecimal getTotalValue() {
		BigDecimal total = new BigDecimal(0);
		total = getSubTotalValue().add(getAdditionalValue()).subtract(getDiscountValue());
		return total;
	}
	
}
