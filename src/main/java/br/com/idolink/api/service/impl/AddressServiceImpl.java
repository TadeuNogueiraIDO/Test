package br.com.idolink.api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.request.AddressRequest;
import br.com.idolink.api.dto.response.AddressResponse;
import br.com.idolink.api.execption.BaseFullException;
import br.com.idolink.api.mapper.AddressMapper;
import br.com.idolink.api.model.Address;
import br.com.idolink.api.model.Country;
import br.com.idolink.api.model.State;
import br.com.idolink.api.model.User;
import br.com.idolink.api.repository.AddressRepository;
import br.com.idolink.api.repository.CountryRepository;
import br.com.idolink.api.repository.StateRepository;
import br.com.idolink.api.repository.UserRepository;
import br.com.idolink.api.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository repository;

	@Autowired
	private AddressMapper mapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private StateRepository stateRepository;

	
	@Override
	public List<AddressResponse> listByaddressUserLogged(Long userId) {
		
		List<Address> model = repository.findByUserId(userId);
		
		return mapper.response(model);
	}
		
	@Override
	public AddressResponse findById(Long id) {
		Optional<Address> model = repository.findById(id);
		validate(model, "Address", "api.error.address.not.found");

		return mapper.response(model.get());
	}

	@Override
	public List<AddressResponse> list() {
		List<Address> model = repository.findAll();
		return mapper.response(model);
	}

	@Override
	@Transactional
	public AddressResponse create(Long userId, AddressRequest request) {

		Country country = new Country();
		
		if(request.getCountry().getId() != null) {
			Optional<Country> c  = countryRepository.findById(request.getCountry().getId());
			
			if(!c.isEmpty()) {
				country = c.get();
			}
		}
		
		
				State state = new State();
			
				if(Objects.nonNull(request.getStateBrasil()) && Objects.nonNull( request.getStateBrasil().getId())) {
					Optional<State> s  = stateRepository.findById(request.getStateBrasil().getId());
				
					if(!s.isEmpty()) {
					state = s.get();
				}
	
			}
		
		validateAddreass(country,state);
		
		Optional<User> user = userRepository.findById(userId);
		if(user.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, "User", "api.error.user.not.found!");
		}
		
		Address model = mapper.create(request, user.get());

	
		
				//Outros
	if(!request.getCountry().getId().equals(1L)) {
		model.setStateBrasil(null);
		model.setNumber(null);
		model.setComplement(null);
		model.setDistrict(null);
		
	}	
				//Brasil
	if (request.getCountry().getId().equals(1L)) {
		model.setAddressLine2(null);
		model.setTypeOfHouse(null);
		model.setState(null);
		model.setStateBrasil(state);
		
	}
	
	model.setCountry(country);

	model =repository.save(model);
	return mapper.response(model);
	}
	
	@Override
	@Transactional
	public AddressResponse update(Long id, AddressRequest request) {
	 
		Country country = new Country();
		
		if(request.getCountry().getId() != null) {
			Optional<Country> c  = countryRepository.findById(request.getCountry().getId());
			
			if(!c.isEmpty()) {
				country = c.get();
			}
		}
		
				State state = new State();
			
				if(Objects.nonNull(request.getStateBrasil()) && Objects.nonNull( request.getStateBrasil().getId())) {
					Optional<State> s  = stateRepository.findById(request.getStateBrasil().getId());
				
					if(!s.isEmpty()) {
					state = s.get();
					
				}
					else{
						throw new BaseFullException(HttpStatus.NOT_FOUND, "State", "api.error.address.state.not.found");
					}
			}
				
		validateAddreass(country,state);
		
	
		Address model = repository.findById(id).orElseThrow(
				() -> new BaseFullException(HttpStatus.NOT_FOUND, "Address", "api.error.address.not.found"));

		BeanUtils.copyProperties(request, model, "id", "dateModel", "user");
		
					//Outros 
		if (!request.getCountry().getId().equals(1L)) {
			model.setStateBrasil(null);
			model.setNumber(null);
			model.setComplement(null);
			model.setDistrict(null);

		}
					// Brasil
		if (request.getCountry().getId().equals(1L)) {
			model.setAddressLine2(null);
			model.setTypeOfHouse(null);
			model.setState(null);
			model.setStateBrasil(state);

		}

		model.setCountry(country);
		
		model =repository.save(model);
		return mapper.response(model);
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) {
		Optional<Address> model = repository.findById(id);
		validate(model, "Endereço", "api.error.address.not.found");
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new BaseFullException(HttpStatus.CONFLICT, "Address", "api.error.address.conflict");
		}

	}

	private void validate(Optional<?> model, String field, String message) {

		if (model.isEmpty()) {
			throw new BaseFullException(HttpStatus.NOT_FOUND, field, message);
		}
	}
	
	private void validateAddreass(Country country,State state) {
		
		if(country.getId().equals(null)) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "Country", "api.error.address.bad.request.country.null");
		}
		
		if(country.getId().equals(1L) && state.getId().equals(null)) {
			throw new BaseFullException(HttpStatus.BAD_REQUEST, "State", "api.error.address.bad.request.state.null ");
		}
		
	}
	
		
	
}
