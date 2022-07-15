package br.com.idolink.api.dto.request;

import javax.validation.constraints.NotNull;

import br.com.idolink.api.model.enums.QuickPayFinalizationType;
import br.com.idolink.api.model.enums.TypeShipping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuickPayOrderRequest {

	@NotNull
	private QuickPayFinalizationType finalizationType;
	
	@NotNull
	private Boolean hasClientData;
	
	@NotNull
	private Boolean hasDeliveryAdress;
	
	private String clientName;
	
	private String clientEmail;
	
	private String clientPhone;
	
	private String dialCode;
	
	private String clientDocument;
	
	private String clientAddress;
	
	private TypeShipping typeShipping;
	
	private String shippingDescription;
		
}
