package br.com.idolink.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import br.com.idolink.api.filter.PortifolioMovimentationFilter;
import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.SingleQuickPay;

public interface PortifolioMovimentsRepository {

	List<SingleQuickPay> findSingleQuickPay (Long userId, PortifolioMovimentationFilter filter, List<LocalDateTime> dates);
	
	List<ShopQuickPay> findShopQuickPay (Long userId, PortifolioMovimentationFilter filter, List<LocalDateTime> dates);
}
