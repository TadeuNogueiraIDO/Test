package br.com.idolink.api.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.idolink.api.dto.response.PortifolioMovimentationGeneralResponse;
import br.com.idolink.api.dto.response.PortifolioMovimentationItem;
import br.com.idolink.api.dto.response.PortifolioMovimentationsReponse;
import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.SingleQuickPay;
import br.com.idolink.api.model.enums.TypeMenuOrder;

@Component
public class PortifolioMovimentsMapper {

	

	
	
	public PortifolioMovimentationGeneralResponse toResponse(List<SingleQuickPay> singleQuickPay, List<ShopQuickPay> shopQuickPay) {
		HashMap<LocalDate, List<PortifolioMovimentationItem>> map = new HashMap<LocalDate, List<PortifolioMovimentationItem>>();
		
		List<PortifolioMovimentationsReponse> response = new ArrayList<>();
		
		List<BigDecimal> total = new ArrayList<>();
		
		if(singleQuickPay != null) {
			singleQuickPay.forEach(m ->{
				LocalDate date = m.getDateModel().getDt_created().toLocalDate();
				PortifolioMovimentationItem item = 	singleQuickPayToPortifolioMovimentationItem(m);
			
				if(map.containsKey(date)) {
					map.get(date).add(item);
				}else {
					List<PortifolioMovimentationItem> order = new ArrayList<>();
					order.add(item);
					map.put(date, order);
				}
				
				total.add(item.getPrice());
			});
		}
		
		if(shopQuickPay != null) {
			shopQuickPay.forEach(m ->{
				
				LocalDate date = m.getDateModel().getDt_created().toLocalDate();
				PortifolioMovimentationItem item = 	shopQuickPayToPortifolioMovimentationItem(m);
				
				if(map.containsKey(date)) {
					map.get(date).add(item);
				}else {
					
					List<PortifolioMovimentationItem> order = new ArrayList<>();
					order.add(item);
					map.put(date, order);
				}
				
				total.add(item.getPrice());
			});
		}
		
		map.forEach((key, value) ->{
			response.add(
					PortifolioMovimentationsReponse.builder()
						.date(key)
						.itens(value).build()
					);
		});
		Collections.sort(response, (a, b) -> b.getDate().compareTo(a.getDate()));
		
		
		return PortifolioMovimentationGeneralResponse.builder()
				.total(total.stream().reduce(BigDecimal.ZERO, BigDecimal :: add))
				.portifolioMoviments(response).build();
	}
	
	public PortifolioMovimentationItem singleQuickPayToPortifolioMovimentationItem(SingleQuickPay model) {
		
		if(model.getUser().getName() != null && !model.getUser().getName().isBlank()) {
			
			return PortifolioMovimentationItem.builder()
					.orderNumber(model.getOrderNumber())
					.orderType(TypeMenuOrder.SINGLE)
					.title("quick simple")
					.id(model.getId())
					.name(model.getUser().getName())
					.price(
							(model.getSinglePrice() != null? model.getSinglePrice():model.getProducts().stream().map(p -> p.getPrice()).reduce(BigDecimal.ZERO, BigDecimal :: add))
							.multiply(
									BigDecimal.valueOf(model.getSingleQuantity() != null ? model.getSingleQuantity() : 
										model.getProducts().stream().map(p -> p.getQuantity()).reduce(0, Integer :: sum))
									)
							).build();
		}else {
		
		return PortifolioMovimentationItem.builder()
				.orderNumber(model.getOrderNumber())
				.orderType(TypeMenuOrder.SINGLE)
				.title("quick simple")
				.id(model.getId())
				.name(null)
				.price(
						(model.getSinglePrice() != null? model.getSinglePrice():model.getProducts().stream().map(p -> p.getPrice()).reduce(BigDecimal.ZERO, BigDecimal :: add))
						.multiply(
								BigDecimal.valueOf(model.getSingleQuantity() != null ? model.getSingleQuantity() : 
									model.getProducts().stream().map(p -> p.getQuantity()).reduce(0, Integer :: sum))
								)
						).build();
		}
	}
	
	public PortifolioMovimentationItem shopQuickPayToPortifolioMovimentationItem(ShopQuickPay model) {
		
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		model.getProducts().forEach(p ->{
			totalPrice.add(BigDecimal.valueOf(p.getQuantity()).multiply(p.getShopProductVariation().getPrice()));
		});
		
		return PortifolioMovimentationItem.builder()
				.orderNumber(model.getOrderNumber())
				.orderType(TypeMenuOrder.SINGLE)
				.title("quick shop")
				.id(model.getId())
				.name(model.getProducts().get(0).getShopProductVariation().shopProduct.getShopCategory().getShop().getIdo().getName())
				.price(totalPrice).build();
	}
}
