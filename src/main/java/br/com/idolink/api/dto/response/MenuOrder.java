package br.com.idolink.api.dto.response;

import java.math.BigDecimal;

import br.com.idolink.api.model.enums.QuickPayFinalizationType;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import br.com.idolink.api.model.enums.QuickPaySedingStatus;
import br.com.idolink.api.model.enums.QuickPayType;
import br.com.idolink.api.model.enums.TypeMenuOrder;
import br.com.idolink.api.model.enums.TypeShipping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuOrder {
	
	private Long id;
	
	private String clientName;
	
	private String nameIdo;
	
	private TypeMenuOrder orderType;
	
	private BigDecimal price;
	
	private QuickPayPaymentStatus statusPayment;
	
	private QuickPaySedingStatus statusSeding;
	
	private String orderNumber;
	
	private QuickPayType type;
	
	private QuickPayFinalizationType typePayment;
	
	private TypeShipping typeShipping;
	
}
