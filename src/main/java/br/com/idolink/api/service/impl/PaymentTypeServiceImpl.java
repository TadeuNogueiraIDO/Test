package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.PaymentTypeResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.PaymentTypeMapper;
import br.com.idolink.api.model.PaymentType;
import br.com.idolink.api.repository.PaymentTypeRepository;
import br.com.idolink.api.service.PaymentTypeService;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService{

		
	@Autowired
	private PaymentTypeRepository repository;
		
	@Autowired
	private PaymentTypeMapper mapper;
	
	@Override
	public List<PaymentTypeResponse> listPaymentTypes() {
		
		List<PaymentType> model = repository.findAllByOrderByIdAsc();
		return  mapper.response(model);
	}
	
	@Override
	public PaymentTypeResponse findPaymentTypeById(Long id) {
		
		Optional<PaymentType> model = repository.findById(id);
		if(model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND,"PaymentType","api.error.payment.type.not.found");
		}
		return mapper.response(model.get());
	}
					
}
