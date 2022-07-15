package br.com.idolink.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.DiscountCoupon;

@Repository
public interface DiscountCouponRepository extends JpaRepository<DiscountCoupon, Long> {
	

	Optional<DiscountCoupon> findByCouponCode(String couponCode);
	
	@Query("select d from DiscountCoupon d where d.shop.id =:shopId ")
	List<DiscountCoupon> findCouponByShop(Long shopId);
	
	@Query("from DiscountCoupon d where d.couponCode = :codCoupon and d.status = true")
	DiscountCoupon findByCod(String codCoupon);
	
	@Query("from DiscountCoupon d where d.couponCode = :codCoupon and d.status = true and d.shop.id = :id")
	Optional<DiscountCoupon>  findByCodByShop(String codCoupon, Long id);
	
	@Query("select c from DiscountCoupon c where c.id =:idCoupon and c.shop.ido.business.user.id =:userId ")
	Optional<DiscountCoupon> findByIdByUser(Long idCoupon, Long userId);
}