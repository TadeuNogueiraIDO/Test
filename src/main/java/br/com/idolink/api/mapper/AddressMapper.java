package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.AddressRequest;
import br.com.idolink.api.dto.response.AddressResponse;
import br.com.idolink.api.model.Address;
import br.com.idolink.api.model.User;

@Component
public class AddressMapper {

	@Autowired
	private ModelMapper mapper;

	public AddressResponse response(Address model) {
		return mapper.map(model, AddressResponse.class);
	}
	
	public Address create (AddressRequest request, User user) {
		Address model = mapper.map(request, Address.class);
		model.setUser(user);
		return model;
	}

	public List<AddressResponse> response(List<Address> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}

}
