package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.BusinessHour;

@Repository
public interface BusinessHourRepository extends JpaRepository<BusinessHour, Long>{
	
	@Query("from BusinessHour i where i.ido.id = :id")
	BusinessHour findByIdo(@Param("id")Long id);
	
}
