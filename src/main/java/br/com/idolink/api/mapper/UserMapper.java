package br.com.idolink.api.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.request.UserRequest;
import br.com.idolink.api.dto.request.UserStatusRequest;
import br.com.idolink.api.dto.response.PersonalDataUserResponse;
import br.com.idolink.api.dto.response.UserResponse;
import br.com.idolink.api.model.User;

@Service
public class UserMapper {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private StorageApi storageApi;

	public UserResponse response(User model) {
		UserResponse response = mapper.map(model, UserResponse.class);
		
		if(Objects.nonNull(model.getFileAvatar()) && model.getFileAvatar() != 0) {
			response.setFileAvatar(storageApi.findFileById(model.getFileAvatar()));
		}
		return response;
	}
	
	public UserResponse modelToResponse(User model) {
		UserResponse response = mapper.map(model, UserResponse.class);
	

		return response;
	}
	
	public PersonalDataUserResponse modelResponse(User model) {
		return mapper.map(model, PersonalDataUserResponse.class);
	}

	public List<UserResponse> response(List<User> model) {
		return model.stream().map(m -> this.response(m)).collect(Collectors.toList());
	}

	public User model(UserRequest request) {
		return mapper.map(request, User.class);
	}

	public User requestToModel(UserStatusRequest request) {
		return mapper.map(request, User.class);
	}

}
