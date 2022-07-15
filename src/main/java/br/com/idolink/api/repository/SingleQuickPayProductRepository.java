package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.SingleQuickPay;
import br.com.idolink.api.model.SingleQuickPayProduct;

@Repository
public interface SingleQuickPayProductRepository extends JpaRepository<SingleQuickPayProduct, Long> {

	List<SingleQuickPayProduct> findBySingleQuickPay(SingleQuickPay singleQuickPay);
			
}
