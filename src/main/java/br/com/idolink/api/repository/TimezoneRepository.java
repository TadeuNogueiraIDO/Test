package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Timezone;

@Repository
public interface TimezoneRepository extends JpaRepository<Timezone, Long> {

	
	List<Timezone> findByCodName(String codName);
	
}
