package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.idolink.api.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

	
	@Query(nativeQuery = true, value = " (select c.* from country c where country like '%brasil%' or  country like '%estadosunidos%' or  country like '%canada%' order by ddi desc, country desc) "
			+ "union all "
			+ "(select c.* from country c where country not like '%brasil%' and  country not like '%estadosunidos%' and  country not like '%canada%') ")
	List<Country> listByBraEuaCad();
}
