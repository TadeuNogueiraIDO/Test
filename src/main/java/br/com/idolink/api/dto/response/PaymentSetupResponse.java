package br.com.idolink.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSetupResponse {

	private Long id;
	
	private boolean gatewayIdo;

	private boolean paymentDelivery;

	private boolean mercadoPago;

	private boolean pagSeguro;

	private boolean payPal;

	private boolean addTax;

	private boolean addTip;
	
	private DeliveryPaymentSetupResponse paymentDeliveries;
	
	
	
}
