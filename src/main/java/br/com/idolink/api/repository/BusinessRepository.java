package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Business;
import br.com.idolink.api.model.User;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
	
	Business findByUser(User user);
	
}
