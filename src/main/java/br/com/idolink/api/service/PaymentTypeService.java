package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.response.PaymentTypeResponse;

public interface PaymentTypeService {
	
	List<PaymentTypeResponse> listPaymentTypes();
	
	PaymentTypeResponse findPaymentTypeById(Long id);
		
}
