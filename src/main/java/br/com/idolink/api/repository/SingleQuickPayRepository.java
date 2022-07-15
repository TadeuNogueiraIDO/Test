package br.com.idolink.api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.idolink.api.model.SingleQuickPay;
import br.com.idolink.api.model.User;

@Repository
public interface SingleQuickPayRepository extends JpaRepository<SingleQuickPay, Long>{

	List<SingleQuickPay> findByUser(User user);
	
	@Query("from SingleQuickPay s where s.id = :orderId and s.user.id = :userId")
	SingleQuickPay findByUserIdAndId(@Param("userId") Long userId,@Param("orderId") Long orderId);
	
	@Query("SELECT COUNT(s) FROM SingleQuickPay s where s.user.id = :userId AND s.dateModel.dt_created BETWEEN :startDate AND :endDate")
	Long countInvoice(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate );
	
	@Query("select i from SingleQuickPay i where i.id =:idOrder and i.user.id =:idUser")
	Optional<SingleQuickPay> findByIdUserByIdOrder(Long idOrder,Long idUser);
	
	@Query("select i from SingleQuickPay i where i.orderNumber =:order and i.user.id =:userId")
	Optional<SingleQuickPay> findByOrderByUser(Long userId, String order);
	 	
	@Query("select i from SingleQuickPay i where i.id =:id and i.user.id =:userId")
	Optional<SingleQuickPay> findByIdByUser(Long userId, Long id);
}
