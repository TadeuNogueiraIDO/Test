package br.com.idolink.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.idolink.api.model.enums.QuickPayFinalizationType;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.model.enums.QuickPayType;
import br.com.idolink.api.model.enums.TypeShipping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleQuickPayResponse {
	
	private Long id;
	
	private BigDecimal singlePrice;
	
	private BigDecimal discountValue;
	
	private BigDecimal additionalValue;
		
	private String observation;
	
	private Integer singleQuantity;
	
	private QuickPayType type;
	
	private List<SingleQuickPayProductResponse> products;
	
	private QuickPayFinalizationType finalizationType;
	
	private Boolean hasClientData;
	
	private Boolean hasDeliveryAdress;
	
	private String orderNumber;
		
	private PaymentTypeResponse paymentType;
	
	private String clientName;
	
	private String clientEmail;
	
	private String clientPhone;
	
	private String dialCode;
	
	private String clientDocument;
	
	private String clientAddress;
	
	private LocalDateTime createdIn;
	
	private LocalDateTime updatedIn;
	
	private QuickPayPaymentStatus statusPayment;
	
	private QuickPaySedingStatus statusSeding;
	
	private BigDecimal subTotalValue;
	
	private BigDecimal totalValue; 
	
	private TypeShipping typeShipping;
	
	private String shippingDescription;
	
}
