package br.com.idolink.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.ShopQuickPayProduct;

@Repository
public interface ShopQuickPayProductRepository extends JpaRepository<ShopQuickPayProduct, Long> {

	List<ShopQuickPayProduct> findByShopQuickPay(ShopQuickPay shopQuickPay);
			
}
