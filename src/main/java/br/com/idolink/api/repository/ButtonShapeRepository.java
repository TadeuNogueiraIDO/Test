package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ButtonShape;

@Repository
public interface ButtonShapeRepository extends JpaRepository<ButtonShape, Long> {

	
	ButtonShape findByName(String name);
}
