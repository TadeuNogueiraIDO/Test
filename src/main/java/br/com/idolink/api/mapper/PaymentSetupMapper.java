package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.PaymentSetupRequest;
import br.com.idolink.api.dto.response.PaymentSetupResponse;
import br.com.idolink.api.model.PaymentSetup;
import br.com.idolink.api.model.enums.TypePaymentSetup;
import br.com.idolink.api.service.DeliveryPaymentSetupService;

@Component
public class PaymentSetupMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private DeliveryPaymentSetupService deliveryPaymentSetupService;

	public PaymentSetupResponse response(PaymentSetup model) {
		PaymentSetupResponse response = mapper.map(model, PaymentSetupResponse.class);
		converTypePaymentSetupInResponse( response, model.getTypePaymentSetup());
		
		response.setPaymentDeliveries(deliveryPaymentSetupService.findByShop(model.getShop().getId()));	

		return response;
	}
	
	public List<PaymentSetupResponse> response(List<PaymentSetup> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
	
	public PaymentSetup model(PaymentSetupRequest request) {
		PaymentSetup model =  mapper.map(request, PaymentSetup.class);
		convertRequestInTypePaymentSetup(request, model);
		return model;
	}
	
	 private void converTypePaymentSetupInResponse(PaymentSetupResponse response, String typePaymentSetup) {
			
			if(Objects.nonNull(typePaymentSetup)) {
				
				String[]splitType = typePaymentSetup.split(";");
				
				for (String data : splitType) {
					if(data.equals(TypePaymentSetup.GATEWAYIDO.toString())) {
						response.setGatewayIdo(true);
					}
					if(data.equals(TypePaymentSetup.MERCADOPAGO.toString())) {
						response.setMercadoPago(true);
					}
					if(data.equals(TypePaymentSetup.PAGSEGURO.toString())) {
						response.setPagSeguro(true);
					}
					if(data.equals(TypePaymentSetup.PAYMENTDELIVERY.toString())) {
						response.setPaymentDelivery(true);
					}
					if(data.equals(TypePaymentSetup.PAYPAL.toString())) {
						response.setPayPal(true);
					}
					if(data.equals(TypePaymentSetup.ADDTAX.toString())) {
						response.setAddTax(true);
					}
					if(data.equals(TypePaymentSetup.ADDTIP.toString())) {
						response.setAddTip(true);
					}
				}
			}
		}
		
		private void convertRequestInTypePaymentSetup(PaymentSetupRequest request, PaymentSetup setup) {
			
			StringBuffer typePaymentSetup = new StringBuffer();
			
			if(request.isGatewayIdo()) {
				typePaymentSetup.append(TypePaymentSetup.GATEWAYIDO.toString()).append(";");
			}
			if(request.isMercadoPago()) {
				typePaymentSetup.append(TypePaymentSetup.MERCADOPAGO.toString()).append(";");
			}
			if(request.isPagSeguro()) {
				typePaymentSetup.append(TypePaymentSetup.PAGSEGURO.toString()).append(";");
			}
			if(request.isPaymentDelivery()) {
				typePaymentSetup.append(TypePaymentSetup.PAYMENTDELIVERY.toString()).append(";");
			}
			if(request.isPayPal()) {
				typePaymentSetup.append(TypePaymentSetup.PAYPAL.toString()).append(";");
			}
			if(request.isAddTax()) {
				typePaymentSetup.append(TypePaymentSetup.ADDTAX.toString()).append(";");
			}
			if(request.isAddTip()) {
				typePaymentSetup.append(TypePaymentSetup.ADDTIP.toString()).append(";");
			}
			setup.setTypePaymentSetup(typePaymentSetup.toString());
			
		}
	
}
