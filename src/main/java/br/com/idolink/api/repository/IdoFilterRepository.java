package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Ido;

@Repository
public interface IdoFilterRepository {
	
	Optional<Ido> findByIdUserFilter(Long idoId);
}
