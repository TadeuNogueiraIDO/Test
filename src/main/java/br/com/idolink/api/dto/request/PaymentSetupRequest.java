package br.com.idolink.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSetupRequest {

	private boolean gatewayIdo;

	private boolean paymentDelivery;

	private boolean mercadoPago;

	private boolean pagSeguro;

	private boolean payPal;

	private boolean addTax;

	private boolean addTip;
}
