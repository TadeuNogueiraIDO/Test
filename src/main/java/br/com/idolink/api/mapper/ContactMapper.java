package br.com.idolink.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.request.ContactRequest;
import br.com.idolink.api.dto.response.ContactResponse;
import br.com.idolink.api.model.Contact;

@Component
public class ContactMapper {

	@Autowired
	private ModelMapper mapper;
		
	public ContactResponse modelToResponse(Contact model) {
		
		ContactResponse response= mapper.map(model, ContactResponse.class);
		
		return response;
	}

	public Contact requestToModel(ContactRequest request) {
		return mapper.map(request, Contact.class);
	}

	public List<ContactResponse> modelToResponse(List<Contact> model) {

		return model.stream().map(m -> modelToResponse(m)).collect(Collectors.toList());
	}

}
