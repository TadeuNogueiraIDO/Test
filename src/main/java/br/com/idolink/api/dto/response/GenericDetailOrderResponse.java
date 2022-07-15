package br.com.idolink.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.idolink.api.model.enums.QuickPayFinalizationType;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.model.enums.TypeShipping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericDetailOrderResponse {

	private Long id;
	
	private String domain;
	
	private String nameShop;

	private BigDecimal discountValue;

	private BigDecimal additionalValue;

	private List<ShopQuickPayProductResponse> products;

	private QuickPayFinalizationType finalizationType;

	private Boolean hasDeliveryAdress;

	private String orderNumber;

	private PaymentTypeResponse paymentType;

	private String clientName;

	private String clientEmail;

	private String clientPhone;

	private String clientAddress;

	private LocalDateTime created;

	private QuickPayPaymentStatus statusPayment;

	private QuickPaySedingStatus statusSeding;

	private BigDecimal subTotalValue;
	
	private BigDecimal totalValue;
	
	private TypeShipping typeShipping;
	
	private String shippingDescription;
	


}
