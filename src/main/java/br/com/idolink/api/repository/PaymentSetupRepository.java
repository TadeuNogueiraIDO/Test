package br.com.idolink.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.PaymentSetup;

@Repository
public interface PaymentSetupRepository extends JpaRepository<PaymentSetup, Long>{

	@Query("from PaymentSetup s where s.shop.id = :id")
	Optional<PaymentSetup> findByShop(Long id);
}
