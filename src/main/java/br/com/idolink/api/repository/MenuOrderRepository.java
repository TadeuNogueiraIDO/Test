package br.com.idolink.api.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.idolink.api.filter.MenuOrderFilter;
import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.SingleQuickPay;

@Repository
public interface MenuOrderRepository {
	List<SingleQuickPay> findSingleQuiqkPay(Long userId,MenuOrderFilter filter,List<LocalDateTime> dates);
	
	List<ShopQuickPay> findShopQuiqkPay(Long userId,MenuOrderFilter filter,List<LocalDateTime> dates);
	
	List<ShopQuickPay> findShopQuickPayInvoice(LocalDate date, List<Long> idosId, Long userId);
	
	List<SingleQuickPay> findSingleQuickPayInvoice(LocalDate date, List<Long> idosId, Long userId);
	
	List<ShopQuickPay> findShopQuickPayDashboard(List<LocalDateTime> dates, Long userId, Long shopId);
	
	List<SingleQuickPay> findSingleQuickPayDashboard(List<LocalDateTime> dates, Long userId, Long shopId);

}
	