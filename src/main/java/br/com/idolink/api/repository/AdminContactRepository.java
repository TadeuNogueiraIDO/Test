package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.AdminContact;

@Repository
public interface AdminContactRepository extends JpaRepository<AdminContact, Long> {

	List<AdminContact> findByOrderById();
	 

}
