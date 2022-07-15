package br.com.idolink.api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.dto.QuickPayIdoShopDTO;
import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.User;

@Repository
public interface ShopQuickPayRepository extends JpaRepository<ShopQuickPay, Long>{

	List<ShopQuickPay> findByUser(User user);
	
	@Query("from ShopQuickPay i where i.user.id = :userId and i.id = :orderId ")
	ShopQuickPay findByUserIdAndId(@Param("userId") Long userId, @Param("orderId") Long orderId);
	
	@Query("SELECT COUNT(s) FROM ShopQuickPay s where s.user.id = :userId AND s.dateModel.dt_created BETWEEN :startDate AND :endDate")
	Long countInvoice(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate );
	
	@Query("select new br.com.idolink.api.dto.QuickPayIdoShopDTO(i.name, s.name) from Ido i inner join Shop s on (i.id=s.ido.id) "
			+ "inner join ShopCategory sc on (s.id = sc.shop.id) "
			+ "inner join ShopProduct sp on (sc.id = sp.shopCategory.id) "
			+ "inner join ShopProductVariation pv on (sp.id = pv.shopProduct.id) "
			+ "inner join ShopQuickPayProduct sqpp on (pv.id=sqpp.shopProductVariation.id) "
			+ "inner join ShopQuickPay sqp on(sqpp.shopQuickPay.id=sqp.id) "
			+ "where sqp.id = :shopQuickPayId")
	Optional<QuickPayIdoShopDTO> findByShopQuickPay(@Param("shopQuickPayId") Long shopQuickPayId);
	
	@Query("select i from ShopQuickPay i where i.id =:idOrder and i.user.id =:idUser")
	Optional<ShopQuickPay> findByIdUserByIdOrder(Long idOrder,Long idUser);
	
	@Query("select i from ShopQuickPay i where i.orderNumber =:order and i.user.id =:userId")
	Optional<ShopQuickPay> findByOrderByUser(Long userId, String order);
	
	@Query("select i from ShopQuickPay i where i.id =:id and i.user.id =:userId")
	Optional<ShopQuickPay> findByIdbyUser(Long userId, Long id);

	
	
	
}
