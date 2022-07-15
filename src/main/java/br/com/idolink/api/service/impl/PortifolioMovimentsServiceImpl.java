package br.com.idolink.api.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.dto.response.PortifolioMovimentationGeneralResponse;
import br.com.idolink.api.filter.PortifolioMovimentationFilter;
import br.com.idolink.api.mapper.PortifolioMovimentsMapper;
import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.SingleQuickPay;
import br.com.idolink.api.repository.PortifolioMovimentsRepository;
import br.com.idolink.api.service.PortifolioMovimentsService;
import br.com.idolink.api.utils.MenuDateUtils;

@Service
public class PortifolioMovimentsServiceImpl implements PortifolioMovimentsService {

	@Autowired
	private PortifolioMovimentsRepository repository;

	@Autowired
	private PortifolioMovimentsMapper mapper;

	@Override
	public PortifolioMovimentationGeneralResponse findPortifolioMovements(PortifolioMovimentationFilter filter,
			Long userId) {

		List<LocalDateTime> dates = new ArrayList<>();

		if (filter.getPeriod() != null) {
			dates.addAll(MenuDateUtils.convertEnumToDate(filter.getPeriod()));
		} else {
			if (filter.getInitDate() != null)
				dates.addAll(MenuDateUtils.validateDate(filter.getInitDate(), filter.getEndDate()));
		}

		List<SingleQuickPay> singleQuickPay = new ArrayList<>();
		List<ShopQuickPay> shopQuickPay = new ArrayList<>();

		if (filter.getTypeSale() != null) {
			switch (filter.getTypeSale()) {
			case ONLINE:
				break;
			case QUICKPAY:
				shopQuickPay = repository.findShopQuickPay(userId, filter, dates);	
				break;
			case QUICKSIMPLE:
				singleQuickPay = repository.findSingleQuickPay(userId, filter, dates);
				break;
			default:
				break;
			}
		} else {
			singleQuickPay = repository.findSingleQuickPay(userId, filter, dates);
			shopQuickPay = repository.findShopQuickPay(userId, filter, dates);
		}
		return mapper.toResponse(singleQuickPay, shopQuickPay);
	}

}
