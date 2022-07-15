package br.com.idolink.api.dto.request;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.QuickPayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleQuickPayCheckoutRequest{

	private BigDecimal singlePrice;
	private Integer singleQuantity;
	
	@NotNull
	private QuickPayType type;
	
	private String observation;
	private BigDecimal discountValue;
	private BigDecimal additionalValue;
	private List<SingleQuickPayProductRequest> products;
		
}
