package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.response.PaymentTypeResponse;
import br.com.idolink.api.model.PaymentType;
import br.com.idolink.api.service.MessagePropertieService;

@Component
public class PaymentTypeMapper{
	
	@Autowired
	private StorageApi storageApi;
	
	@Autowired
	private MessagePropertieService messagePropertieService;
	
	@Autowired
	private ModelMapper mapper;

	public PaymentTypeResponse response(PaymentType model) {
		
		if(Objects.nonNull(model)) {

			PaymentTypeResponse response = mapper.map(model, PaymentTypeResponse.class);
			response.setName(messagePropertieService.getMessageAttribute(model.getName()));
			
			if(Objects.nonNull(model.getFile())) {
				response.setIcon(storageApi.findByUuid(model.getFile()));
			}
			
			return response;
		}
		return null;
	}

	public List<PaymentTypeResponse> response(List<PaymentType> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
	
}
