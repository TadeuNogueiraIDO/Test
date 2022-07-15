package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.idolink.api.model.State;

public interface StateRepository extends JpaRepository<State, Long>{
	
}
