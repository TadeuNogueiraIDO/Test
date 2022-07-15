package br.com.idolink.api.service;

import java.util.List;

import br.com.idolink.api.dto.request.AddressRequest;
import br.com.idolink.api.dto.response.AddressResponse;

public interface AddressService {

	AddressResponse create(Long userId, AddressRequest request);
	
	List<AddressResponse> listByaddressUserLogged(Long userId);
	
	List<AddressResponse> list();
	
	AddressResponse findById(Long id);

	AddressResponse update(Long id, AddressRequest request);
	
	void delete(Long id);
	
}
