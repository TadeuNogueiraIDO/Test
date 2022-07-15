package br.com.idolink.api.dto.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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
public class ShopQuickPayCheckoutResponse {
	
	private Long shopQuickPayId;
	private QuickPayType type;
	private List<ShopQuickPayProductResponse> products;
	private BigDecimal discountValue;
	private BigDecimal additionalValue;
	
	private QuickPayPaymentStatus statusPayment;
	
	private QuickPaySedingStatus statusSeding;
	
	public BigDecimal getSubTotalValue() {

		BigDecimal subtotal = new BigDecimal(0);

		for (ShopQuickPayProductResponse product : getProducts()) {

			BigDecimal priceProduct = Objects.nonNull(product.getProductVariation().getPromoPrice())
					? product.getProductVariation().getPromoPrice()
					: product.getProductVariation().getPrice();

			BigDecimal subTotalProduct = priceProduct.multiply(new BigDecimal(product.getQuantity()));
			subtotal = subTotalProduct.add(subtotal);
		}
		return subtotal;
	}

	public BigDecimal getTotalValue() {
		BigDecimal total = new BigDecimal(0);
		total = getSubTotalValue().add(getAdditionalValue()).subtract(getDiscountValue());
		return total;
	}
}
