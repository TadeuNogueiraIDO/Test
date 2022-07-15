package br.com.idolink.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.Currency;

@Repository
public interface ToolCoinRepository extends JpaRepository<Currency, Long>{

}
