package br.com.idolink.api.dto.request;

import java.util.List;

import br.com.idolink.api.dto.request.common.GenericRequest;
import lombok.Data;

@Data
public class DeliveryPaymentSetupRequest {
	
	private List<GenericRequest> paymentTypeIds;
	
	private String otherPayment; 
}
