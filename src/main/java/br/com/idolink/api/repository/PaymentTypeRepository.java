package br.com.idolink.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.PaymentType;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long>{
	
	List<PaymentType> findAllByOrderByIdAsc();
	
	Optional<PaymentType> findByFile(String uuid);
}

