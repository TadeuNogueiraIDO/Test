package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.AdminContactRequest;
import br.com.idolink.api.dto.response.AdminContactResponse;
import br.com.idolink.api.model.AdminContact;

@Component
public class AdminContactMapper {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private StorageApi storageApi;

	public AdminContactResponse response(AdminContact model) {
		AdminContactResponse response = mapper.map(model, AdminContactResponse.class);

		if (Objects.nonNull(model.getMainIcon()) && model.getMainIcon() != 0) {
			response.setMainIcon(storageApi.findFileById(model.getMainIcon()));
		}

		if (Objects.nonNull(model.getSecondaryIcon()) && model.getSecondaryIcon() != 0) {
			response.setSecondaryIcon(storageApi.findFileById(model.getSecondaryIcon()));
		}

		return response;
	}

	public List<AdminContactResponse> response(List<AdminContact> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}

	public AdminContact requestToModel(AdminContactRequest request) {
		return mapper.map(request, AdminContact.class);
	}

}
