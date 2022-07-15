package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.response.DeliveryPaymentSetupResponse;
import br.com.idolink.api.dto.response.PaymentTypeResponse;
import br.com.idolink.api.model.DeliveryPaymentSetup;

@Component
public class DeliveryPaymentSetupMapper {

	
	@Autowired
	private PaymentTypeMapper paymentMapper;

	public DeliveryPaymentSetupResponse response(List<DeliveryPaymentSetup> models, Long shopId) {
		
		List<PaymentTypeResponse> paymentTypes = paymentMapper.response(models.stream().map(payment -> payment.getPaymentType()).collect(Collectors.toList()));
		
		String otherPayment = null;
		
		for (DeliveryPaymentSetup delivery : models) {
			if(Objects.nonNull(delivery.getPaymentType()) && delivery.getPaymentType().getId() == 13L) {
				otherPayment = delivery.getOtherPayment();
			}
		}
		
		DeliveryPaymentSetupResponse response = DeliveryPaymentSetupResponse.builder().paymentTypes(paymentTypes).otherPayment(otherPayment).shopId(shopId).build();
		
		return response;
	}
	
}
