package br.com.idolink.api.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.idolink.api.api.StorageApi;
import br.com.idolink.api.dto.response.MenuOrder;
import br.com.idolink.api.dto.response.MenuOrderDeliveryResponse;
import br.com.idolink.api.dto.response.MenuOrderDetailItemResponse;
import br.com.idolink.api.dto.response.MenuOrderItem;
import br.com.idolink.api.dto.response.MenuOrderPaymentResponse;
import br.com.idolink.api.dto.response.MenuOrderResponse;
import br.com.idolink.api.model.ClientQuickPay;
import br.com.idolink.api.model.ShopQuickPay;
import br.com.idolink.api.model.ShopQuickPayProduct;
import br.com.idolink.api.model.SingleQuickPay;
import br.com.idolink.api.model.SingleQuickPayProduct;
import br.com.idolink.api.model.enums.QuickPayType;
import br.com.idolink.api.model.enums.TypeMenuOrder;
import br.com.idolink.api.service.MessagePropertieService;

@Service
public class MenuOrderMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private StorageApi storageApi;

	@Autowired
	private MessagePropertieService messagePropertieService;
	
	public List<MenuOrderResponse> singleQuickPayToMenuOrderResponse (List<SingleQuickPay> singleQuickPay, List<ShopQuickPay> shopQuickPay){
		HashMap<LocalDate, List<MenuOrder>> map = new HashMap<LocalDate, List<MenuOrder>>();
		
		if(singleQuickPay != null) {
			singleQuickPay.forEach(m ->{
				LocalDate date = m.getDateModel().getDt_created().toLocalDate();
				if(map.containsKey(date)) {
					map.get(date).add(singleQuickPayToMenuOrder(m));
				}else {
					
					List<MenuOrder> order = new ArrayList<>();
					order.add(singleQuickPayToMenuOrder(m));
					map.put(date, order);
				}
			});
		}
		
		if(shopQuickPay != null) {
			shopQuickPay.forEach(m ->{
				LocalDate date = m.getDateModel().getDt_created().toLocalDate();
				if(map.containsKey(date)) {
					map.get(date).add(shopQuickPayToMenuOrder(m));
				}else {
					
					List<MenuOrder> order = new ArrayList<>();
					order.add(shopQuickPayToMenuOrder(m));
					map.put(date, order);
				}
			});
		}
		List<MenuOrderResponse> response = new ArrayList<>();
		
		map.forEach((key, value) ->{
			response.add(
					MenuOrderResponse.builder()
						.date(key)
						.menuOrder(value).build()
					);
		});
		
		Collections.sort(response, (a, b) -> b.getDate().compareTo(a.getDate()));
		
		return response;
	}
	                                   
	public MenuOrderDetailItemResponse singleQuickPayToMenuOrderDetailResponse(SingleQuickPay model) {
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal adittionaoValue = model.getAdditionalValue() != null ? model.getAdditionalValue() : BigDecimal.ZERO;
		BigDecimal discountValue = model.getDiscountValue() != null  ? model.getDiscountValue() : BigDecimal.ZERO;
		BigDecimal priceTotal = (model.getSinglePrice()!= null?model.getSinglePrice():BigDecimal.ZERO)
				.multiply(BigDecimal.valueOf(model.getSingleQuantity()!=null?model.getSingleQuantity():0L));
		
		if(model.getProducts() != null && model.getProducts().size() > 0) {
			priceTotal =  model.getProducts().stream().map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal :: add);
		}
		
		total = priceTotal.add(adittionaoValue).subtract(discountValue);
		
		return MenuOrderDetailItemResponse.builder()
				.orderNumber(model.getOrderNumber())
				.orderId(model.getId())
				.date(model.getDateModel().getDt_created())
				.subtotal(priceTotal)
				.additionalValue(adittionaoValue)
				.discountValue(discountValue)
				.sendValue(BigDecimal.ZERO)
				.totalValue(total)
				.itens(model.getProducts() != null ? 
						model.getProducts().stream().map(p -> singleQuickPayToMenuOrderItem(p)).collect(Collectors.toList())
						: new ArrayList<>())
				.build();
	}

	public MenuOrderDetailItemResponse singleQuickPayToMenuOrderDetailResponse(ShopQuickPay model) {
		
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal adittionaoValue = model.getAdditionalValue() != null ? model.getAdditionalValue() : BigDecimal.ZERO;
		BigDecimal discountValue = model.getDiscountValue() != null  ? model.getDiscountValue() : BigDecimal.ZERO;
		if(model.getProducts() != null && model.getProducts().size() > 0) {
			for (ShopQuickPayProduct p : model.getProducts()) {
				BigDecimal price = p.getShopProductVariation().getPromoPrice() != null ? p.getShopProductVariation().getPromoPrice() : p.getShopProductVariation().getPrice();
				total = total.add(BigDecimal.valueOf(p.getQuantity()).multiply(price));
			}
			
		}
		
		return MenuOrderDetailItemResponse.builder()
				.orderId(model.getId())
				.date(model.getDateModel().getDt_created())
				.additionalValue(adittionaoValue)
				.discountValue(discountValue)
				.sendValue(BigDecimal.ZERO)
				.totalValue(total != BigDecimal.ZERO ? total.add(adittionaoValue).subtract(discountValue): BigDecimal.ZERO)
				.itens(model.getProducts() != null ? 
						model.getProducts().stream().map(p -> singleQuickPayToMenuOrderItem(p)).collect(Collectors.toList())
						: new ArrayList<>())
				.build();
	}
	
	
	public MenuOrderPaymentResponse singleQuickPayToMenuOrderPaymentResponse (SingleQuickPay model) {
		BigDecimal total;
		BigDecimal adittionaoValue = model.getAdditionalValue() != null ? model.getAdditionalValue() : BigDecimal.ZERO;
		BigDecimal discountValue = model.getDiscountValue() != null  ? model.getDiscountValue() : BigDecimal.ZERO;
		BigDecimal priceTotal = model.getSinglePrice() != null ? model.getSinglePrice().multiply(BigDecimal.valueOf(model.getSingleQuantity())) : BigDecimal.ZERO;
		total = priceTotal.add(adittionaoValue).subtract(discountValue);
		if(model.getProducts() != null && model.getProducts().size() > 0) {
			priceTotal =  model.getProducts().stream().map(p -> p.getPrice()).reduce(BigDecimal.ZERO, BigDecimal :: add);
		}
		
		LocalDateTime data = model.getDateModel().getDt_updated() != null ? model.getDateModel().getDt_updated() :
			model.getDateModel().getDt_created();
		
		return MenuOrderPaymentResponse.builder()
				.orderOn(model.getDateModel().getDt_created().toLocalDate())
				.paymentType(model.getPaymentType() != null ? messagePropertieService.getMessageAttribute(model.getPaymentType().getName()) : null)
				.rateIdolink(model.getAdditionalValue())
				.statusGateway("")
				.statusPayment(model.getStatusPayment())
				.totalValue(total)
				.finalizationType(model.getFinalizationType())
				.anotherPaymentType(model.getAnotherPaymentType())
				.updated(data.toLocalDate()).build();
	}
	
	
	public MenuOrderPaymentResponse singleQuickPayToMenuOrderPaymentResponse (ShopQuickPay model) {
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal adittionaoValue = model.getAdditionalValue() != null ? model.getAdditionalValue() : BigDecimal.ZERO;
		BigDecimal discountValue = model.getDiscountValue() != null  ? model.getDiscountValue() : BigDecimal.ZERO;
		if(model.getProducts() != null && model.getProducts().size() > 0) {
			model.getProducts().forEach(p ->{
				total.add(BigDecimal.valueOf(p.getQuantity()).multiply(p.getShopProductVariation().getPrice()));
			});
		}
		
		LocalDateTime data = model.getDateModel().getDt_updated() != null ? model.getDateModel().getDt_updated() :
			model.getDateModel().getDt_created();
		
		return MenuOrderPaymentResponse.builder()
				.orderOn(model.getDateModel().getDt_created().toLocalDate())
				.paymentType(model.getPaymentType() != null ? messagePropertieService.getMessageAttribute(model.getPaymentType().getName()):"")
				.rateIdolink(model.getAdditionalValue())
				.statusGateway("")
				.statusPayment(model.getStatusPayment())
				.totalValue(total != BigDecimal.ZERO ? total.add(adittionaoValue).subtract(discountValue): BigDecimal.ZERO)
				.finalizationType(model.getFinalizationType())
				.anotherPaymentType(model.getAnotherPaymentType())
				.updated(data.toLocalDate()).build();
	}
									 
	public MenuOrderDeliveryResponse singleQuickPayToMenuOrderDeliveryResponse (SingleQuickPay model) {
		
		ClientQuickPay client = new ClientQuickPay();
		if(model.getDataClient() != null) {
			client = model.getDataClient();
		}
		return MenuOrderDeliveryResponse.builder()
				.clientAddress(client.getClientAddress())
				.clientEmail(client.getClientEmail())
				.clientName(client.getClientName())
				.clientPhone(client.getClientPhone())
				.typeFreight(model.getTypeShipping())
				.freight(model.getShippingDescription())
				
				.statusSeding(model.getStatusSeding()).build();
	}
	
	public MenuOrderDeliveryResponse shopQuickPayToMenuOrderDeliveryResponse (ShopQuickPay model) {
		
		ClientQuickPay client = new ClientQuickPay();
		if(model.getDataClient() != null) {
			client = model.getDataClient();
		}
		return MenuOrderDeliveryResponse.builder()
				.clientAddress(client.getClientAddress())
				.clientEmail(client.getClientEmail())
				.clientName(client.getClientName())
				.clientPhone(client.getClientPhone())
				.typeFreight(model.getTypeShipping())
				.freight(model.getShippingDescription())
				
				.statusSeding(model.getStatusSeding()).build();
	}
	
	public MenuOrderItem singleQuickPayToMenuOrderItem(SingleQuickPayProduct model) {
		
		
		return MenuOrderItem.builder()
				.id(model.getId())
				.observation(model.getObservation())
				.price(model.getPrice())
				.quantity(model.getQuantity())
				.name(model.getName())
				.urlImage(null).build();
	}
	
	public MenuOrderItem singleQuickPayToMenuOrderItem(ShopQuickPayProduct model) {
		
		return MenuOrderItem.builder()
				.id(model.getId())
				.observation(model.getObservation())
				.price(model.getShopProductVariation().getPrice())
				.quantity(model.getQuantity())
				.name(model.getShopProductVariation().getName())
				.urlImage(storageApi.findFileById(model.getShopProductVariation().getShopProduct().getMainImage())).build();
	}
	
	public MenuOrder singleQuickPayToMenuOrder(SingleQuickPay model) {
		
		
		BigDecimal price = model.getType().equals(QuickPayType.SINGLE)? model.getSinglePrice().multiply(BigDecimal.valueOf(model.getSingleQuantity())) 
				: model.getProducts().stream().map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		BigDecimal totalPrice = price.add(model.getAdditionalValue()).subtract(model.getDiscountValue());
		
		return MenuOrder.builder()
				.clientName(model.getDataClient() != null?model.getDataClient().getClientName(): null)
				.orderNumber(model.getOrderNumber())
				.nameIdo("")
				.orderType(TypeMenuOrder.SINGLE)
				.id(model.getId())
				.price(totalPrice)
				.statusPayment(model.getStatusPayment())
				.typePayment(model.getFinalizationType())
				.statusSeding(model.getStatusSeding())
				.typeShipping(model.getTypeShipping())
				.type(model.getType()).build();
	}
	
	public MenuOrder shopQuickPayToMenuOrder(ShopQuickPay model) {
		
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		for (ShopQuickPayProduct p : model.getProducts()) {
			BigDecimal price = p.getShopProductVariation().getPromoPrice() != null ? p.getShopProductVariation().getPromoPrice() : p.getShopProductVariation().getPrice();
			totalPrice = totalPrice.add(BigDecimal.valueOf(p.getQuantity()).multiply(price));
		}
		
		totalPrice = totalPrice.add(model.getAdditionalValue()).subtract(model.getDiscountValue());
		
		return MenuOrder.builder()
				.clientName(model.getDataClient() != null?model.getDataClient().getClientName(): null)
				.orderNumber(model.getOrderNumber())
				.nameIdo(model.getProducts().get(0) != null ? model.getProducts().get(0).getShopProductVariation()
						.getShopProduct().getShopCategory()
						.getShop().getIdo().getName(): "")
				.id(model.getId())
				.orderType(TypeMenuOrder.SHOP)
				.price(totalPrice)
				.typeShipping(model.getTypeShipping())
				.typePayment(model.getFinalizationType())
				.statusPayment(model.getStatusPayment())
				.statusSeding(model.getStatusSeding())
				.type(null).build();
	}
	public MenuOrder responseToModel(MenuOrderResponse response) {
		
		return mapper.map(response, MenuOrder.class);
	}
	public List<MenuOrder> responseToModel(List<MenuOrderResponse> response) {
	
		return response.stream().map(m -> this.responseToModel(m)).collect(Collectors.toList());
	}

}
