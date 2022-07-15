package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Address;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Long>{

	List<Address> findByUserId(Long userId);
	
}
