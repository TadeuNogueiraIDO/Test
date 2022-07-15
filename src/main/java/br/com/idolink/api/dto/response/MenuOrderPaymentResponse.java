package br.com.idolink.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.idolink.api.model.enums.QuickPayFinalizationType;
import br.com.idolink.api.model.enums.QuickPayPaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuOrderPaymentResponse {
	private QuickPayPaymentStatus statusPayment;
	
	private String paymentType;
	
	private String anotherPaymentType;
	
	private BigDecimal totalValue;
	
	private BigDecimal rateIdolink;
	
	private String statusGateway;
	
	private LocalDate orderOn;
	
	private LocalDate updated;
	
	private QuickPayFinalizationType finalizationType;
	
}
