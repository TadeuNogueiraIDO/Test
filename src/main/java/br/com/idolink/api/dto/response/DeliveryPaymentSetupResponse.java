package br.com.idolink.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPaymentSetupResponse {

	private Long shopId;
	
	private List<PaymentTypeResponse> paymentTypes;
	
	private String otherPayment; 
}
