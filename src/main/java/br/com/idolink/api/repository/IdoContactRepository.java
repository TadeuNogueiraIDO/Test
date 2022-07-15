package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Contact;
import br.com.idolink.api.model.Ido;
import br.com.idolink.api.model.IdoContact;

@Repository
public interface IdoContactRepository extends JpaRepository<IdoContact, Long> {

	List<IdoContact> findIdoContactByIdo(Ido ido);
	
	void deleteIdoContactByIdo(Ido ido);
	
	void deleteIdoContactByIdoAndContact(Ido ido, Contact contact);
	
	@Query(value ="SELECT distinct (activated) FROM public.ido_contact where ido_id = :ido and dt_deleted is null", nativeQuery = true)
	Boolean findByIdoByActived (Long ido);
	
	@Query(value = "SELECT * FROM public.ido_contact\r\n"
			+ "where ido_id =:ido ", nativeQuery = true )
	List<IdoContact> findByIdo(Long ido);
	
}
