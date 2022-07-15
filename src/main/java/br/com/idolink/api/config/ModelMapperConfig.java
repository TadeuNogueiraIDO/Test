package br.com.idolink.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.idolink.api.dto.response.ShippingSettingsResponse;
import br.com.idolink.api.dto.response.ToolResponse;
import br.com.idolink.api.model.ShippingSettings;
import br.com.idolink.api.model.Tool;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(Tool.class, ToolResponse.class)
        .addMappings(mapper -> mapper.skip(ToolResponse::setPrices));
		
		modelMapper.createTypeMap(ShippingSettings.class, ShippingSettingsResponse.class)
        .addMappings(mapper -> mapper.skip(ShippingSettingsResponse::setDigitalDelivery))
        .addMappings(mapper -> mapper.skip(ShippingSettingsResponse::setLocalPickup))
        .addMappings(mapper -> mapper.skip(ShippingSettingsResponse::setOwnShipping))
        .addMappings(mapper -> mapper.skip(ShippingSettingsResponse::setPostOffice));
		
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
				
		return modelMapper;
		
	}
	
}