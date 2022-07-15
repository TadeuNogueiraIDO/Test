package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.DeliveryPaymentSetup;

@Repository
public interface DeliveryPaymentSetupRepository extends JpaRepository<DeliveryPaymentSetup, Long>{

	@Query("from DeliveryPaymentSetup s where s.shop.id = :id")
	List<DeliveryPaymentSetup> findByShop(Long id);
}
