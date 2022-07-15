package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.ShippingSettingsRequest;
import br.com.idolink.api.dto.response.ShippingSettingsResponse;
import br.com.idolink.api.model.ShippingSettings;
import br.com.idolink.api.model.enums.TypeShipping;

@Component
public class ShippingSettingsMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private OwnShippingMapper ownMapper;
	
	@Autowired
	private PostOfficeShippingMapper postOfficeMapper;
	
	@Autowired
	private LocalPickupMapper localPickupMapper;
	
	@Autowired
	private DigitalDeliveryMapper digitalDeliveryMapper;

	public ShippingSettingsResponse response(ShippingSettings model) {
		
		ShippingSettingsResponse response = mapper.map(model, ShippingSettingsResponse.class);
				
		converTypeShippingInResponse(response, model.getTypeShipping());
				
		//limpando configuracoes de envio nao aceitas. 
		if(Objects.nonNull(model.getOwnShipping()) && Objects.nonNull(model.getOwnShipping().getId())) {
			response.setOwnShippingResponse(ownMapper.response(model.getOwnShipping()));
		}else {
			response.setOwnShippingResponse(null);
		}
		
		if(Objects.nonNull(model.getPostOfficeShipping()) && Objects.nonNull(model.getPostOfficeShipping().getId())){
			response.setPostOfficeShippingResponse(postOfficeMapper.response(model.getPostOfficeShipping()));
		}else {	
			response.setPostOfficeShippingResponse(null);
		}
		
		if(Objects.nonNull(model.getLocaPickups()) && !model.getLocaPickups().isEmpty()) {
			response.setLocalPickupsResponses(localPickupMapper.response(model.getLocaPickups()));
		}else {
			response.setLocalPickupsResponses(null);
		}
		
		if(Objects.nonNull(model.getDigitalDelivery()) && Objects.nonNull(model.getDigitalDelivery().getId()) ) {
			response.setDigitalDeliveryResponse(digitalDeliveryMapper.response(model.getDigitalDelivery()));
		}else {
			response.setDigitalDeliveryResponse(null);
		}
				
		return response;
	}
	
	public List<ShippingSettingsResponse> response(List<ShippingSettings> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}
			
	public ShippingSettings model(ShippingSettingsRequest request) {
		
		ShippingSettings model =  mapper.map(request, ShippingSettings.class);
		convertRequestInTypeShipping(request, model);
		return model;
	}
		
    private void converTypeShippingInResponse(ShippingSettingsResponse response, String typeShipping) {
		
		if(Objects.nonNull(typeShipping)) {
			
			String[]splitDataUser = typeShipping.split(";");
			
			for (String data : splitDataUser) {
				
				if(data.equals(TypeShipping.OWNSHIPPING.toString())) {
					response.setOwnShipping(true);
				}
				if(data.equals(TypeShipping.POSTOFFICE.toString())) {
					response.setPostOffice(true);
				}
				if(data.equals(TypeShipping.DIGITALDELIVERY.toString())) {
					response.setDigitalDelivery(true);
				}
				if(data.equals(TypeShipping.LOCALPICKUP.toString())) {
					response.setLocalPickup(true);
				}
			}
		}
	}
	
	private void convertRequestInTypeShipping(ShippingSettingsRequest request, ShippingSettings settings) {
		
		StringBuffer typeShippings = new StringBuffer();
		
		if(request.getDigitalDelivery()) {
			typeShippings.append(TypeShipping.DIGITALDELIVERY.toString()).append(";");
		}
		if(request.getLocalPickup()) {
			typeShippings.append(TypeShipping.LOCALPICKUP.toString()).append(";");
		}
		if(request.getOwnShipping()) {
			typeShippings.append(TypeShipping.OWNSHIPPING.toString()).append(";");
		}
		if(request.getPostOffice()) {
			typeShippings.append(TypeShipping.POSTOFFICE.toString()).append(";");
		}
		settings.setTypeShipping(typeShippings.toString());
		
	}
			
}
