package br.com.idolink.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.idolink.api.dto.request.PasswordRecoveryRequest;
import br.com.idolink.api.dto.request.PersonalDataUserRequest;
import br.com.idolink.api.dto.request.UserPasswordRequest;
import br.com.idolink.api.dto.request.UserRequest;
import br.com.idolink.api.dto.response.PersonalDataUserResponse;
import br.com.idolink.api.dto.response.UserResponse;

public interface UserService {

	 Page<UserResponse> list(Pageable pageable);
    
	 UserResponse findById(Long id);
	 
	 String emailAutorization(String token);
	 
	 UserResponse create(UserRequest request);

	 UserResponse update(Long id, UserRequest request);

     void delete(Long id);
     
     UserResponse updatePassword(UserPasswordRequest password, Long id);
     
     UserResponse updateEmailUser(String email, Long id);
     
     UserResponse updateAvatarUser(Long avatar, Long id);
     
     PersonalDataUserResponse updatePersonalData(Long id, PersonalDataUserRequest request);
         
     void generatePasswordRecovery(String email);
     
     void recoverPasswordWithCode(PasswordRecoveryRequest request);
     
     UserResponse updateCountry(Long userId, Long idCountry);
     
     void validateAccountByEmail(Long idUser);
}